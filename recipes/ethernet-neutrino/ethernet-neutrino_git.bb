inherit module autotools

DESCRIPTION = "Neutrino Ethernet driver"
LICENSE = "MIT-style"
LIC_FILES_CHKSUM = "file://DWC_ETH_QOS_dev.c;\
startline=1;endline=71;md5=647bc30ad1427f25bad8967a98d3aecb"

FILES_${PN}     += "${base_libdir}/modules/${KERNEL_VERSION}/"
FILES_${PN}     += "${sysconfdir}/init.d/neutrino_start_stop_le"
RPROVIDES_${PN} += "kernel-module-dwc-eth-qos"

do_unpack[deptask] = "do_populate_sysroot"
PR = "r0-${KERNEL_VERSION}"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://qcom-opensource/ethernet/neutrino/driver/"
SRC_URI += "file://neutrino_start_stop_le"

S = "${WORKDIR}/qcom-opensource/ethernet/neutrino/driver/"

EXTRA_OEMAKE =+ "LBITS=32"
EXTRA_OEMAKE =+ "DWC_ETH_QOS_DISABLE_PLT_INIT=1"
EXTRA_OEMAKE =+ "DWC_ETH_QOS_ENABLE_ETHTOOL=1"
EXTRA_OEMAKE =+ "CONFIG_IPA_OFFLOAD=1"

do_install() {
    module_do_install
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/neutrino_start_stop_le ${D}${sysconfdir}/init.d
    install -m 0644 ${S}/DWC_ETH_QOS_yapphdr.h ${STAGING_INCDIR}/
}

pkg_postinst_${PN} () {
    [ -n "$D" ] && OPT="-r $D" || OPT="-s"
    update-rc.d $OPT -f neutrino_start_stop_le remove
    update-rc.d $OPT neutrino_start_stop_le start 91 5 . stop 15 0 1 6 .
}
