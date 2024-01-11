import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;


public class ExtensionsActions {

    /**
     * Метод copyFile копирует восстанавливаемый файл
     * @param source исходный файл
     * @param destination файл назначения
     * @throws IOException если возникает ошибка при копировании файла
     */
    public static void copyFile(File source, File destination) throws IOException {
        // Копирование файла
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

        FileTypeIdentifier.myLogger.info("Процесс копирования файла.");

    }

    /**
     * Метод getFileType возвращает установленное программой расширение файла
     * @param file восстанавливаемый файл
     * @return установленное расширение файла
     * @throws IOException если возникла ошибка при чтении файла
     */
    public static String getFileType(File file) throws IOException {

        FileTypeIdentifier.myLogger.info("Процесс представления файла в HEX формате и поиск корректного расширения.");

        /**
         * @param extension расширение файла
         * @param magicNumbers байтовый массив, содержащий магическое число
         */
        String extension = null;
        byte[] magicNumbers = new byte[4];

        /**
         * @param fileInputStream поток для ввода файла
         */
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            /**
             * Определение магического числа файла
             * @param hex объект класса StringBuilder для операций над строкой
             */
            if (fileInputStream.read(magicNumbers) != -1) {

                StringBuilder hex = new StringBuilder();

                /**
                 * Добавление магических чисел в StringBuilder
                 * @param b индекс
                 */
                for (byte b : magicNumbers) {

                    hex.append(String.format("%02X", b));

                }

                /**
                 * Получение магического числа
                 * @param magicNumber магическое число
                 */
                String magicNumber = hex.toString();

                // подбор расширения из HashMap
                for (Map.Entry<String, String> entry : MagicNumbers.fileTypes.entrySet()) {

                    // Определение расширения
                    if (magicNumber.startsWith(entry.getValue())) {

                        extension = entry.getKey();

                        break;

                    }

                }

                // if для корректной работы логгера
                if (extension != null) {

                    FileTypeIdentifier.myLogger.info("Корректное расширение установлено: " + extension);

                } else {

                    FileTypeIdentifier.myLogger.info("Не удалось распознать расширение.");

                }
            }

        }

        return extension;

    }

    /**
     * Метод CompareExtensions сравнивает полученное расширение с его фактическим
     * @param file восстанавливаемый файл
     * @return возвращает расширение файла из строки имени
     */
    public static String CompareExtensions(File file) {

        FileTypeIdentifier.myLogger.info("Проверка расширений на совпадение.");

        /**
         * @param name имя файла
         * @param dot индекс точки
         */
        String name = file.getName();
        int dot = name.lastIndexOf('.');

        return (dot == -1) ? name : name.substring(dot + 1, name.length());

    }

}
