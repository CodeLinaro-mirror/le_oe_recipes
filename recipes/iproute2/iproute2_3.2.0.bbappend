PRINC = "3"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"

SRC_URI += "file://0001-tc-Add-flow-control-setting-on-PRIO-qdisc.patch"

EXTRA_OEMAKE = "CC='${CC}' CFLAGS+='-Wall -include ${STAGING_DIR_HOST}/kernel/usr/include/linux/pkt_sched.h -I../include' \
        KERNEL_INCLUDE=${STAGING_DIR_HOST}/usr/src/linux/usr/include \
        DOCDIR=${docdir}/iproute2 SUBDIRS='lib tc ip' SBINDIR=/sbin"

DEPENDS += "virtual/kernel"
