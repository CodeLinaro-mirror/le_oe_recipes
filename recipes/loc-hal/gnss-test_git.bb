inherit autotools qcommon

DESCRIPTION = "GNSS Test shippable app for LOC API HAL"
PR = "r5"
SRC_DIR = "${WORKSPACE}/hardware/qcom/gps/gnss-test/"
DEPENDS = "qmi-framework loc-hal"
S = "${WORKDIR}/hardware/qcom/gps/gnss-test"
EXTRA_OECONF = "--with-libhardware-includes=${STAGING_INCDIR} \
                --with-core-includes=${STAGING_INCDIR} \
                --with-glib \
                --enable-target=${BASEMACHINE} "

