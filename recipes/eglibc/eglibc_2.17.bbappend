FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "\
    file://CVE-2014-9984.patch \
    file://CVE-2015-7547.patch \
    file://CVE-2016-4429.patch \
    file://CVE-2016-3706.patch \
    file://CVE-2015-0235.patch \
    file://CVE-2014-9402.patch \
    file://CVE-2015-8779_1.patch \
    file://CVE-2015-8779_2.patch \
    file://CVE-2015-8778_1.patch \
    file://CVE-2015-8778_2.patch \
    file://Refactor-strtod-parsing-of-NaN-payloads.patch \
    file://CVE-2014-9761_1.patch \
    file://CVE-2014-9761_2.patch \
    file://CVE-2017-15804.patch \
    file://CVE-2017-15670.patch \
    file://CVE-2017-1000366.patch \
"
