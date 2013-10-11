DESCRIPTION = "Installs external/skia's headers into staging directory"
LICENSE = "QUALCOMM-Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qcom/files/qcom-licenses/\
QUALCOMM-Proprietary;md5=92b1d0ceea78229551577d4284669bb8"
PR = "r0"

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
