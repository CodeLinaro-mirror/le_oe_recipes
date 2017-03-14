DESCRIPTION = "pmipv6"
SECTION = "console/network"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"
DEPENDS = "virtual/kernel libnfnetlink libpcap pmipv6radius"

inherit qcommon

S = "${WORKDIR}/pmipv6"
SRC_DIR =  "${WORKSPACE}/pmipv6/"
PR = "r0"

EXTRA_OECONF += "--host arm-linux-gnueabi"

do_configure_prepend() {
    cp ${S}/src/pmgr.c.in ${S}/src/pmgr.c
	cp ${S}/src/pmgr.h.in ${S}/src/pmgr.h
}

do_install_append() {
install -d ${D}/usr/sbin ${D}/data/
cp ${S}/extras/example-mag1.conf ${D}/data/pmip-mag.conf
}
FILES_${PN} += "/data/pmip-mag.conf"
