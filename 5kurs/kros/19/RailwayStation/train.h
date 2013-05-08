#ifndef TRAIN_H
#define TRAIN_H
#include <QString>
#include <QHash>
class Train
{
public:
    Train();
    // setters-getters
    QString getStart();
    void setStart(QString start);
    QString getDestination();
    void setDestination(QString destination);
    int getNumber();
    void setNumber(int number);
    QString toString();
   inline bool operator==( const Train &a   )const
    {
      return true;
    }

   template<typename T> uint qHash( const Train &key );


private:
   QString start;
   QString destination;
   int number;
};

#endif // TRAIN_H
