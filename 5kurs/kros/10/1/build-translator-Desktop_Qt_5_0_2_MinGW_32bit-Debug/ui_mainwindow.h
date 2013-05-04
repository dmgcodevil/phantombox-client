/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.0.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSplitter>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTableView>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QTableView *libraryTable;
    QLabel *libraryNamelbl;
    QWidget *widget;
    QFormLayout *formLayout;
    QGridLayout *gridLayout;
    QLabel *label;
    QLabel *label_2;
    QLineEdit *sourceEdit;
    QLineEdit *translationEdit;
    QPushButton *addTranslationBtn;
    QSplitter *splitter;
    QPushButton *saveLibraryBtn;
    QPushButton *loadLibraryBtn;
    QPushButton *deleteTranslationBtn;
    QPushButton *deleteAllBtn;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(424, 413);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        libraryTable = new QTableView(centralWidget);
        libraryTable->setObjectName(QStringLiteral("libraryTable"));
        libraryTable->setGeometry(QRect(10, 30, 401, 192));
        libraryNamelbl = new QLabel(centralWidget);
        libraryNamelbl->setObjectName(QStringLiteral("libraryNamelbl"));
        libraryNamelbl->setGeometry(QRect(10, 10, 391, 16));
        widget = new QWidget(centralWidget);
        widget->setObjectName(QStringLiteral("widget"));
        widget->setGeometry(QRect(10, 280, 282, 72));
        formLayout = new QFormLayout(widget);
        formLayout->setSpacing(6);
        formLayout->setContentsMargins(11, 11, 11, 11);
        formLayout->setObjectName(QStringLiteral("formLayout"));
        formLayout->setContentsMargins(0, 0, 0, 0);
        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        label = new QLabel(widget);
        label->setObjectName(QStringLiteral("label"));

        gridLayout->addWidget(label, 0, 0, 1, 1);

        label_2 = new QLabel(widget);
        label_2->setObjectName(QStringLiteral("label_2"));

        gridLayout->addWidget(label_2, 0, 1, 1, 1);

        sourceEdit = new QLineEdit(widget);
        sourceEdit->setObjectName(QStringLiteral("sourceEdit"));

        gridLayout->addWidget(sourceEdit, 1, 0, 1, 1);

        translationEdit = new QLineEdit(widget);
        translationEdit->setObjectName(QStringLiteral("translationEdit"));

        gridLayout->addWidget(translationEdit, 1, 1, 1, 1);


        formLayout->setLayout(0, QFormLayout::LabelRole, gridLayout);

        addTranslationBtn = new QPushButton(widget);
        addTranslationBtn->setObjectName(QStringLiteral("addTranslationBtn"));

        formLayout->setWidget(1, QFormLayout::LabelRole, addTranslationBtn);

        splitter = new QSplitter(centralWidget);
        splitter->setObjectName(QStringLiteral("splitter"));
        splitter->setGeometry(QRect(10, 230, 401, 23));
        splitter->setOrientation(Qt::Horizontal);
        saveLibraryBtn = new QPushButton(splitter);
        saveLibraryBtn->setObjectName(QStringLiteral("saveLibraryBtn"));
        splitter->addWidget(saveLibraryBtn);
        loadLibraryBtn = new QPushButton(splitter);
        loadLibraryBtn->setObjectName(QStringLiteral("loadLibraryBtn"));
        splitter->addWidget(loadLibraryBtn);
        deleteTranslationBtn = new QPushButton(splitter);
        deleteTranslationBtn->setObjectName(QStringLiteral("deleteTranslationBtn"));
        splitter->addWidget(deleteTranslationBtn);
        deleteAllBtn = new QPushButton(splitter);
        deleteAllBtn->setObjectName(QStringLiteral("deleteAllBtn"));
        splitter->addWidget(deleteAllBtn);
        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 424, 21));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0));
        libraryNamelbl->setText(QApplication::translate("MainWindow", "libraryName", 0));
        label->setText(QApplication::translate("MainWindow", "source", 0));
        label_2->setText(QApplication::translate("MainWindow", "translation", 0));
        addTranslationBtn->setText(QApplication::translate("MainWindow", "add / modify", 0));
        saveLibraryBtn->setText(QApplication::translate("MainWindow", "save", 0));
        loadLibraryBtn->setText(QApplication::translate("MainWindow", "load", 0));
        deleteTranslationBtn->setText(QApplication::translate("MainWindow", "delete", 0));
        deleteAllBtn->setText(QApplication::translate("MainWindow", "delete all", 0));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
