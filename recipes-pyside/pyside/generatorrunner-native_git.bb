DESCRIPTION = "GeneratorRunner is a tool that eases the development of binding generators for C++ and Qt-based libraries by \
providing a framework to help automating most of the process. It uses the ApiExtractor library to parse the header files and \
manipulate the classes information while generating the binding code using front-end modules provided by the user."

BBCLASSEXTEND += "native"
HOMEPAGE = "http://www.pyside.org"
DEPENDS = "apiextractor-native"
PR = "r0"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=34337af480a8c452bfafe22a78fa20cb"

SRC_URI = "git://gitorious.org/pyside/generatorrunner.git;protocol=git;tag=29bfdca3d2fd6f87fc15f5e24fdeb532281855b2"
S = "${WORKDIR}/git"

SRC_URI[md5sum] = "946e8988e5f4c4bd62e774407fa80fee"
SRC_URI[sha256sum] = "82c6c24dc55458ed047eba9fe700894a3347cd53462b21a97b7b5f9180b2a896"

inherit cmake native
