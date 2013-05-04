#-------------------------------------------------
#
# Project created by QtCreator 2013-05-04T15:01:00
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = translator
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    library.cpp \
    fileutils.cpp

HEADERS  += mainwindow.h \
    library.h \
    fileutils.h

FORMS    += mainwindow.ui
#enable console
CONFIG += console
