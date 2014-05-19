DESCRIPTION = "WCNSS platform"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"
LICENSE = "BSD"

PR = "r0"

SRC_URI = "file://${WORKSPACE}/qcom-opensource/wlan/prima/firmware_bin \
           file://set_wcnss_mode"

S = "${WORKDIR}"

inherit update-rc.d

do_install() {
    install -d ${D}/etc
    install -d ${D}/etc/init.d
    install set_wcnss_mode ${D}/etc/init.d
}

do_install_append_msm8610() {
   mkdir -p ${D}/lib/firmware/wlan/prima
   cp -pPr ${S}/firmware_bin/* ${D}/lib/firmware/wlan/prima
}

do_install_append_msm8226() {
   mkdir -p ${D}/lib/firmware/wlan/prima
   cp -pPr ${S}/firmware_bin/* ${D}/lib/firmware/wlan/prima
}

INITSCRIPT_NAME = "set_wcnss_mode"
INITSCRIPT_PARAMS = "start 99 1 2 3 4 5 6 ."

pkg_postinst () {
	[ -n "$D" ] && OPT="-r $D" || OPT="-s"
	# remove all rc.d-links potentially created from alternatives
	update-rc.d $OPT -f ${INITSCRIPT_NAME} remove
	update-rc.d $OPT ${INITSCRIPT_NAME} ${INITSCRIPT_PARAMS}
}

FILES_${PN} = "/lib/firmware/*"
FILES_${PN} += "/etc/*"

