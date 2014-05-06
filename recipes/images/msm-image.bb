DESCRIPTION = "Minimal image for MSM devices"

# Open source packages
include recipes/images/${MACHINE}-image.inc
include recipes/images/msm-image.inc

inherit core-image
