#include "menu.h"
#include "iostream"

Menu::Menu()
{
    trainHolder = new TrainHolder();
}

void Menu::addTrain(){
    Train train =  Train();
    train.setStart("bel");
    train.setDestination("USA");
    train.setNumber(100);
    trainHolder->addTrain(train);
}

void Menu::printTrains(){

    printTrains(*trainHolder->getTrains());


}

void Menu::printTrains(QSet<Train> trains){
QList<Train> list = trains.toList();
    QList<Train>::iterator i;
    for (i = list.begin(); i != list.end(); ++i){
     std::cout<< i->toString().toStdString().data() <<std::endl;
    }

}

