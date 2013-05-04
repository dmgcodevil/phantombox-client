#include "mainwindow.h"
#include "library.h"
#include "fileutils.h"
#include "ui_mainwindow.h"
#include <QFileDialog>

MainWindow::MainWindow(QWidget *parent) :QMainWindow(parent), ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_testBtn_clicked()
{
//    Library *library = new Library;
//    library->add(QString("hello"),  QString("??????"));
//    library->add(QString("hello1"),  QString("??????1"));
//    library->printTranslationMap(ui->engRuListWidget);
//    ui->libraryTable->insertRow(2);
//    ui->testLbl->setText(library->find("hello"));
}


void MainWindow::on_addTranslationBtn_clicked()
{
  library->add(ui->sourceEdit->text(), ui->translationEdit->text());
  ui->libraryTable->setModel(library->getItemModel());
}

void MainWindow::on_libraryTable_itemClicked(QTableWidgetItem *item)
{
    ui->sourceEdit->setText(item->text());
}

void MainWindow::on_loadLibraryBtn_clicked()
{
    QString fileName = QFileDialog::getOpenFileName(this, tr("Open library"), "", tr("library files (*.lib)"));
    FileUtils fileUtils = FileUtils();
    ui->libraryNamelbl->setText(fileUtils.getFileNameFromPath(fileName));
    library->setPath(fileName);
    library->load(fileName);
    ui->libraryTable->setModel(library->getItemModel());
}

void MainWindow::on_deleteTranslationBtn_clicked()
{
    QItemSelectionModel *select = ui->libraryTable->selectionModel();
    QModelIndexList rows = select->selectedRows();
    QList<QModelIndex>::iterator i;
    for (i = rows.begin(); i != rows.end(); ++i)
    {
        QString source = ui->libraryTable->model()->data(ui->libraryTable->model()->index(i->row(),0)).toString();
        library->remove(source);
    }
    ui->libraryTable->setModel(library->getItemModel());
}

void MainWindow::on_deleteAllBtn_clicked()
{
    library->clear();
    ui->libraryTable->setModel(library->getItemModel());
}

void MainWindow::on_saveLibraryBtn_clicked()
{
    library->save(library->getPath());
}

void MainWindow::on_libraryTable_clicked(const QModelIndex &index)
{
    int row = index.row();
    int col = index.column();
    QString source = ui->libraryTable->model()->data(ui->libraryTable->model()->index(row,0)).toString();
    QString transcription = ui->libraryTable->model()->data(ui->libraryTable->model()->index(row,1)).toString();
    ui->sourceEdit->setText(source);
    ui->translationEdit->setText(transcription);

}
