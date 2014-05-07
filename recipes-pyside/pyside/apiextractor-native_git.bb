DESCRIPTION = "API Extractor is a tool that eases the development of bindings \
of Qt-based libraries for high level languages by automating most of the process."
HOMEPAGE = "http://www.pyside.org"
PR = "r0"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=34337af480a8c452bfafe22a78fa20cb"

SRC_URI = "git://gitorious.org/pyside/apiextractor.git;protocol=git;tag=25b23b4f039650c6e5d64592e6e7210719e52097"
S = "${WORKDIR}/git"

SRC_URI[md5sum] = "946e8988e5f4c4bd62e774407fa80fee"
SRC_URI[sha256sum] = "82c6c24dc55458ed047eba9fe700894a3347cd53462b21a97b7b5f9180b2a896"

inherit cmake native
