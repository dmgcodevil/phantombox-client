#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QTableWidgetItem>
#include <QFileDialog>
#include "library.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit MainWindow(QWidget *parent = 0);
    Library *library = new Library;
    ~MainWindow();
    
private slots:
    void on_testBtn_clicked();

    void on_addTranslationBtn_clicked();



    void on_libraryTable_itemClicked(QTableWidgetItem *item);

    void on_loadLibraryBtn_clicked();

    void on_deleteTranslationBtn_clicked();

    void on_deleteAllBtn_clicked();

    void on_saveLibraryBtn_clicked();

    void on_libraryTable_clicked(const QModelIndex &index);

private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
