QT += xml network svg
TARGET = HelloMap
TEMPLATE = app 
INCLUDEPATH += ../../src/global \
               ../../src/location \
               ../../src/location/maps
SOURCES += main.cpp \
           mapwidget.cpp \
           mainwindow.cpp
           
HEADERS += mapwidget.h \
           mainwindow.h

CONFIG += mobility
MOBILITY = location

equals(QT_MAJOR_VERSION, 4):lessThan(QT_MINOR_VERSION, 7){
    MOBILITY += bearer
    INCLUDEPATH += ../../src/bearer
}

symbian: { 
    TARGET.CAPABILITY = Location \
                        NetworkServices \
                        ReadUserData \
                        WriteUserData
    ICON = icon.svg
}
