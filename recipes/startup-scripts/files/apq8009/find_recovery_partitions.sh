#!/bin/sh
# Copyright (c) 2016, The Linux Foundation. All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are
# met:
#     * Redistributions of source code must retain the above copyright
#       notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above
#       copyright notice, this list of conditions and the following
#       disclaimer in the documentation and/or other materials provided
#       with the distribution.
#     * Neither the name of The Linux Foundation nor the names of its
#       contributors may be used to endorse or promote products derived
#       from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
# WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
# MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
# ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
# BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
# BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
# WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
# OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
# IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#
# find_recovery_partitions        init.d script to dynamically find partitions used in recovery
#

DUMP_TO_KMSG=/dev/kmsg

UpdateRecoveryVolume () {
   partition=$1
   dir=$2
   fstype=$3
   echo "EMMC : Update Recovery Volume for Partition :  $partition , Directory : $dir, fstype : $fstype and mmc_block_device : $mmc_block_device" > $DUMP_TO_KMSG
   echo /dev/$mmc_block_device       $dir     $fstype     defaults    0   0 >> /res/recovery_volume_config
}

FindAndMountEXT4 () {
   partition=$1
   dir=$2
   fstab_only="$3"
   mmc_block_device=/dev/block/bootdevice/by-name/$partition
   echo "EMMC : Detected block device : $dir for $partition" > $DUMP_TO_KMSG
   mkdir -p $dir
   if [ "$fstab_only" != "1" ]; then
      mount -t ext4 $mmc_block_device $dir -o relatime,data=ordered,noauto_da_alloc,discard
      echo "EMMC : Mounting of $mmc_block_device on $dir done"  > $DUMP_TO_KMSG
   fi
   UpdateRecoveryVolume $1 $2 "ext4" $mmc_block_device
}

FindAndMountEXT4 system     /system  1
FindAndMountEXT4 userdata  /usr  1
FindAndMountEXT4 cache    /cache
FindAndMountEXT4 modem   /firmware

exit
