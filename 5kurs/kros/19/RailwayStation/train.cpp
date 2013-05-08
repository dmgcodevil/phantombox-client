#include "train.h"

Train::Train()
{
}

QString Train::getStart() const {
    return start;
}

void Train::setStart(QString start){
    this->start = start;
}

QString Train::getDestination() const {
    return destination;
}

void Train::setDestination(QString destination) {
    this->destination = destination;
}

int Train::getNumber() const {
    return number;
}

void Train::setNumber(int number) {
    this->number = number;
}

QString Train::toString(){
    return QString("start=%1, destination=%2, number=%3")
            .arg(start)
            .arg(destination)
            .arg(number);
}

