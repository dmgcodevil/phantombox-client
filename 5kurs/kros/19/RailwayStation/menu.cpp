#include "menu.h"
#include "iostream"

Menu::Menu()
{
    trainHolder = new TrainHolder();
    console = new Console();
}

void Menu::addTrain(){
    Train train =  Train();
    train.setStart("bel");
    train.setDestination("USA");
    train.setNumber(100);
    trainHolder->addTrain(train);
}

void Menu::printTrains(){
    QString test = console->readLine("enter text: ");
    console->print("your text:", test);
    printTrains(*trainHolder->getTrains());
}

void Menu::printTrains(QSet<Train> trains){
QList<Train> list = trains.toList();
    QList<Train>::iterator i;
    for (i = list.begin(); i != list.end(); ++i){
     std::cout<< i->toString().toStdString().data() <<std::endl;
    }

}

