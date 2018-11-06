SUMMARY = "Recipe file to create users and groups using useradd"
DESCRIPTION = "This recipe creates all the users/groups required by the system"
PR = "r1"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

S = "${WORKDIR}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit useradd
USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 1000 -s /bin/sh -U system"

do_install() {
    mkdir -p ${D}${datadir}/users
    install -d -m 755 ${D}${datadir}/users/system
}

FILES_${PN} = "${datadir}/users/*"
