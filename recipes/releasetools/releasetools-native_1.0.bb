inherit native

DESCRIPTION = "releasetools used for OTA"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

PR = "r0"

FILESPATH =+ "${WORKSPACE}/bootable/scripts/tools/:"

SRC_URI   = "file://releasetools/"
SRC_URI  += "file://full_ota.sh"


S = "${WORKDIR}/releasetools"


do_configure_append() {
    mv ${WORKDIR}/full_ota.sh ${S}
    chmod 755 ${S}/full_ota.sh
}

do_compile[noexec] = "1"
do_install[noexec] = "1"
