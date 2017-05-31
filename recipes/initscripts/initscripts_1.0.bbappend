PRINC = "1"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"

#re-use non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-perf', '')}"

SRC_URI += "file://umountfs \
	    file://cookie.txt \
	    file://check_for_firmware_corruption.sh"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/cookie

        install -m 0755    ${WORKDIR}/cookie.txt        ${D}${sysconfdir}/init.d/cookie
	install -m 0755 ${WORKDIR}/check_for_firmware_corruption.sh  ${D}${sysconfdir}/init.d

	update-rc.d -r ${D} check_for_firmware_corruption.sh start 99 2 3 4 5 .
	update-rc.d -f -r ${D} reboot remove
        update-rc.d -f -r ${D} mountnfs.sh remove
        update-rc.d -f -r ${D} urandom remove
        rm -rf ${D}${sysconfdir}/init.d/reboot
}
