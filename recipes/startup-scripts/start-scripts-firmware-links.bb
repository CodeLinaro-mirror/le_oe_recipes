DESCRIPTION = "Start up script for firmware links"
HOMEPAGE = "http://codeaurora.org"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"
LICENSE = "BSD"

SRC_URI_a-family = "file://a-family/firmware-links.sh"
SRC_URI_b-family = "file://b-family/firmware-links.sh"

PR = "r3"

inherit update-rc.d

INITSCRIPT_NAME = "firmware-links.sh"

do_install_a-family() {
    install -m 0755 ${WORKDIR}/a-family/firmware-links.sh -D ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

do_install_b-family() {
    install -m 0755 ${WORKDIR}/b-family/firmware-links.sh -D ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

pkg_postinst-${PN} () {
        update-alternatives --install ${sysconfdir}/init.d/${INITSCRIPT_NAME} ${INITSCRIPT_NAME} ${INITSCRIPT_NAME}.${PN} 60
        [ -n "$D" ] && OPT="-r $D" || OPT="-s"
        # remove all rc.d-links potentially created from alternatives
        update-rc.d $OPT -f ${INITSCRIPT_NAME} remove
        update-rd.d $OPT ${INITSCRIPT_NAME} multiuser
}
