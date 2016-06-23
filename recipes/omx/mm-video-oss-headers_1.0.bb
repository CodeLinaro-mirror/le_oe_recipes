DESCRIPTION = "OpenMAX video for MSM chipsets"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

FILESPATH =+ "${WORKSPACE}:"
S = "${WORKDIR}/hardware/qcom/media"

SRC_URI = "file://hardware/qcom/media/"

PACKAGES = "${PN}"
PR = "r1"

INSANE_SKIP_${PN} += "installed-vs-shipped"

do_install() {
   install -d ${D}${includedir}/omx
   cp -a ${S}/mm-core/inc/*.h ${D}${includedir}/omx
   cp -a ${S}/libstagefrighthw/QComOMXMetadata.h ${D}${includedir}/omx
}

