require perl-rdepends_${PV}.inc

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "\
        file://makedepend.SH.patch \
        "
