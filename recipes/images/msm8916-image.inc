# The mkbootimg production process.
require msm-x11-image.inc

# Force a few additional dependencies in the mix so that we get the needed
# recipes to build in the right order so we can make the bootimg file and
# our yaffs2 image...
DEPENDS = 	" \
		virtual/kernel \
		virtual/bootloader \
		pkgconfig-native \
		gtk-doc-native \
		gettext-native \
		mkbootimg-native \
		dtbtool-native \
		"

# Image output types
IMAGE_FSTYPES = "ext4"

# The OE-Core minimal boot image files.
IMAGE_INSTALL += "task-core-boot"
IMAGE_INSTALL += "${ROOTFS_PKGMANAGE_BOOTSTRAP}"
IMAGE_INSTALL += "${POKY_EXTRA_INSTALL}"

# The MSM Linux minimal boot image files.
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "base-files"
IMAGE_INSTALL += "base-passwd"
IMAGE_INSTALL += "powerapp"
IMAGE_INSTALL += "powerapp-powerconfig"
IMAGE_INSTALL += "powerapp-reboot"
IMAGE_INSTALL += "powerapp-shutdown"
IMAGE_INSTALL += "reboot-daemon"
IMAGE_INSTALL += "system-core-adbd"
IMAGE_INSTALL += "system-core-usb"
IMAGE_INSTALL += "system-core-liblog"
IMAGE_INSTALL += "system-core-libcutils"
#IMAGE_INSTALL += "start-scripts-backlight"
IMAGE_INSTALL += "start-scripts-firmware-links"
IMAGE_INSTALL += "alsa-utils-amixer"
IMAGE_INSTALL += "alsa-utils-aplay"
IMAGE_INSTALL += "alsa-utils-speakertest"
IMAGE_INSTALL += "alsa-utils-midi"
IMAGE_INSTALL += "udev"
IMAGE_INSTALL += "libstdc++"
IMAGE_INSTALL += "mm-core-oss"
IMAGE_INSTALL += "mm-video-oss"
#IMAGE_INSTALL += "camera-hal"
IMAGE_INSTALL += "open-source-kernel-tests"
IMAGE_INSTALL += "libtbm-msm"
IMAGE_INSTALL += "libgenlock"
#IMAGE_INSTALL += "mm-image-codec"
IMAGE_INSTALL += "init-audio"

# Convenience: build a compatible GDB and install gdbserver on the image
DEPENDS += "gdb-cross"
IMAGE_INSTALL += "gdbserver"
IMAGE_INSTALL += "strace"
#IMAGE_INSTALL += "loc-api"
#IMAGE_INSTALL += "msm7k"

IMAGE_PREPROCESS_COMMAND += "mv ${IMAGE_ROOTFS}/lib/modules ${IMAGE_ROOTFS}/usr/lib;"

IMAGE_PREPROCESS_COMMAND += "mkdir -p ${IMAGE_ROOTFS}/cache;"
IMAGE_PREPROCESS_COMMAND += "echo ro.build.version.release=`cat ${IMAGE_ROOTFS}/etc/version ` >> ${IMAGE_ROOTFS}/build.prop;"

do_rootfs_append () {

    mkdir -p ${DEPLOY_DIR_IMAGE}
    cp ${DEPLOY_DIR_IMAGE}/${MACHINE}-boot.img ${DEPLOY_DIR_IMAGE}/${PN}-boot-${MACHINE}.img
}

