DESCRIPTION = "Start up script for mhi dev net interface ip configuration"
HOMEPAGE = "http://codeaurora.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

#re-use non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-perf', '')}"

SRC_URI +="file://${BASEMACHINE}/config_mhi_dev_iface.sh"

PR = "r4"

inherit update-rc.d

INITSCRIPT_NAME   = "config_mhi_dev_iface.sh"
INITSCRIPT_PARAMS = "start 99 5 ."

do_install() {
    install -m 0755 ${WORKDIR}/${BASEMACHINE}/config_mhi_dev_iface.sh -D ${D}${sysconfdir}/init.d/config_mhi_dev_iface.sh
}

pkg_postinst_${PN} () {
        update-alternatives --install ${sysconfdir}/init.d/config_mhi_dev_iface.sh config_mhi_dev_iface.sh config_mhi_dev_iface.sh 60
        [ -n "$D" ] && OPT="-r $D" || OPT="-s"
        # remove all rc.d-links potentially created from alternatives
        update-rc.d $OPT -f config_mhi_dev_iface.sh remove
        update-rc.d $OPT config_mhi_dev_iface.sh multiuser
}
