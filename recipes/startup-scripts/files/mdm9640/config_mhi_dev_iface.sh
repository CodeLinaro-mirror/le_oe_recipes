#!/bin/sh
# Copyright (c) 2017, The Linux Foundation. All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are
# met:
#     * Redistributions of source code must retain the above copyright
#	notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above
#	copyright notice, this list of conditions and the following
#	disclaimer in the documentation and/or other materials provided
#	with the distribution.
#     * Neither the name of The Linux Foundation nor the names of its
#	contributors may be used to endorse or promote products derived
#	from this software without specific prior written permission.
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

#constants
INTERFACE_PATH="/sys/class/net/"
BAR_REGISTER_PATH="/sys/module/ep_pcie_core/parameters/bar0_address"

#Timeout in seconds for interface to come up
TIMEOUT=300

#MHI Device Interface for SWIP netdev
NETDEV0_ifname="mhi_dev_net0"

#NAD IP# for swip interface
NAD0_mdmIP="192.168.1.4"
NAD1_mdmIP="192.168.1.5"
NAD2_mdmIP="192.168.1.6"

#NADs BAR0 register address
NAD0_BAR0_ref="238026756"
NAD1_BAR0_ref="239075332"
NAD2_BAR0_ref="240123908"

#check if interfaces is up
is_iface_init_done()
{
    local path
    path=$INTERFACE_PATH$NETDEV0_ifname
    if [ -d $path ] ; then
      echo "MHI dev net interface found"
    fi
}

#configure the interface
config( )
{
	local iface=$1
	local mdmIP=$2

	echo "up the interface with specific IP address"
	echo $iface $mdmIP
    ifconfig $iface $mdmIP "up"
}

#Check if the interface is up, if not sleep 1sec and recheck
/etc/init.d/config_mhi_dev_iface.sh > /dev/null 2>&1 &
timeout=0
while [ $timeout -lt $TIMEOUT ]; do
    if is_iface_init_done; then
	echo "interface is enabled"
	break
    fi
    ((timeout++))
    sleep 1
done
if [ $timeout -ge $TIMEOUT ]; then
    echo "Still interfaces is not up"
    exit 1
fi

#Identify Each NAD using BAR0 register

BAR0_NAD="$(cat $BAR_REGISTER_PATH)"

echo "$BAR0_NAD"

if [ "$BAR0_NAD" = "$NAD0_BAR0_ref" ]
then
   config $NETDEV0_ifname $NAD0_mdmIP
   echo "BAR0 matches with NAD0"
elif [ "$BAR0_NAD" = "$NAD1_BAR0_ref" ]
then
	config $NETDEV0_ifname $NAD1_mdmIP
	echo "BAR0 matches NAD1"
elif [ "$BAR0_NAD" = "$NAD2_BAR0_ref" ]
then
	config $NETDEV0_ifname $NAD2_mdmIP
	echo "BAR0 matches with NAD2"
else
	echo "BAR0 register doesnt match with NADs"
fi
