inherit autotools-brokensep gettext

LICENSE = "Public Domain"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f7c191d0ae6a652f63b2d2bc1ea8bdab"

SECTION = "libs"

PR = "r3"

SRC_URI = "http://downloads.sourceforge.net/project/jsoncpp/jsoncpp/${PV}/jsoncpp-src-${PV}.tar.gz \
        file://${PN}-src-${PV}/Makefile.am \
        file://${PN}-src-${PV}/configure.ac \
	file://${PN}-src-${PV}/jsoncpp.pc.in \
"

S = "${WORKDIR}/${PN}-src-${PV}"

SRC_URI[md5sum] = "24482b67c1cb17aac1ed1814288a3a8f"
SRC_URI[sha256sum] = "22b14ecd0de8cdad2b6b6839f6d0804d3b84e91f42861ebd843832a26a927433"

do_install_append() {

}
