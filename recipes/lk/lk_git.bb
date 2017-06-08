inherit deploy

DESCRIPTION = "Little Kernel bootloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://www.codeaurora.org/gitweb/quic/la?p=kernel/lk.git"
PROVIDES = "virtual/bootloader"
FILESPATH =+ "${WORKSPACE}:"
SRC_URI  = "file://bootable/bootloader/lk/"
S        = "${WORKDIR}/bootable/bootloader/${PN}"
PR       = "r14"

PACKAGE_ARCH = "${MACHINE_ARCH}"

#re-use non-perf settings
BASEMACHINE        = "${@d.getVar('MACHINE', True).replace('-perf', '').replace('-hf', '')}"

MY_TARGET          = "${BASEMACHINE}"

MY_TARGET_mdm9640  = "mdm9640"
MY_TARGET_mdm9640-perf  = "mdm9640"
MY_TARGET_mdm9640-hf  = "mdm9640"

LIBGCC             = "${STAGING_LIBDIR}/${TARGET_SYS}/4.9.3/libgcc.a"

EXTRA_OEMAKE = "${MY_TARGET} ARCH='${TARGET_ARCH}' CC='${CC}' TOOLCHAIN_PREFIX='${TARGET_PREFIX}'  LIBGCC='${LIBGCC}'"
EXTRA_OEMAKE_append_mdm9640 = " SIGNED_KERNEL=1"
EXTRA_OEMAKE_append_mdm9640-hf = " SIGNED_KERNEL=1"

do_install() {
        install -d ${D}/boot

        install build-${MY_TARGET}/*.mbn ${D}/boot
}


FILES_${PN} = "/boot"
FILES_${PN}-dbg = "/boot/.debug"

do_deploy() {
        install ${S}/build-${MY_TARGET}/*.mbn ${DEPLOYDIR}
}

do_deploy[dirs] = "${S} ${DEPLOYDIR}"
addtask deploy before do_build after do_install

PACKAGE_STRIP = "no"
