inherit deploy

DESCRIPTION = "Little Kernel bootloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://www.codeaurora.org/gitweb/quic/la?p=kernel/lk.git"
PROVIDES = "virtual/bootloader"
SRC_URI  = "file://${WORKSPACE}/bootable/bootloader/lk"
S        = "${WORKDIR}/${PN}"
PR       = "r13"

PACKAGE_ARCH = "${MACHINE_ARCH}"

#re-use non-perf settings
BASEMACHINE        = "${@d.getVar('MACHINE', True).replace('-perf', '')}"

LIBGCC_9615-cdp    = "${STAGING_LIBDIR}/${TARGET_SYS}/4.6.3/libgcc.a"
LIBGCC_mdm9625     = "${STAGING_LIBDIR}/${TARGET_SYS}/4.6.3/libgcc.a"
LIBGCC_mdm9635     = "${STAGING_LIBDIR}/${TARGET_SYS}/4.6.3/libgcc.a"

MY_TARGET          = "${@ 'msm8226' if BASEMACHINE == 'apq8026' else '${BASEMACHINE}'}"

MY_TARGET_9615-cdp = "mdm9615"

BOOTLOADER_NAME = "${@base_contains('DISTRO_FEATURES', 'emmc-boot', 'emmc_appsboot', 'appsboot', d)}"

EXTRA_OEMAKE = "TOOLCHAIN_PREFIX='${TARGET_PREFIX}' ${MY_TARGET}"
EXTRA_OEMAKE_append_9615-cdp = " LIBGCC='${LIBGCC}'"
EXTRA_OEMAKE_append_mdm9625  = " LIBGCC='${LIBGCC}'"
EXTRA_OEMAKE_append_mdm9635  = " LIBGCC='${LIBGCC}'"
EXTRA_OEMAKE   += "${@base_contains('DISTRO_FEATURES', 'emmc-boot', 'EMMC_BOOT=1', '', d)}"
EXTRA_OEMAKE   += "${@base_contains('DISTRO_FEATURES', 'signed-kernel', 'SIGNED_KERNEL=1', '', d)}"

do_install() {
	install	-d ${D}/boot
	install build-${MY_TARGET}/${BOOTLOADER_NAME}.{mbn,raw} ${D}/boot
	if [ -f build-${MY_TARGET}/EMMCBOOT.MBN ]; then
		install build-${MY_TARGET}/EMMCBOOT.MBN ${D}/boot
	fi
}

FILES_${PN} = "/boot"

do_deploy () {
        install ${S}/build-${MY_TARGET}/${BOOTLOADER_NAME}.mbn ${DEPLOYDIR}
}
do_deploy[dirs] = "${S} ${DEPLOYDIR}"
addtask deploy before do_build after do_install

PACKAGE_STRIP = "no"
