inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Vendor Library"
HOMEPAGE = "http://codeaurora.org/"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "common system-core hci-qcomm-init"
RDEPENDS_${PN} = "system-core-libcutils"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://hardware/qcom/bt/libbt-vendor/"

S = "${WORKDIR}/hardware/qcom/bt/libbt-vendor/"

CFLAGS_append = " -DBT_SOC_TYPE_ROME"

EXTRA_OECONF = "--with-common-includes="${WORKSPACE}/hardware/libhardware/include" \
                --with-lib-path=${STAGING_LIBDIR} \
               "

do_install_append() {
       mv ${D}/usr/lib/libbt-vendor.so.0 ${D}/usr/lib/libbt-vendor.so
}
