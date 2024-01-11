import javax.swing.*;
import java.io.File;


public class DisplayOutput {

    /**
     * Метод displayIfExtensionIsCorrect отображает информацию о расширении файла, если оно корректное.
     * @param frame главное окно программы
     * @param fileType тип расширения файла
     */
    public static void displayIfExtensionIsCorrect(JFrame frame, String fileType) {

        /**
         * Создание интерфейса и вывод информации
         * @param label выводимое сообщение
         * @param panel тело окна
          */
        JLabel label = new JLabel("Файл не поврежден. Расширение: " + fileType);
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        FileTypeIdentifier.myLogger.info("Отображение расширения неповрежденного файла в JFrame.");

    }

    /**
     * Метод restoreFileExtension отображает информацию о успешном восстановлении расширения файла, либо об ошибке в восстановлении.
     * @param frame главное окно программы
     * @param file файл для восстановления
     * @param fileType тип расширения
     */
    public static void restoreFileExtension(JFrame frame, File file, String fileType) {

        /**
         * Создание интерфейса и вывод информации
         * @param label выводимое сообщение
         * @param panel тело окна
         */
        JLabel label = new JLabel("Тип расширения файла: " + fileType);
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /**
         * Переименование файла
         * @param dot индекс точки в названии файла
         * @param name имя файла
         * @param woext имя, обрезанное до точки с расширением
         * @param restoredFilename новое имя файла с корректным расширением
         * @param restoredFile восстановленный файл
         */
        String name = file.getName();
        int dot = name.lastIndexOf('.');
        String woext = (dot == -1) ? name : name.substring(0, dot);
        String restoredFilename = woext + '.' + fileType.toLowerCase();
        File restoredFile = new File(file.getParent(), restoredFilename);

        if (file.renameTo(restoredFile)) {

            /**
            * Сообщение об успешном изменении расширения файла
            * @param label1 выводимое сообщение
             */
            JLabel label1 = new JLabel("Тип расширения файла изменен на корректный: " + restoredFile.getName());
            panel.add(label1);
            frame.getContentPane().add(panel);

            FileTypeIdentifier.myLogger.info("Отображение восстановленного расширения файла в JFrame.");

        } else {

            /**
             * Сообщение об ошибке в восстановлении расширения
             * @param label2 окно интерфейса
             */
            JLabel label2 = new JLabel("Произошла ошибка в восстановлении расширения. Попробуйте снова!");
            panel.add(label2);
            frame.getContentPane().add(panel);

            FileTypeIdentifier.myLogger.info("Отображение сообщения о том, что не удалось восстановить файл в JFrame.");

        }

    }

    /**
     * Метод displayIfIdentityFails предназначен для вывода информации о том, что не удалось распознать расширение файла
     */
    public static void displayIfIdentifyFails() {

        /**
         * @param frame название окна
         * @param label выводимое сообщение
         * @param panel тело окна
         */
        JFrame frame = new JFrame("Восстановление расширения файла");
        JLabel label = new JLabel("Не удалось распознать тип расширение файла.");
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        FileTypeIdentifier.myLogger.info("Отображение сообщения о том, что не удалось распознать расширение файла в JFrame.");

    }

}
