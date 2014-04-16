# List of packages to be installed by msm-x11-image


IMAGE_INSTALL += "${POKY_BASE_INSTALL}"


IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_LINGUAS = ""

# Use busybox as login manager
IMAGE_LOGIN_MANAGER = "busybox-static"

# Include minimum init and init scripts
IMAGE_DEV_MANAGER = "busybox-static-mdev"
IMAGE_INIT_MANAGER = "sysvinit sysvinit-pidof"
IMAGE_INITSCRIPTS = ""

export IMAGE_BASENAME = "msm-x11-image"
