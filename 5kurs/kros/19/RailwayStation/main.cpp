#include <QCoreApplication>
#include <QString>
#include "train.h"
#include "menu.h"
#include "iostream"
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Menu *menu = new Menu();
    menu->addTrain();
    menu->printTrains();

    return a.exec();
}
