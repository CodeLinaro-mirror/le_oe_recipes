require recipes-devtools/git/git.inc
inherit native
SRC_URI[md5sum] = "e16c14b27c644b8e0dd72bdb5ff77450"
SRC_URI[sha256sum] = "de3097fdc36d624ea6cf4bb853402fde781acdb860f12152c5eb879777389882"
SRC_URI = "https://git-core.googlecode.com/files/${BP}.tar.gz"
PR = "r0"
EXTRA_OECONF_append = " --without-python"
EXTRA_OEMAKE = "NO_TCLTK=1"
