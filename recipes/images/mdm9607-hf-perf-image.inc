#Include the non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-hf-perf', '')}"
require ${BASEMACHINE}-image.inc

