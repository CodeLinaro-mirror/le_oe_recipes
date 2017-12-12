#Include the non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-2k-perf', '')}"
require ${BASEMACHINE}-image.inc
