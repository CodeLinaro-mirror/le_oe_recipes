FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "\
    file://Remove-DHCP-entries-from-interfaces.patch \
"
