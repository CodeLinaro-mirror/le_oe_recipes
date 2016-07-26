DESCRIPTION = "emac init.d script"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"

PR = "r0"

SRC_URI = "file://start_emac_le "

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/start_emac_le ${D}${sysconfdir}/init.d
}



