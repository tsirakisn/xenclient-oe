SUMMARY = "64-bit shim"
DESCRIPTION = "shim is a trivial EFI application that, when run, \
attempts to open and execute another application. It will initially \
attempt to do this via the standard EFI LoadImage() and StartImage() \
calls. If these fail (because secure boot is enabled and the binary \
is not signed with an appropriate key, for instance) it will then \
validate the binary against a built-in certificate. If this succeeds \
and if the binary or signing key are not blacklisted then shim will \
relocate and execute the binary."
HOMEPAGE = "https://github.com/tklengyel/shim/shim.git"
SECTION = "bootloaders"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=b92e63892681ee4e8d27e7a7e87ef2bc"

DEPENDS_${PN} += "\
    gnu-efi-native openssl util-linux-native openssl-native \
"

PV = "13+git${SRCPV}"

SRC_URI = "git://github.com/tklengyel/shim;branch=keep_reloc"


SRCREV = "${AUTOREV}"

PACKAGE_ARCH = "x86_64"

S = "${WORKDIR}/git"

# inherit deploy user-key-store

EXTRA_OEMAKE = "\
    CROSS_COMPILE="${TARGET_PREFIX}" \
    prefix="${STAGING_DIR_HOST}/${prefix}" \
    LIB_GCC="`${CC} -print-libgcc-file-name`" \
    LIB_PATH="${STAGING_LIBDIR_NATIVE}" \
    EFI_PATH="${STAGING_LIBDIR_NATIVE}" \
    EFI_INCLUDE="${STAGING_INCDIR_NATIVE}/efi" \
    RELEASE="_${DISTRO}_${DISTRO_VERSION}" \
    DEFAULT_LOADER=\\\\\\xen-signed.efi \
    OPENSSL=${STAGING_BINDIR_NATIVE}/openssl \
    HEXDUMP=${STAGING_BINDIR_NATIVE}/hexdump \
    PK12UTIL=${STAGING_BINDIR_NATIVE}/pk12util \
    CERTUTIL=${STAGING_BINDIR_NATIVE}/certutil \
    AR=${AR} \
    ARCH=x86_64 \
    KEEP_DISCARDABLE_RELOC=1 \
"

#    ${@'VENDOR_CERT_FILE=${WORKDIR}/vendor_cert.cer' \
#       if d.getVar('MOK_SB', True) == '1' else ''} \
#    ${@'VENDOR_DBX_FILE=${WORKDIR}/vendor_dbx.esl' \
#       if uks_signing_model(d) == 'user' else ''} \
#    SBSIGN=${STAGING_BINDIR_NATIVE}/sbsign \
#    ENABLE_HTTPBOOT=1 \
#    ENABLE_SBSIGN=1 \
#

EXTRA_OEMAKE_append_x86-64 = " OVERRIDE_SECURITY_POLICY=1"

PARALLEL_MAKE = ""
COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

EFI_TARGET = "/boot/efi/EFI/BOOT"

MSFT = "${@bb.utils.contains('DISTRO_FEATURES', 'msft', '1', '0', d)}"

do_install() {
    install -d "${D}${EFI_TARGET}"

    local shim_dst="${D}${EFI_TARGET}/bootx64.efi"
    if [ x"${UEFI_SB}" = x"1" ]; then
        install -m 0600 "${B}/shimx64.efi.signed" "$shim_dst"
    else
        install -m 0600 "${B}/shimx64.efi" "$shim_dst"
    fi
}

FILES_${PN} += "${EFI_TARGET}"
BBCLASSEXTEND="native"
