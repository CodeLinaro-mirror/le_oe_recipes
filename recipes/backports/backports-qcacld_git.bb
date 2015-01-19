include backports.inc

SRC_URI += "file://${WORKSPACE}/wlan/qcacld-2.0"
SRC_URI += "file://defconfig-qcacld"

FIRMWARE_PATH = "${D}${base_libdir}/firmware/wlan/qca_cld"
DRIVER_PATH = "${S}/drivers/staging/qcacld-2.0"

do_configure() {
    export CC="${BUILD_CC}"
    mkdir -p drivers/staging
    ln -s ${WORKDIR}/qcacld-2.0 drivers/staging/
    echo -e "\nsource drivers/staging/qcacld-2.0/Kconfig" >> Kconfig
    echo -e "\nobj-\$(CPTCFG_QCA_CLD_WLAN) += drivers/staging/qcacld-2.0/" >> Makefile.kernel
    install -m 0644 ${WORKDIR}/defconfig-qcacld defconfigs/qcacld
    oe_runmake defconfig-qcacld
}

do_install_append() {
    install -d ${FIRMWARE_PATH}
    install -m 0644 ${DRIVER_PATH}/firmware_bin/WCNSS_cfg.dat ${FIRMWARE_PATH}/
    install -m 0644 ${DRIVER_PATH}/CORE/SVC/external/wlan_nlink_common.h \
        -D ${D}${includedir}/qcacld/wlan_nlink_common.h
}

FILES_${PN} += "${base_libdir}/firmware/wlan/*"
