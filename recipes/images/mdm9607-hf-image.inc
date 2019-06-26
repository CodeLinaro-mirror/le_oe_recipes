#Include the non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-hf', '')}"
require ${BASEMACHINE}-image.inc

