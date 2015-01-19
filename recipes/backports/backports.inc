inherit autotools module

DESCRIPTION = "Linux kernel backports on older kernels"
HOMEPAGE = "https://www.kernel.org/pub/linux/kernel/projects/backports/stable/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS = "virtual/kernel"

INC_PR = "r0"

SRC_URI = "file://${WORKSPACE}/backports-drivers"
S = "${WORKDIR}/backports-drivers/"

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D}"

do_install() {
    oe_runmake -C ${STAGING_KERNEL_DIR} M="${S}" INSTALL_MOD_PATH="${D}" \
        INSTALL_MOD_DIR=updates LDFLAGS="" INSTALL_MOD_STRIP=1 modules_install
}
