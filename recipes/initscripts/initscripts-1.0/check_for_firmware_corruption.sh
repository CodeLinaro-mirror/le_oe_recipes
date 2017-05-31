#!/bin/sh

# Copyright (c) 2017, The Linux Foundation. All rights reserved.
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

echo "Checking for firmware corruption"
for dir in `ls /sys/bus/msm_subsys/devices/`;do
	for file in `ls /sys/bus/msm_subsys/devices/$dir`;do
		if [[ $file == "error" ]]; then
			data=`cat /sys/bus/msm_subsys/devices/$dir/$file`
			if [[ $data == "firmware_error" ]];then
				mtd=`cat /proc/mtd | grep '"misc"'|cut -d ' ' -f1|sed "s/://g"`
				if [[ $mtd != null ]];then
				    flash_erase /dev/$mtd 0 1
				    if [ $? -eq 0  ];then
					pagesize=`mtd_debug info /dev/$mtd | grep "mtd.writesize" | sed 's/[^0-9]*//g'|sed 's/[0-9]$//'`
					if [[ $pagesize != null ]];then
					    nandwrite -p -s $pagesize /dev/$mtd /etc/init.d/cookie/cookie.txt
					    if [ $? -eq 0 ];then
						cookie=`dd if=/dev/$mtd count=3000 bs=2`
						cookie=`echo $cookie |sed 's/[^a-zA-Z0-9]//g'`
						if [[ $cookie == "bootrecovery" ]];then
						    echo "Subsystem $dir firmware is corrupt. Rebooting into recovery kernel"
						    reboot
						else
						    echo "Error: Incorrect cookie written to misc partition"
						fi
					    else
						echo "Error: nandwrite command failed"
					    fi
					else
						echo "Error: pagesize not retrieved"
					fi
				    else
					echo "Error: flash_erase command failed"
				    fi
				else
				    echo "Error: misc partition not found"
				fi
			fi
		fi
	done
done

