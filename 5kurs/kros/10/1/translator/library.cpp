#include "library.h"
#include <QApplication>
#include <QWidget>
#include <QHBoxLayout>
#include <QListWidget>
#include <QListWidgetItem>
#include <QFile>
#include <QTextStream>

/**
 * @brief Library::Library
 */
Library::Library()
{
    translationMap = new QMap<QString, QString>;
}

/**
 * @brief Library::clear
 */
void Library::clear(){

    translationMap->clear();
}

/**
 * @brief Library::add
 * @param source
 * @param translation
 */
void Library::add(QString source, QString translation){
    translationMap->insert(source, translation);
}

/**
 * @brief Library::remove
 * @param source
 */
void Library::remove(QString source){
    translationMap->remove(source);
}


/**
 * @brief Library::getTranslationMap
 * @return
 */
QMap<QString, QString> *Library::getTranslationMap(){
    return translationMap;
}

/**
 * @brief Library::getName
 * @return
 */
QString Library::getName(){
    return name;
}

/**
 * @brief Library::setName
 * @param name
 */
void Library::setName(QString name){
    this->name = QString(name);
}

/**
 * @brief Library::getPath
 * @return
 */
QString Library::getPath(){
    return path;
}

/**
 * @brief Library::setPath
 * @param path
 */
void Library::setPath(QString path){
    this->path = path;
}

/**
 * @brief Library::find
 * @param source
 * @return
 */
QString Library::find(QString source){
   return translationMap->find(source).value();
}

/**
 * @brief Library::printTranslationMap
 * @param listWidget
 */
void Library::printTranslationMap(QListWidget *listWidget){
    printTranslationMap(listWidget, translationMap);
 }

/**
 * @brief Library::save
 * @param fileName
 */
void Library::save(QString fileName) {

    QFile fOut(fileName);
     if (fOut.open(QFile::WriteOnly | QFile::Text)) {
       QTextStream s(&fOut);
       QMapIterator<QString, QString> i(*translationMap);
       while (i.hasNext()) {
           i.next();
           s << i.key() + "=" + i.value() << '\n';
       }

     } else {
        return;
     }
     fOut.close();
}

void Library::save(QString fileName, QStandardItemModel *itemModel) {
}

/**
 * @brief Library::load
 * @param fileName
 */
void Library::load(QString fileName){
    translationMap->clear();
    QFile file(fileName);
      if (!file.open(QIODevice::ReadOnly | QIODevice::Text)){
          return;
      }

      QTextStream in(&file);
      QString line = in.readLine();
      while (!line.isNull()) {
          QStringList pieces = line.split("=");
          add(pieces.first(), pieces.last());
          line = in.readLine();
      }
}

/**
 * @brief Library::getItemModel
 * @return
 */
QStandardItemModel *Library::getItemModel(){
    QStandardItemModel *itemModel = new QStandardItemModel(0, 2);
    itemModel->setHorizontalHeaderItem(0, new QStandardItem(QString("source")));
     itemModel->setHorizontalHeaderItem(1, new QStandardItem(QString("translation")));
    QMapIterator<QString, QString> i(*translationMap);
    int index= 0;
    while (i.hasNext()) {
        i.next();
        QList<QStandardItem *> *row = new QList<QStandardItem *>;
        row->append(new QStandardItem(i.key()));
        row->append(new QStandardItem(i.value()));
        itemModel->insertRow(index,*row);
        index++;

    }
    return  itemModel;
}

/**
 * @brief Library::printLangMap
 * @param listWidget
 * @param tMap
 */
void Library::printTranslationMap(QListWidget *listWidget, QMap<QString, QString> *tMap){
    QMapIterator<QString, QString> i(*tMap);
    while (i.hasNext()) {
        i.next();
        listWidget->addItem(new QListWidgetItem(i.key() + " = " + i.value()));
    }
}
