DESCRIPTION = "Provide QSEEComAPI Headers required from securemsm"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

PR = "1"

SRC_URI = "file://${WORKSPACE}/vendor/qcom/opensource/time-services"

S = "${WORKDIR}/vendor/qcom/opensource/time-services"

do_install() {
   mkdir -p ${D}/usr/include
   cp -pPr ${WORKSPACE}/vendor/qcom/opensource/time-services/time_genoff.h ${D}/usr/include
}

