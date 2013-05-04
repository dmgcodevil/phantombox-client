#ifndef LIBRARY_H
#define LIBRARY_H
#include <QApplication>
#include <QStandardItemModel>
#include <QHBoxLayout>
#include <QListWidget>
#include <QListWidgetItem>
class Library
{
public:
    Library();
    void clear();
    QMap<QString, QString> *getTranslationMap();
    QMap<QString, QString> *getRuEngMap();
    QString getName();
    void setName(QString name);
    QString getPath();
    void setPath(QString path);
    void add(QString source, QString translation);
    void remove(QString source);
    QStandardItemModel *getItemModel();
    void printTranslationMap(QListWidget *listWidget);
    void printTranslationMap(QListWidget *listWidget, QMap<QString, QString> *tMap);
    void save(QString fileName);
    void save(QString fileName, QStandardItemModel *itemModel);
    void load(QString fileName);
    QString find(QString source);
private:
    QMap<QString, QString> *translationMap;
    QString name;
    QString path;
};

#endif // LIBRARY_H
