inherit autotools-brokensep

DESCRIPTION = "dsrc-tools"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/wlan/tools/NOTICE;md5=b61e09613da94fcbb21267d3e642f4a4"

DEPENDS = "libnl"

PR = "r0"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://wlan/tools/"

S = "${WORKDIR}/wlan/tools/dsrc"

CFLAGS += "-I${STAGING_INCDIR}/libnl3"

EXTRA_OEMAKE = "HAVE_LIBNL3=1 all dsrc_config"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/dsrc* ${D}${bindir}
    install -m 0644 ${S}/bin/dcc.dat ${D}${bindir}
}
