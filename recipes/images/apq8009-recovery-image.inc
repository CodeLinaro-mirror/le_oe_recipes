# The mkbootimg production process.
require mdm-bootimg.inc

RECOVERY_IMAGE_ROOTFS_SIZE = "25000000"

DEPENDS += "bzip2 fsconfig-native"

# The OE-Core minimal boot image files.
IMAGE_INSTALL += "packagegroup-core-boot"
IMAGE_INSTALL += "${ROOTFS_PKGMANAGE_BOOTSTRAP}"
IMAGE_INSTALL += "${CORE_IMAGE_EXTRA_INSTALL}"

# The MSM Linux minimal boot image files.
IMAGE_INSTALL += "base-files"
IMAGE_INSTALL += "base-passwd"
IMAGE_INSTALL += "system-core-adbd"
IMAGE_INSTALL += "system-core-usb"
IMAGE_INSTALL += "recovery"
IMAGE_INSTALL += "start-scripts-find-recovery-partitions"
IMAGE_INSTALL += "start-scripts-firmware-links"

# Below is to generate sparse ext4 recovery image (OE by default supports raw ext4 images)

do_makesystem() {
	make_ext4fs -s -l ${RECOVERY_IMAGE_ROOTFS_SIZE} ${DEPLOY_DIR_IMAGE}/mdm-recovery-image-${MACHINE}.ext4 ${IMAGE_ROOTFS}
}
