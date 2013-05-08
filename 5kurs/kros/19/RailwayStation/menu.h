#ifndef MENU_H
#define MENU_H
#include "trainholder.h"

class Menu
{
public:
    Menu();
    void addTrain();
    void printTrains();
    void printTrains(QList<Train> &trains);
    void MyMessageOutput(QtMsgType Type, const QMessageLogContext& Context, const QString &Message);
private:
    TrainHolder *trainHolder;
//    void addTrain();
//    void printTrains();
//    void printTrains(QSet<Train *> *trains);
};

#endif // MENU_H
