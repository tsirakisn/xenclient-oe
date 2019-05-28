SUMMARY = "Test server/client for testing argo comms"
LICENSE="GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/"

SRC_URI = "git:///home/crogers/git/testsrv;protocol=file;branch=master \
        "

FILES_${PN} += " \
    ${bindir}/test_srv \
    ${bindir}/test_cli \
    ${datadir}/input_txt \
"

do_install() {
    oe_runmake DESTDIR=${D} install

    install -m 0755 -d ${D}/usr/share/testsrv
    install -m 0644 ${S}/input_txt ${D}/usr/share/testsrv/
}
