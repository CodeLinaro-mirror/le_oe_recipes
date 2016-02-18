FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += "file://CHANGES.patch \
            file://d1_both.patch \
            file://t1_lib.patch \
            "

PR = "r1"
