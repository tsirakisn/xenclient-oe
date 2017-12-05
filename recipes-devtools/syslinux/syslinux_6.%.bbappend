PACKAGES =+ " \
    ${PN}-isohybrid \
    ${PN}-mboot \
    ${PN}-uefi \
"
do_install_append() {
   install -m 755 ${S}/bios/utils/isohybrid ${D}${bindir}/
   install -m 644 ${S}/efi64/efi/syslinux.efi ${D}${datadir}/${BPN}/BOOTX64.EFI
   install -m 644 ${S}/efi64/com32/elflink/ldlinux/ldlinux.e64 ${D}${datadir}/${BPN}/
   install -m 644 ${S}/efi64/mbr/isohdpfx.bin ${D}${datadir}/${BPN}/
}

# Since version 5.00, all Syslinux variants require an additional module,
# ldlinux, to be loaded too.
# (http://www.syslinux.org/wiki/index.php?title=Library_modules).
FILES_${PN} += " ${datadir}/${BPN}/ldlinux.c32"

FILES_${PN}-isohybrid = "${bindir}/isohybrid"

# mboot.c32 requires libcom32.c32 library
# (http://www.syslinux.org/wiki/index.php?title=Library_modules).
FILES_${PN}-mboot = " \
    ${datadir}/${BPN}/mboot.c32 \
    ${datadir}/${BPN}/libcom32.c32 \
"

FILES_${PN}-uefi += " \
    ${datadir}/${BPN}/BOOTX64.EFI \
    ${datadir}/${BPN}/ldlinux.e64 \
    ${datadir}/${BPN}/isohdpfx.bin \
"

INSANE_SKIP_${PN}-uefi = "arch"

