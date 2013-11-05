DESCRIPTION = "Installs external/skia's headers into staging directory"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
BSD;md5=3775480a712fc46a69647678acb234cb"
PR = "r1"

SRC_URI = "file://${WORKSPACE}/external/skia"

S = "${WORKDIR}/skia"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install_append() {
   mkdir -p ${STAGING_INCDIR}
   mkdir -p ${STAGING_INCDIR}/skia
   cp -a ${S}/src/core/*.h ${STAGING_INCDIR}/skia/
   cp -a ${S}/include/core/*.h ${STAGING_INCDIR}/skia/
   cp -a ${S}/include/effects/*.h ${STAGING_INCDIR}/skia/
   cp -a ${S}/include/images/*.h ${STAGING_INCDIR}/skia/
   cp -a ${S}/include/utils/*.h ${STAGING_INCDIR}/skia/
   cp -a ${S}/include/xml/*.h ${STAGING_INCDIR}/skia/
}
