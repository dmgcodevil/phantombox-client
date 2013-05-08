#ifndef MENU_H
#define MENU_H
#include "trainholder.h"
#include "console.h"

class Menu
{
public:
    Menu();
    void addTrain();
    void printTrains();
    void printTrains(QSet<Train> trains);
private:
    TrainHolder *trainHolder;
    Console * console;
};

#endif // MENU_H
