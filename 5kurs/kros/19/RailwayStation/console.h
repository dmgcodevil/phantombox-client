#ifndef CONSOLE_H
#define CONSOLE_H
#include "console.h"
#include "iostream"
#include <QString>

class Console
{
public:
    Console();
    void print(char msg[]);
    void print(QString msg);
    void print(char msg1[], QString msg2);
    QString readLine();
    QString readLine(char msg[]);

};

#endif // CONSOLE_H
