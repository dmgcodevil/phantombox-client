#include "fileutils.h"
#include <QApplication>
/**
 * @brief FileUtils::FileUtils
 */
FileUtils::FileUtils()
{
}

/**
 * @brief FileUtils::getFileNameFromPath
 * @param path
 * @return
 */
QString FileUtils::getFileNameFromPath(QString path){
    path.replace(QString("//"), QString("\\"));
    std::string pathSec = path.toStdString();
    std::string filename;
    size_t pos = pathSec.find_last_of("//");
    if(pos != std::string::npos) {
        filename.assign(pathSec.begin() + pos + 1, pathSec.end());
    }
    else {
        filename = pathSec;
    }
    return QString::fromStdString(filename);
}
