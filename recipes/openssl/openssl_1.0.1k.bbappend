FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "\
    file://CVE-2015-3197.patch \
    file://CVE-2016-0703.patch \
    file://openssl-disable-EXPORT-and-LOW-SSLv3-ciphers-by-default.patch \
    file://openssl-harden-SSLv2-supporting-servers-against-Bleichenbach.patch \
    file://openssl-disable-SSLv2-default-build-default-negotiation.patch \
    file://openssl-bring-SSL-method-documentation-up-to-date.patch \
"
