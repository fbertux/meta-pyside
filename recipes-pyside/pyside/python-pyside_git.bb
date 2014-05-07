QTV = "4.8"
DESCRIPTION = "Python Bindings for Qt ${QTV}"
LICENSE = "LGPLv2.1 | GPLv3"

BBCLASSEXTEND = "native"
DEPENDS = "apiextractor-native generatorrunner-native shiboken-native libshiboken"
RDEPENDS_${pn} = "python-core"
PROVIDES = "python-pyside"
INC_PR = "r2"
inherit qt4e

RDEPENDS_${PN} = " \
 python-lang \
"

SRC_URI = "git://gitorious.org/pyside/pyside.git;protocol=git;tag=8dfeddb3a679d2f6fdb1ae01f8eee77c9bec7edd \
    file://support-qws.patch \
    file://MacroPushRequiredVars.cmake \
    file://FindQt4.cmake \
    file://no-accessibility-support.patch \
    "

# NOTE this should be reworked when a x11 version of qt4 is needed
inherit cmake qt4e

PYTHON_DIR="python2.7"

OECMAKE_CXX_FLAGS_append=" -DQ_WS_QWS "
CXX_DEFINES+=" -DQ_WS_QWS "
QT_LIBINFIX="E"

export CXX_DEFINES

#linux-arm-gnueabi-g++
OE_CMAKE_AR = "${STAGING_BINDIR_TOOLCHAIN}/${AR}"
EXTRA_OECMAKE += " \
    -DCMAKE_AR=${OE_CMAKE_AR} \
    -DQT_LIBINFIX=${QT_LIBINFIX} \
    -DQT_MKSPECS_DIR=${STAGING_DATADIR}/qtopia/mkspecs/qws/linux-arm-gnueabi-g++/ \
    -DQT_INCLUDE_DIR:PATH=${STAGING_INCDIR}/qtopia/ \
    -DQT_HEADERS_DIR:PATH=${STAGING_INCDIR}/qtopia \
    -DQT_QTCORE_INCLUDE_DIR:PATH=${STAGING_INCDIR}/qtopia/QtCore \
    -DQT_QTCORE_LIBRARY:FILE=${STAGING_LIBDIR}/libQtCoreE.so \
    -DQT_QTCORE_LIBRARY_RELEASE:FILE=${STAGING_LIBDIR}/libQtCoreE.so \
    -DSITE_PACKAGE=${STAGING_LIBDIR}/python2.7/site-packages \
    -DPYTHON_INCLUDE_DIR:PATH=${STAGING_INCDIR}/python2.7 \
    -DPYTHON_LIBRARIES:PATH=${STAGING_LIBDIR}/python2.7 \
    -DQT_HEADERS_DIR=${STAGING_INCDIR}/qtopia \
    -DSHIBOKEN_INCLUDE_DIR:PATH=${STAGING_INCDIR}/shiboken \
    -DCMAKE_LIBRARY_PATH=${STAGING_LIBDIR} \
"
do_configure_append() {
    find ${S}/PySide -name cmake_install.cmake | xargs sed -i "s:${STAGING_LIBDIR}/python2.7/site-packages/PySide:${libdir}/${PYTHON_DIR}/site-packages/PySide:g"
}

FILES_${PN} =+ " \
    ${libdir}/${PYTHON_DIR}/site-packages/PySide/*.so \
    ${libdir}/${PYTHON_DIR}/site-packages/PySide/*.py \
"
FILES_${PN}-dbg =+ "${libdir}/${PYTHON_DIR}/site-packages/PySide/.debug"

FILES_${PN}-dev =+ " \
   ${datadir}/PySide/typesystems \
   ${libdir}/cmake \
"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19"

S = "${WORKDIR}/git"

SRC_URI[md5sum] = "946e8988e5f4c4bd62e774407fa80fee"
SRC_URI[sha256sum] = "82c6c24dc55458ed047eba9fe700894a3347cd53462b21a97b7b5f9180b2a896"
