#Include the non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-2k', '')}"
require ${BASEMACHINE}-image.inc

