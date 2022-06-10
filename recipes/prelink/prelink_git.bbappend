SRC_URI = "${CLO_LE_GIT}/platform/external/prelink-cross;protocol=https"

SRC_URI +="file://prelink.conf \
           file://prelink.cron.daily \
           file://prelink.default \
           file://macros.prelink"

PR = "r1"
