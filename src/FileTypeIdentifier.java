import javax.swing.*;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.*;


public class FileTypeIdentifier {

    /**
     * Логгер для записи информации о действиях программы.
     */
    public static Logger myLogger = Logger.getLogger(FileTypeIdentifier.class);

    /**
     * Метод для запуска программы и выполнения идентификации типа файла.
     *
     * @param args аргументы командной строки
     * @throws IOException если возникла ошибка ввода-вывода
     */
    public static void main(String[] args) throws IOException {

        // Устанавливаем уровень логирования на все сообщения
        myLogger.setLevel(Level.ALL);
        myLogger.info("Запуск программы.");

        /**
         * Открытие проводника для выбора файла
         * @param result результат открытия файла
         * @param file выбранный файл
          */
        myLogger.info("Открытие проводника.");
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        File file;

        if (result == JFileChooser.APPROVE_OPTION) {

            /**
             * Файл выбран
             * @param desktopPath папка, в которой находится файл
             * @param desktopFile файл для копирования
             */
            myLogger.info("Файл выбран.");

            file = fileChooser.getSelectedFile();
            String desktopPath = file.getParent();
            File desktopFile = new File(desktopPath, file.getName());

            try {

                // Копируем файл в ту же папку
                ExtensionsActions.copyFile(file, desktopFile);
                file = desktopFile;

                myLogger.info("Файл успешно скопирован.");

            } catch (IOException e) {

                // В случае ошибки при копировании
                myLogger.error("Не удалось скопировать файл: " + e.getMessage());

                e.printStackTrace();

            }

            /**
             * Получаем тип расширения файла
             * @param filetype расширение файла
             * @param frame окно для интерфейса
             * @param ext имеющееся расширения файла для сравнения с тем, который определит программа
             */
            String fileType = ExtensionsActions.getFileType(file);
            String ext = ExtensionsActions.CompareExtensions(file);
            JFrame frame = new JFrame("Восстановление расширения файла");

            if (fileType != null && ext.equals(fileType.toLowerCase())) {

                myLogger.info("Выбранный файл не поврежден. Программа завершена.");
                // Если файл не поврежден
                DisplayOutput.displayIfExtensionIsCorrect(frame, fileType);

            } else if (fileType != null) {

                myLogger.info("Выполнено восстановление расширения файла. Программа завершена.");
                // Если требуется восстановление расширения файла
                DisplayOutput.restoreFileExtension(frame, file, fileType);

            } else {

                myLogger.info("Не удалось определить расширение файла. Программа завершена.");
                // Если не удалось определить расширение файла
                DisplayOutput.displayIfIdentifyFails();

            }

        } else {

            myLogger.info("Файл не выбран. Программа завершена.");
            // Если файл не выбран
            System.out.println("Файл не выбран. Попробуйте снова!");

        }

    }

}