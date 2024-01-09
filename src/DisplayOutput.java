import javax.swing.*;
import java.io.File;

public class DisplayOutput {

    public static void displayIfExtensionIsCorrect(JFrame frame, String fileType) {

        JLabel label = new JLabel("Файл не поврежден. Расширение: " + fileType);
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void restoreFileExtension(JFrame frame, File file, String fileType) {

        JLabel label = new JLabel("Тип расширения файла: " + fileType);
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String name = file.getName();
        int dot = name.lastIndexOf('.');
        String woext = (dot == -1) ? name : name.substring(0, dot);
        String restoredFilename = woext + '.' + fileType.toLowerCase();
        File restoredFile = new File(file.getParent(), restoredFilename);

        if (file.renameTo(restoredFile)) {

            JLabel label1 = new JLabel("Тип расширения файла изменен на корректный: " + restoredFile.getName());
            panel.add(label1);
            frame.getContentPane().add(panel);

        } else {

            JLabel label2 = new JLabel("Произошла ошибка в восстановлении расширения. Попробуйте снова!");
            panel.add(label2);
            frame.getContentPane().add(panel);

        }

    }

    public static void displayIfIdentifyFails() {

        JFrame frame = new JFrame("Восстановление расширения файла");
        JLabel label = new JLabel("Не удалось распознать тип расширения файла.");
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
