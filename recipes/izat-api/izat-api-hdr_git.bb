inherit autotools qcommon

DESCRIPTION = "Izat api hdr"
PR = "r1"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/qcom-opensource/location/izat_api/"
S = "${WORKDIR}/qcom-opensource/location/izat_api"

do_configure() {
}

do_compile() {
}

do_install() {
    install -d ${D}${includedir}
    install -m 644 ${S}/*.h ${D}${includedir}
}
