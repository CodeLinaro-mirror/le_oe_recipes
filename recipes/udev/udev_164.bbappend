PRINC = "12"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"

SRC_URI += "file://do-not-install-unnecessary-udev-rules.patch"
SRC_URI_append_a-family += " file://a-family/local.rules"
SRC_URI_append_b-family += "file://b-family/local.rules \
                           file://b-family/set-dev-nodes.sh"

do_install_append_b-family () {
     install -d ${D}${sysconfdir}/udev/scripts/
     install -m 0755 ${FILESEXTRAPATHS}/b-family/set-dev-nodes.sh ${D}${sysconfdir}/udev/scripts/set-dev-nodes.sh
}
