DESCRIPTION = "OpenMAX video for MSM chipsets"
HOMEPAGE = "http://support.cdmatech.com"

LICENSE = "BSD" 
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

FILESPATH =+ "${WORKSPACE}:"
SRC_DIR = "${WORKSPACE}/hardware/qcom/media"

#inherit autotools localgit qcommon
inherit autotools pkgconfig

S = "${WORKDIR}/hardware/qcom/media"
#echo " WORKSPACE= ${WORKSPACE} WORKDIR= ${WORKDIR}"
#echo "${SRC_DIR}"

#EXTRA_OEMAKE = 'all -C ${S}'

SRC_URI  = "file://hardware/qcom/media/"
SRC_URI += "file://venus_v4l2.rules"

PACKAGES = "${PN}"

#DEPENDS += "glib-2.0 virtual/kernel live555 camera-hal  system-core"
DEPENDS += "glib-2.0 virtual/kernel autogen-native mm-video-oss-headers system-core mm-video-firmware-prebuilt"
#DEPENDS += "glib-2.0 virtual/kernel autogen-native mm-video-oss-headers system-core"
#PV = "1.0"
PR = "r1"

# Need the kernel headers
#PACKAGE_ARCH = "${MACHINE_ARCH}"
EXTRA_OECONF_append += "--with-sanitized-headers=${STAGING_INCDIR}/linux-headers/usr/include"
EXTRA_OECONF_append += " --enable-target=msm8909"
EXTRA_OECONF_append += " --with-sanitized-headers=${STAGING_INCDIR}/usr/include"
EXTRA_OECONF_append += " --with-common-includes=${STAGING_INCDIR}"
CFLAGS += " -I${STAGING_KERNEL_BUILDDIR}/usr/include"
CFLAGS += " -I${STAGING_KERNEL_DIR}/include"
CFLAGS += " -I${STAGING_INCDIR}"

CXXFLAGS += " -I${STAGING_KERNEL_BUILDDIR}/usr/include"
CXXFLAGS += " -I${STAGING_KERNEL_DIR}/include"
CXXFLAGS += " -I${STAGING_INCDIR}"

CFLAGS += "-I${STAGING_INCDIR}/omx"
CXXFLAGS += "-I${STAGING_INCDIR}/omx"
CFLAGS += "-I${STAGING_INCDIR}/PQp"
CXXFLAGS += "-I${STAGING_INCDIR}/PQp"
# Need the glib-2.0 headers
CFLAGS += "-I${STAGING_INCDIR}/glib-2.0"
CFLAGS += "-I${STAGING_LIBDIR}/glib-2.0/include"
CXXFLAGS += "-I${STAGING_INCDIR}/glib-2.0"
CXXFLAGS += "-I${STAGING_LIBDIR}/glib-2.0/include"
LDFLAGS += "-lglib-2.0 -lpthread"
CFLAGS += "-D__LE__"
#LDFLAGS += "-lglib-2.0 -L${STAGING_LIBDIR}/egl"
LDLIBS   = "-fPIC ${LDFLAGS}"
TARGET_CC_ARCH += "${LDLIBS}"

# C++ headers added to CXXFLAGS
#CXXINC  = "-I${STAGING_INCDIR}/c++"
#CXXINC += "-I${STAGING_INCDIR}/c++/${TARGET_SYS}"
#CXXFLAGS ="-fno-default-inline -fno-inline-functions ${CXXINC}"
#CXXFLAGS_NOOPT = "-O0 ${CXXINC}"
#echo " STAGING_INCDIR=${STAGING_INCDIR}"

CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/display/libcopybit"
CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/display/libgralloc"
CPPFLAGS_append += "-I${WORKSPACE}/system/core/include"
CPPFLAGS_append += "-I${WORKSPACE}/frameworks/native/include"
CPPFLAGS_append += "-I${WORKSPACE}/hardware/libhardware/include"
#CPPFLAGS_append += "-I${STAGING_INCDIR}/live555"
#CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/media/mm-core/src/common"
#CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/media/mm-core/inc"
#CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/media/mm-video-v4l2/vidc/venc/inc"
#CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/media/mm-video-v4l2/vidc/common/inc"
#CPPFLAGS_append += "-I${WORKSPACE}/hardware/qcom/media/libc2dcolorconvert"
#CPPFLAGS_append += "-I${WORKSPACE}/kernel/include"
#CPPFLAGS_append += "-I${WORKSPACE}/kernel/include/uapi"
#CPPFLAGS_append += "-I${WORKSPACE}/kernel/arch/arm/include"


FILES_${PN} = "${libdir}/libOmxCore.so.* \
               ${libdir}/libOmxCore.la \
               ${libdir}/libOmxVenc.so.* \
               ${libdir}/libOmxVenc.so \
               ${libdir}/libOmxVenc.la \
               ${sysconfdir}"

# Skips check for .so symlinks
INSANE_SKIP_${PN} = "dev-so"
INSANE_SKIP_${PN} += "installed-vs-shipped"

do_install_append() {
    dest=/etc/udev/rules.d
    install -d ${D}${dest}
    install -m 644 ${WORKDIR}/venus_v4l2.rules -D ${D}${dest}/venus_v4l2.rules
}


