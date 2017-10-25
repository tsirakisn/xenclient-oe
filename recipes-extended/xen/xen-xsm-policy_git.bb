DESCRIPTION = "XSM Policy"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=4641e94ec96f98fabc56ff9cc48be14b"
DEPENDS += "checkpolicy-native"
PROVIDES = "xen-xsm-policy"

S = "${WORKDIR}/git"

PV = "${XEN_VERSION}+git${SRCPV}"

SRCREV = "${AUTOREV}"
#SRC_URI = "git://${OPENXT_GIT_MIRROR}/xsm-policy.git;protocol=${OPENXT_GIT_PROTOCOL};branch=${OPENXT_BRANCH}"
SRC_URI = "git://github.com/tklengyel/xsm-policy.git;protocol=${OPENXT_GIT_PROTOCOL};branch=move_to_usr"

FILES_${PN} += "/usr/share/xen/xenrefpolicy/policy/policy.24"

EXTRA_OEMAKE = " -j 1 "

do_compile(){
	oe_runmake DESTDIR=${D} BINDIR=${STAGING_BINDIR_NATIVE}
}

do_install(){
	mkdir -p ${D}/usr/share/xen/xenrefpolicy/users/
	touch ${D}/usr/share/xen/xenrefpolicy/users/system.users
	touch ${D}/usr/share/xen/xenrefpolicy/users/local.users
	oe_runmake DESTDIR=${D} BINDIR=${STAGING_BINDIR_NATIVE} install
	rm ${D}/usr/share/xen/xenrefpolicy/booleans || :
	rm -r ${D}/usr/share/xen/xenrefpolicy/users || :
	rm -r ${D}/usr/share/xen/xenrefpolicy/contexts || :
}

inherit xenclient
