inherit module

DESCRIPTION = "Toshiba Neutrino Ethernet driver"
LICENSE = "MIT-style"
LIC_FILES_CHKSUM = "file://DWC_ETH_QOS_dev.c;\
startline=1;endline=71;md5=ab83971dfe75bb275cb6820dbbc27f95"

FILES_${PN}     += "${base_libdir}/modules/${KERNEL_VERSION}/"
RPROVIDES_${PN} += "kernel-module-dwc-eth-qos"

do_unpack[deptask] = "do_populate_sysroot"
PR = "r0-${KERNEL_VERSION}"

# This DEPENDS is to serialize kernel module builds
DEPENDS = "rtsp-alg"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://qcom-opensource/ethernet/neutrino/driver/"

S = "${WORKDIR}/qcom-opensource/ethernet/neutrino/driver/"

EXTRA_OEMAKE =+ "LBITS=32"
EXTRA_OEMAKE =+ "DWC_ETH_QOS_DISABLE_PLT_INIT=1"
EXTRA_OEMAKE =+ "DWC_ETH_QOS_ENABLE_ETHTOOL=1"


