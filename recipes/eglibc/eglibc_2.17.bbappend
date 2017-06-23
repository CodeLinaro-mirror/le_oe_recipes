FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "\
    file://CVE-2015-7547.patch \
    file://CVE-2016-4429.patch \
    file://CVE-2016-3706.patch \
    file://CVE-2015-0235.patch \
    file://CVE-2014-9402.patch \
"
