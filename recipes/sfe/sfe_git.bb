inherit autotools module
DESCRIPTION = "Qualcomm Atheros Gigabit Shortcut Forwarding Engine Driver"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"

PR = "r1"

SRCREV = "0493b2d4d192f9431e543cf201df45fd8b14b592"
SRC_URI = "git://codeaurora.org/quic/qsdk/oss/lklm/shortcut-fe;protocol=git;branch=banana \
		   file://mdm_shortcut_fe.patch \
		   file://start_shortcut_fe_le "

S = "${WORKDIR}/git/shortcut-fe"

FILES_${PN}="/etc/init.d/start_shortcut_fe_le"

do_install() {
    module_do_install
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/start_shortcut_fe_le ${D}${sysconfdir}/init.d
}

INITSCRIPT_NAME = "start_shortcut_fe_le"
INITSCRIPT_PARAMS = "start 91 5 . stop 15 0 1 6 ."


