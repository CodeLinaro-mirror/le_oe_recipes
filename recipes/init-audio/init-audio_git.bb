DESCRIPTION = "Installing audio init script"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

PR = "r6"

SRC_URI = "file://files/init_qcom_audio"

inherit autotools

S = ${WORKDIR}/init-audio
INITSCRIPT_NAME = "init_qcom_audio"
INITSCRIPT_PARAMS = "start 41 S 2 3 4 5 S . stop 59 0 1 6 ."

do_install() {
    install -m 0755 ${S}/../files/init_qcom_audio -D ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

pkg_postinst() {
    [ -n "$D" ] && OPT="-r $D" || OPT="-s"
    update-rc.d $OPT -f ${INITSCRIPT_NAME} remove
    update-rc.d $OPT ${INITSCRIPT_NAME} ${INITSCRIPT_PARAMS}
}
