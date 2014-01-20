inherit autotools linux-kernel-base

DESCRIPTION = "Open Source kernel tests"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_URI = "file://${WORKSPACE}/qcom-opensource/kernel/kernel-tests"

DEPENDS = "virtual/kernel"

PR = "r3"

S = "${WORKDIR}/kernel-tests"
CFLAGS_pn-${PN} = ""
CPPFLAGS_pn-${PN} = ""
CXXFLAGS_pn-${PN} = ""
LDFLAGS_pn-${PN} = ""
PACKAGE_STRIP = "no"
KERNEL_VERSION = "${@get_kernelversion('${STAGING_KERNEL_DIR}')}"

EXTRA_OEMAKE += "ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX}"

#re-use non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-perf', '')}"

EXTRA_OECONF = "--prefix=/usr/kernel-tests \
                --with-kernel=${STAGING_KERNEL_DIR} \
                --disable-sps \
                --enable-target=${BASEMACHINE} \
                --with-sanitized-headers=${STAGING_KERNEL_DIR}/usr/include"

FILES_${PN}-dbg = "${prefix}/kernel-tests/*/.debug/* ${prefix}/src/debug/*"
FILES_${PN}-dbg += "${libdir}/*.so ${libdir}/.debug/*"
FILES_${PN} = "${prefix}/kernel-tests/* ${prefix}/src/*"
FILES_${PN} += "${datadir}/pixmaps/* ${libdir}/*"
