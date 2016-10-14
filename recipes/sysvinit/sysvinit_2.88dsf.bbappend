FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += "\
           file://rcS-default \
           file://call_restorecon_from_init.patch \
"

do_install_append() {
  install -d ${D}/firmware
}

FILES_${PN} += "/firmware"
