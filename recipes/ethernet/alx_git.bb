inherit autotools linux-kernel-base module

DESCRIPTION = "Qualcomm Atheros Gigabit Ethernet Driver"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"

KERNEL_VERSION = "${@get_kernelversion('${STAGING_KERNEL_DIR}')}"

PR = "r2"

FILES_${PN} += "\
    ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra/alx.ko \
    "

SRC_URI = "\
          file://${WORKSPACE}/external/compat-wireless/drivers/net/ethernet/atheros/alx \
          file://start_alx_le \
"

S = "${WORKDIR}/alx"

do_install() {
    module_do_install
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/start_alx_le ${D}${sysconfdir}/init.d
}

pkg_postinst_${PN} () {
    [ -n "$D" ] && OPT="-r $D" || OPT="-s"
    update-rc.d $OPT -f start_alx_le remove
    update-rc.d $OPT start_alx_le start 91 5 . stop 15 0 1 6 .
}
