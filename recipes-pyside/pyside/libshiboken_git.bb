DESCRIPTION = "Shiboken is a plugin (front-end) for Generator Runner and a runtime library. It generates \
bindings for C++ libraries using CPython source code."

HOMEPAGE = "http://www.pyside.org"

BBCLASSEXTEND += "native"
DEPENDS = "apiextractor-native generatorrunner-native python-native libxslt qt4-native"
PR = "r0"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=34337af480a8c452bfafe22a78fa20cb"

SRC_URI = "git://gitorious.org/pyside/shiboken.git;protocol=git;tag=c4d13fee5e20a85a9325f30da0f7b08f5aa63bcd \
    file://MacroPushRequiredVars.cmake \
    file://FindQt4.cmake \
    file://rename-shiboken-pkg.patch \
    file://fix-shiboken-cmake-config.patch \
    "

S = "${WORKDIR}/git"
inherit cmake pkgconfig python-dir native

SRC_URI[md5sum] = "946e8988e5f4c4bd62e774407fa80fee"
SRC_URI[sha256sum] = "82c6c24dc55458ed047eba9fe700894a3347cd53462b21a97b7b5f9180b2a896"
QT_LIBINFIX = "E"
QT_DIR_NAME = "qtopia"

OE_CMAKE_AR = "${STAGING_BINDIR_TOOLCHAIN}/${AR}"
EXTRA_OECMAKE += " -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python-native/python2.7 \
    -DCMAKE_AR=${OE_CMAKE_AR} \
    -DQT_LIBINFIX=${QT_LIBINFIX} \
    -DQT_DIR_NAME=${QT_DIR_NAME} \
    -DQT_INCLUDE_DIR=${STAGING_INCDIR}/qtopia \
    -DQT_QTCORE_INCLUDE_DIR=${STAGING_INCDIR}/qtopia/QtCore \
    -DQT_QTGUI_INCLUDE_DIR=${STAGING_INCDIR}/qtopia/QtGui \
    -DQT_QTXML_INCLUDE_DIR=${STAGING_INCDIR}/qtopia/QtXML \
    -DQT_LIBRARY_DIR=${STAGING_LIBDIR} \
    -DSITE_PACKAGE=${STAGING_LIBDIR}/python2.7/site-packages \
    -DEPENDSYTHON_INCLUDE_DIR:PATH=${STAGING_INCDIR}/python2.7 \
    -DPYTHON_LIBRARIES:PATH=${STAGING_LIBDIR}/python2.7 \
    -DQT_LIBRARY_DIR=${STAGING_INCDIR} \
    -DQT_HEADERS_DIR=${STAGING_INCDIR}/qtopia/ \
    -DLIBXSLT_INCLUDE_DIR=${STAGING_INCDIR}/libxslt \
    -DLIBXSLT_LIBRARIES=${STAGING_LIBDIR}/libxslt.so \
    -DCMAKE_STAGING_DIR_NATIVE:PATH=${STAGING_DIR_NATIVE} \
    "

# The following exports are needed to let the cmake build configuration succeed without
# errors when detecting the correct python version
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

addtask do_fix_generator_names after do_patch before do_configure

do_fix_generator_names() {
    mv ${S}/generator/shiboken ${S}/generator/shiboken-src
    ln -s ${STAGING_BINDIR_NATIVE}/shiboken ${S}/generator/shiboken
}

FILES_${PN}-gdb += "${libdir}/python2.7/site-packages/.debug/shiboken*"
FILES_${PN}-dev += "${libdir}/cmake/ ${libdir}/pkgconfig"
FILES_${PN} += "${libdir}/python2.7/site-packages/shiboken*"
