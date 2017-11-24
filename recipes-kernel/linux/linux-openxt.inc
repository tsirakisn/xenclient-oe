inherit xenclient

DEPENDS += "bc-native"
DEPENDS_xenclient-dom0 += "xenclient-initramfs-image"

KERNEL_IMAGETYPE = "bzImage"

S = "${WORKDIR}/linux-${PV}"

# Don't use update-alternatives for kernel at all. 
pkg_postinst_kernel-image () {
    mv $D/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} $D/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
}

pkg_postrm_kernel-image () {
    rm -f $D/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
}

# Copy initramfs and set cmdline options that will get baked into dom0 kernel here
# (whatever is set in the defconfig will get overwritten)
do_configure_prepend_xenclient-dom0() {
    cp ${DEPLOY_DIR_IMAGE}/xenclient-initramfs-image-xenclient-dom0.cpio.gz ${B}
    CMDLINE="console=hvc0 root=/dev/mapper/xenclient-root ro boot=/dev/mapper/xenclient-boot swiotlb=16384 xen_pciback.passthrough=1 consoleblank=0 video.delay_init=1 vt.global_cursor_default=0 rootfstype=ext3 bootfstype=ext3"
}
