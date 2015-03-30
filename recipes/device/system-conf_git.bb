inherit autotools

DESCRIPTION = "Device specific config"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"
PR = "r0"

# Provide a baseline
SRC_URI = "file://${WORKSPACE}/mdm-init/"

# Update for each machine
S = "${WORKDIR}/mdm-init/"

FILES_${PN} += "${base_libdir}/firmware/wlan/qca_cld/*"
