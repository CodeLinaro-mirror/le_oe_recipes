inherit linux-kernel-base localgit

DESCRIPTION = "QuIC Linux Kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(9615-cdp|mdm9625|mdm9625-perf)"

# Moved to here from the distro.conf file because it really kind of belongs
# here and we're moving more to being a BSP with the MSM linux distro...
KERNEL_IMAGETYPE = "zImage"
KERNEL_IMAGETYPE_9615-cdp = "Image"

# Provide a config baseline for things so the kernel will build...
KERNEL_DEFCONFIG_9615-cdp      = "msm9615_defconfig"
KERNEL_DEFCONFIG_mdm9625       = "msm9625_defconfig"
KERNEL_DEFCONFIG_mdm9625-perf  = "msm9625-perf_defconfig"
KERNEL_DEFCONFIG              ?= "msm9625_defconfig"

PACKAGE_ARCH = "${MACHINE_ARCH}"
KDIR = "/kernel"
SRC_DIR = "${WORKSPACE}/kernel"
PV = "git-${GITSHA}"
PR = "r6"

PROVIDES += "virtual/kernel"
DEPENDS = "virtual/${TARGET_PREFIX}gcc dtbtool-native mkbootimg-native"

INHIBIT_DEFAULT_DEPS = "1"
# Until usr/src/linux/scripts can be correctly processed
PACKAGE_STRIP = "no"
INHIBIT_PACKAGE_STRIP = "1"

PACKAGES = "kernel kernel-base kernel-module-bridge \
  kernel-module-ip-tables \
  kernel-module-iptable-nat \
  kernel-module-iptable-filter \
  kernel-module-ipt-masquerade \
  kernel-module-x-tables \
  kernel-module-nf-defrag-ipv4 \
  kernel-module-nf-conntrack \
  kernel-module-nf-conntrack-ipv4 \
  kernel-module-nf-nat"

PACKAGES =+ "kernel-image"
FILES_kernel-image = "/boot/${KERNEL_IMAGETYPE}*"

PACKAGES =+ "kernel-dev"
FILES_kernel-dev = "/boot/System.map* /boot/Module.symvers* /boot/config*"

PACKAGES =+ "kernel-vmlinux"
FILES_kernel-vmlinux = "/boot/vmlinux*"

PACKAGES =+ "kernel-headers"
FILES_kernel-headers = "${KDIR}/usr/include"

PACKAGES =+ "kernel-modbuild"
FILES_kernel-modbuild = "${KDIR}"
INSANE_SKIP_kernel-modbuild = "arch"

PACKAGES =+ "kernel-modules"
FILES_kernel-modules = "/lib/modules"

# The kernel makefiles do not like extra flags being given to make.
EXTRA_OEMAKE_pn-${PN} = ""
CFLAGS_pn-${PN} = ""
CPPFLAGS_pn-${PN} = ""
CXXFLAGS_pn-${PN} = ""
LDFLAGS_pn-${PN} = ""

export ARCH = "${TARGET_ARCH}"
export CROSS_COMPILE = "${TARGET_PREFIX}"

uses_modules () {
	grep -q -i -e '^CONFIG_MODULES=y$' "${O}/.config"
}

do_configure () {
	mkdir -p ${STAGING_KERNEL_DIR}
	rm -rf ${STAGING_KERNEL_DIR}/*
	rm -f ${O}
	ln -s ${STAGING_KERNEL_DIR} ${O}
	__do_clean_make
	oe_runmake ${KERNEL_DEFCONFIG} O=${O}
}

do_menuconfig() {
        export TERMWINDOWTITLE="${PN} Configuration"
        export SHELLCMDS="make ARCH=${ARCH} menuconfig O=${O}"
        ${TERMCMDRUN}
        if [ $? -ne 0 ]; then
                oefatal "'${TERMCMD}' not found. Check TERMCMD variable."
        fi
}

do_menuconfig[nostamp] = "1"
addtask menuconfig after do_configure

do_savedefconfig() {
	oe_runmake savedefconfig O=${O}
	mv ${O}/defconfig ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG}
}

addtask savedefconfig after do_configure

do_compile () {
	oe_runmake ${KERNEL_IMAGETYPE} O=${O}
	uses_modules && oe_runmake modules O=${O}
}

__do_clean_make () {
	[ -d ${O} ] && oe_runmake mrproper O=${O}
	oe_runmake mrproper
}

KERNEL_VERSION = "${@get_kernelversion('${O}')}"
do_install () {

	# Files destined for the target

	install -d ${D}/boot
	for f in System.map Module.symvers vmlinux; do
		install -m 0644 ${O}/${f} ${D}/boot/${f}-${KERNEL_VERSION}
	done
	install -m 0644 ${O}/arch/${TARGET_ARCH}/boot/${KERNEL_IMAGETYPE} \
		${D}/boot/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
	install -m 0644 ${O}/.config ${D}/boot/config-${KERNEL_VERSION}
	uses_modules && oe_runmake modules_install O=${O} INSTALL_MOD_PATH=${D}

	# Files needed for staging
	install -d ${D}${KDIR}/usr
	oe_runmake headers_install O=${D}${KDIR}
	oe_runmake ${KERNEL_DEFCONFIG} O=${D}${KDIR}
	uses_modules && oe_runmake modules_prepare O=${D}${KDIR}
    	cp -rf ${D}/* ${STAGING_DIR_TARGET}
}

do_deploy () {

        # Make bootimage
        ver=`sed -r 's/#define UTS_RELEASE "(.*)"/\1/' ${STAGING_KERNEL_DIR}/include/generated/utsrelease.h`

        # Make Device tree blobs for various hardware configurations
        dts_files=`find ${WORKSPACE}/kernel/arch/arm/boot/dts -iname *${MACHINE_DTS_NAME}*.dts | awk -F/ '{print $NF}' | awk -F[.][d] '{print $1}'`
 
        for d in ${dts_files}; do
            ${STAGING_KERNEL_DIR}/scripts/dtc/dtc -p 4096 -O dtb -o ${STAGING_DIR_TARGET}/boot/${d}.dtb ${WORKSPACE}/kernel/arch/arm/boot/dts/${d}.dts
        done

        dtb_files=`find ${STAGING_DIR_TARGET}/boot -iname *${MACHINE_DTS_NAME}*.dtb | awk -F/ '{print $NF}' | awk -F[.][d] '{print $1}'`

        # Create separate images with dtb appended to zImage for all targets.
        for d in ${dtb_files}; do
            targets=`echo ${d#${MACHINE_DTS_NAME}-}`
            cat ${STAGING_DIR_TARGET}/boot/zImage-${ver} ${STAGING_DIR_TARGET}/boot/${d}.dtb > ${STAGING_DIR_TARGET}/boot/dtb-zImage-${ver}-${targets}
        done

        ${STAGING_BINDIR_NATIVE}/dtbtool ${STAGING_DIR_TARGET}/boot/ -o ${STAGING_DIR_TARGET}/boot/masterDTB -p ${STAGING_KERNEL_DIR}/scripts/dtc/ -v

        mkdir -p ${DEPLOY_DIR_IMAGE}

        # Updated base address according to new memory map.
        ${STAGING_BINDIR_NATIVE}/mkbootimg --kernel ${STAGING_DIR_TARGET}/boot/zImage-${ver} \
            --dt ${STAGING_DIR_TARGET}/boot/masterDTB \
            --ramdisk /dev/null \
            --cmdline "noinitrd root=/dev/mtdblock17 rw rootfstype=yaffs2 console=ttyHSL0,115200,n8 androidboot.hardware=qcom ehci-hcd.park=3 g-android.rx_trigger_enabled=1" \
            --base 0x00300000 \
            --tags-addr 0x06800000 \
            --ramdisk_offset 0x0 \
            --output ${DEPLOY_DIR_IMAGE}/${MACHINE}-boot.img
}

addtask deploy before do_build after do_install
