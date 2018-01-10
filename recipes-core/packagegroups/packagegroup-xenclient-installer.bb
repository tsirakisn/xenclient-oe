DESCRIPTION = "All packages required for XenClient installer"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6      \
                    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r11"

inherit packagegroup

RDEPENDS_${PN} = " \
    kernel-modules \
    openssh \
    openssh-sshd-tcp-init \
    util-linux-mount \
    util-linux-umount \
    xen-blktap \
    xen-console \
    xen-hypervisor \
    xen-efi \
    xen-flask-tools \
    xen-libblktapctl \
    xen-libvhd \
    xen-libxenctrl \
    xen-libxenguest \
    xen-libxenlight \
    xen-libxenstat \
    xen-libxlutil \
    xen-xenstat \
    xen-xl \
    dialog \
    e2fsprogs \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    eject \
    lvm2 \
    util-linux-sfdisk \
    xenclient-installer \
    xenclient-installer-tweaks \
    cryptsetup \
    xenclient-get-config-key \
    falloc \
    openssl \
    xenclient-toolstack \
    read-edid \
    pciutils-ids \
    dosfstools \
    syslinux \
    syslinux-isolinux \
    syslinux-mboot \
    syslinux-pxelinux \
    ethtool \
    rsyslog \
    xenclient-udev-force-discreet-net-to-eth0 \
    bc \
    wget \
    refpolicy-mcs \
    dmidecode \
    netcat \
    tboot \
    tboot-utils \
    acms \
    trousers \
    trousers-data \
    tpm-tools \
    xenclient-pcrdiff \
    xenclient-tpm-scripts \
    openxt-keymanagement \
    openxt-measuredlaunch \
    ncurses \
    intel-microcode \
    libtss2 \
    libtctidevice \
    libtctisocket \
    tpm2-tools \
    efibootmgr \
    shim \
    gptfdisk \
    parted \
"

# open-iscsi-user \
# 
