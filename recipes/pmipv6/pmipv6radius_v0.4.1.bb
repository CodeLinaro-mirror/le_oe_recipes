DESCRIPTION = "pmipv6 Radius"
HOMEPAGE = "http://openairinterface.eurecom.fr/"
SECTION = "console/network"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"
DEPENDS = "virtual/kernel"


inherit autotools
PR = "r0"
FILES_${PN} +="/usr/lib/*.a"
FILES_${PN} +="/usr/local/etc/radiusclient/*"
FILES_${PN} +="/usr/local/sbin/*"

SRC_URI = "git://source.codeaurora.org/quic/le/freeradius-client-1.1.6;protocol=http;branch=github/chunyeow/master \
file://configure.patch \
"

SRCREV = "a28ae2a79d36ed5f37ea1c18fcb34a0291f3cbe4"
S = "${WORKDIR}/git/openair3/PMIPv6/freeradius-client-1.1.6"
B = "${WORKDIR}/git/openair3/PMIPv6/freeradius-client-1.1.6"
do_configure() {
       autoreconf -i
       ./configure --host arm-linux-gnueabi
}
do_install_append() {
        install -d ${D}/usr/lib ${D}/usr/sbin ${D}/usr/include
        cp ${D}/usr/local/lib/*.a ${D}/usr/lib/
        cp ${D}/usr/local/lib/libfreeradius-client.so.2 ${D}/usr/lib/
        mv ${D}/usr/local/sbin/rad* ${D}/usr/sbin/
        cp ${D}/usr/local/include/freeradius-client.h ${D}/usr/include
}
