do_configure_linux-gnux32_prepend() {
    cp ${STAGING_INCDIR}/gnu/stubs-x32.h ${STAGING_INCDIR}/gnu/stubs-64.h
    cp ${STAGING_INCDIR}/bits/long-double-32.h ${STAGING_INCDIR}/bits/long-double-64.h
}

# Force 64-bit version
EXTRA_OEMAKE = "'ARCH=x86_64' 'CC=${CC}' 'AS=${AS}' 'LD=${LD}' 'AR=${AR}' \
                'RANLIB=${RANLIB}' 'OBJCOPY=${OBJCOPY}' 'PREFIX=${prefix}' 'LIBDIR=${libdir}' \
                "

# gnu-efi's Makefile treats prefix as toolchain prefix, so don't
# export it.
prefix[unexport] = "1"
