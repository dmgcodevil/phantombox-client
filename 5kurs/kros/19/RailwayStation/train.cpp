#include "train.h"

Train::Train()
{
}

QString Train::getStart(){
    return start;
}

void Train::setStart(QString start){
    this->start = start;
}

QString Train::getDestination(){
    return destination;
}

void Train::setDestination(QString destination){
    this->destination = destination;
}

int Train::getNumber(){
    return number;
}

void Train::setNumber(int number){
    this->number = number;
}
template<typename T> uint Train::qHash( const Train &key )
    {
       return ::qHash( "fuck" );
    }

QString Train::toString(){
    return QString("start=%1, destination=%2, number=%3")
            .arg(start)
            .arg(destination)
            .arg(number);
}
