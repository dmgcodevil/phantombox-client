#include "console.h"
#include "iostream"
#include <sstream>
#include <QString>


Console::Console(){
}

void Console::print(char msg[]){
    std::cout << msg << std::endl;
}

void Console::print(QString msg){
    std::cout << msg.toStdString().data() << std::endl;
}

void Console::print(char msg1[], QString msg2){
    std::cout << msg1 << msg2.toStdString().data() << std::endl;
}

QString Console::readLine(){
    std::string line;
    getline (std::cin, line);
    return QString::fromStdString(line);
}

QString Console::readLine(char msg[]){
    print(msg);
    return readLine();
}
