#include "trainholder.h"

TrainHolder::TrainHolder(){
    trains = new QSet<Train>;
}

QSet <Train> *TrainHolder::getTrains(){
    return trains;
}

void TrainHolder::addTrain(Train train){
   trains->insert(train);
}

QSet<Train> *TrainHolder::filterTrainsByNumber(int num){
      return trains;
}

QSet<Train> *TrainHolder::filterTrainsByDestination(QString destination){
     return trains;
}
