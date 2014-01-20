DESCRIPTION = "OpenMAX video for MSM chipsets"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"
SRC_URI = "file://${WORKSPACE}/mm-video-oss"

PR = "r18"

DEPENDS = "virtual/kernel"
DEPENDS += "glib-2.0"
DEPENDS += "mm-core-oss"
DEPENDS += "adreno200"
RDEPENDS = "mm-video-prop"
INSANE_SKIP = 1

# Need the kernel headers
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/mm-video-oss"

LV = "1.0.0"

inherit autotools

#re-use non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-perf', '')}"

EXTRA_OECONF += " --with-libhardware-headers=${WORKSPACE}/hardware/libhardware \
                 --with-sanitized-headers=${STAGING_KERNEL_DIR}/usr/include \
                 --with-common-includes=${STAGING_INCDIR} \
                 --enable-target-${BASEMACHINE}=yes"

CPPFLAGS += "-I${STAGING_INCDIR}/glib-2.0 \
             -I${STAGING_LIBDIR}/glib-2.0/include \
             -I${STAGING_INCDIR}/c++ \
             -I${STAGING_INCDIR}/c++/${TARGET_SYS}"

LDFLAGS += "-lglib-2.0"

FILES_${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /usr/share/*"

#Skips check for .so symlinks
INSANE_SKIP_${PN} = "dev-so"

do_install() {
	oe_runmake DESTDIR="${D}/" LIBVER="${LV}" install
	mkdir -p ${STAGING_INCDIR}/mm-core
	install -m 0644 ${S}/mm-core/inc/*.h ${STAGING_INCDIR}/mm-core
}
