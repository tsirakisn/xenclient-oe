EXTRA_OECONF_append=" --target=x86_64"

GRUB_BUILDIN = "all_video boot btrfs cat chain configfile echo \
                efifwsetup efinet ext2 fat font gfxmenu gfxterm gzio halt \
                hfsplus iso9660 jpeg loadenv loopback lvm mdraid09 mdraid1x \
                minicmd normal part_apple part_msdos part_gpt \
                password_pbkdf2 png \
                reboot search search_fs_uuid search_fs_file search_label \
                serial sleep syslinuxcfg test tftp video xfs \
                linux backtrace usb usbserial_common \
                usbserial_pl2303 usbserial_ftdi usbserial_usbdebug \
		multiboot multiboot2"

do_deploy() {
        # Search for the grub.cfg on the local boot media by using the
        # built in cfg file provided via this recipe
        grub-mkimage -c ../cfg -p /EFI/BOOT -d ./grub-core/ \
                       -O x86_64-efi -o ./bootx64.efi \
                       ${GRUB_BUILDIN}
        install -m 644 ${B}/bootx64.efi ${DEPLOYDIR}
}

INSANE_SKIP_${PN}_append = " arch"
INSANE_SKIP_${PN}-dbg_append = " arch"
