#ifndef TRAINHOLDER_H
#define TRAINHOLDER_H
#include <QSet>
#include "train.h"

class TrainHolder
{
public:
    TrainHolder();
    QSet <Train> *getTrains();
    void addTrain(Train train);
    QSet<Train> *filterTrainsByNumber(int num);
    QSet<Train> *filterTrainsByDestination(QString destination);

private:
    QSet<Train> *trains;

};

#endif // TRAINHOLDER_H
