DESCRIPTION = "HAL libraries for camera"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"
PV = "1.0.0"
PR = "r8"

SRC_URI = "file://${WORKSPACE}/camera-hal \
           file://camera_parameters_header_include_patch.txt \
           file://dlog-replace-utils-patch.txt \
	       file://string_fix_patch.txt"

S = "${WORKDIR}/camera-hal"

inherit autotools

# Need the kernel headers
DEPENDS += "virtual/kernel"
DEPENDS += "mm-camera"
DEPENDS += "mm-still"
#DEPENDS += "utils-lib"
DEPENDS += "mm-image-codec"
DEPENDS += "dlog"
DEPENDS += "lua"

PACKAGE_ARCH = "${MACHINE_ARCH}"

#re-use non-perf settings
BASEMACHINE = "${@d.getVar('MACHINE', True).replace('-perf', '')}"

ARM_INSTRUCTION_SET = "arm"

CFLAGS += "-I${STAGING_INCDIR}"
CFLAGS += "-I${STAGING_INCDIR}/jpeg/inc"
CFLAGS += "-I${STAGING_INCDIR}/cameracommon"
CFLAGS += "-I${STAGING_KERNEL_DIR}/usr/include"
CFLAGS += "-I${STAGING_KERNEL_DIR}/usr/include/media"

EXTRA_OECONF += " --enable-debug=no --with-dlog --enable-target=${BASEMACHINE} \
                 --with-sanitized-headers=${STAGING_KERNEL_DIR}/usr/include"

EXTRA_OECONF_append_a-family = " --with-additional-include-directives="-I${WORKSPACE}/mm-video-oss/mm-core/inc \
                                 -I${WORKSPACE}/mm-still/omx/inc/""
EXTRA_OECONF_append_b-family = " --with-additional-include-directives="${WORKSPACE}/mm-video-oss/mm-core/inc \
                                 -I${WORKSPACE}/hardware/libhardware/include -I${WORKSPACE}/base/include \
                                  -I${WORKSPACE}/mm-video-oss/libstagefrighthw""

CPPFLAGS += "-I${STAGING_INCDIR}/c++ \
             -I${STAGING_INCDIR}/c++/${TARGET_SYS}"

FILES_${PN}_append_a-family += "/usr/lib/hw/*"
FILES_${PN}_append_b-family += "/usr/lib/*"

# The camera-hal package contains symlinks that trip up insane
INSANE_SKIP_${PN} = "dev-so"


do_configure_prepend() {

    mkdir -p ${STAGING_INCDIR}/camera/

    wget --no-check-certificate -O ${S}/QCamera2/HAL/CameraParameters.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/av/plain/include/camera/CameraParameters.h?h=jb_3.2_rb5.39

    wget --no-check-certificate -O ${S}/QCamera2/HAL/CameraParameters.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/av/plain/camera/CameraParameters.cpp?h=jb_3.2_rb5.39

    wget --no-check-certificate -O ${S}/QCamera2/HAL/Mutex.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/native/plain/include/utils/Mutex.h?h=jb_3.2_rb5.39

    mkdir -p ${S}/QCamera2/HAL/utils/
    mkdir -p ${S}/QCamera2/HAL/private/utils/

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Errors.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Errors.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/private/utils/Static.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/private/utils/Static.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/List.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/List.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/KeyedVector.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/KeyedVector.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/SortedVector.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/SortedVector.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Vector.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Vector.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/VectorImpl.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/VectorImpl.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/TypeHelpers.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/TypeHelpers.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/String8.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/String8.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/String16.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/String16.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/SharedBuffer.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/SharedBuffer.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/RefBase.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/RefBase.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Timers.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Timers.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Atomic.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Atomic.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/StringArray.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/StringArray.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/SystemClock.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/SystemClock.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Log.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Log.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Debug.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Debug.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/CallStack.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/CallStack.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/TextOutput.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/TextOutput.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/StopWatch.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/StopWatch.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/TextOutput.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/TextOutput.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/TextOutput.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/TextOutput.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/threads.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/threads.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/misc.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/misc.h?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/HAL/utils/Endian.h https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/include/utils/Endian.h?h=tizen_0.1


    mkdir -p ${S}/QCamera2/util/

    wget --no-check-certificate -O ${S}/QCamera2/util/RefBase.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/RefBase.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/SharedBuffer.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/SharedBuffer.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/StopWatch.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/StopWatch.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/String8.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/String8.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/String16.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/String16.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/StringArray.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/StringArray.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/SystemClock.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/SystemClock.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/Timers.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/Timers.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/VectorImpl.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/VectorImpl.cpp?h=tizen_0.1

    wget --no-check-certificate -O ${S}/QCamera2/util/misc.cpp https://www.codeaurora.org/cgit/quic/la/platform/frameworks/base/plain/libs/utils/misc.cpp?h=tizen_0.1


    # Apply patch for newly imported cameraparameters.cpp file
    pushd ${S}/..
    patch -p0 < camera_parameters_header_include_patch.txt
    popd

    # Apply patch to string class
    pushd ${S}/QCamera2/
    patch -p0 < ../../string_fix_patch.txt
    popd

    # copy additional system header files
    mkdir -p ${STAGING_INCDIR}/system
    wget --no-check-certificate -O ${STAGING_INCDIR}/system/camera.h https://www.codeaurora.org/cgit/external/gigabyte/platform/system/core/plain/include/system/camera.h?h=caf/jb_3.2_rb5.39
    wget --no-check-certificate -O ${STAGING_INCDIR}/system/window.h https://www.codeaurora.org/cgit/external/gigabyte/platform/system/core/plain/include/system/window.h?h=caf/jb_3.2_rb5.39
    wget --no-check-certificate -O ${STAGING_INCDIR}/system/graphics.h https://www.codeaurora.org/cgit/external/gigabyte/platform/system/core/plain/include/system/graphics.h?h=caf/jb_3.2_rb5.39
    mkdir -p ${STAGING_INCDIR}/sync
    wget --no-check-certificate -O ${STAGING_INCDIR}/sync/sync.h https://www.codeaurora.org/cgit/external/gigabyte/platform/system/core/plain/include/sync/sync.h?h=caf/jb_3.2_rb5.39

    # patch utils lib to replace log with dlog
    pushd ${S}/..
    patch -p0 < dlog-replace-utils-patch.txt
    popd
}

do_clean_extra () {
    #do clean up the patch
    pushd ${WORKSPACE}/base
    git reset --hard
    popd
}

addtask do_clean_extra before do_clean

do_install_append_msm8960() {
   mkdir -p ${D}/usr/lib/hw

   # Move and rename libcamera.so files to hw/machine-specific names.
   cp ${D}/usr/lib/libcamera.so.0.0.0 ${D}/usr/lib/hw/libcamera.so

   pushd ${D}/usr/lib/hw
   ln -s libcamera.so ./camera.msm8960.so
   popd
}
