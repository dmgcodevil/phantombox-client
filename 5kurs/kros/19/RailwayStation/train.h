#ifndef TRAIN_H
#define TRAIN_H
#include <QString>
#include <QHash>
class Train
{
public:
    Train();
    QString getStart() const;
    void setStart(QString start);    
    QString getDestination() const;
    void setDestination(QString destination);
    int getNumber() const;
    void setNumber(int number);
    QString toString();
private:
   QString start;
   QString destination;
   int number;
};

inline bool operator<(const Train &left, const Train &right){
      return  left.getNumber()  <  right.getNumber();
}

inline bool operator==(const Train &left, const Train &right ){
    return left.getDestination() == right.getDestination()
            && left.getNumber() == right.getNumber()
            && left.getStart() == right.getStart();
}

inline uint qHash( const Train &key ){
    return qHash(key.getNumber()) ^ qHash(key.getStart());
}

#endif // TRAIN_H

