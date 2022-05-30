include wpa-supplicant.inc

PR = "${INC_PR}.2"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://external/wpa_supplicant_8/"
SRC_URI += "file://defconfig-qcacld"

DEPENDS += "qmi"
DEPENDS += "qmi-framework"

S = "${WORKDIR}/external/wpa_supplicant_8/wpa_supplicant"

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig-qcacld .config
    echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> .config

	# wpa version string format like #define VERSION_STR "2.10-devel"
	WPA_VER_STR=$(sed -n 's/.*VERSION_STR\s\+\"\([0-9]\+\.[0-9]\+\)-devel.*/\1/p' ${WORKDIR}/external/wpa_supplicant_8/src/common/version.h)
	WPA_VER_MAJOR=$(echo $WPA_VER_STR | sed -n 's/\([0-9]\+\)\..*/\1/p')
	WPA_VER_MINOR=$(echo $WPA_VER_STR | sed -n 's/.*\.\([0-9]\+\)/\1/p')

	# wpa version >= 2.9 should support the wpa3, version >=2.10 should support wpa3r3
	if [ $WPA_VER_MAJOR -gt 2 ] || [[ $WPA_VER_MAJOR -eq 2 && $WPA_VER_MINOR -ge 9 ]]; then
        echo "CONFIG_OWE=y" >>.config
        echo "CONFIG_SAE=y" >>.config
	fi
}
