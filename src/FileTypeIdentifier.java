import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class FileTypeIdentifier {

    public static void main(String[] args) throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        File file;

        if (result == JFileChooser.APPROVE_OPTION) {

            file = fileChooser.getSelectedFile();
            String desktopPath = file.getParent();
            File desktopFile = new File(desktopPath, file.getName());

            try {

                ExtensionsActions.copyFile(file, desktopFile);
                file = desktopFile;

            } catch (IOException e) {

                e.printStackTrace();

            }

            String fileType = ExtensionsActions.getFileType(file);
            String ext = ExtensionsActions.CompareExtensions(file);
            JFrame frame = new JFrame("Восстановление расширения файла");

            if (fileType != null && ext.equals(fileType.toLowerCase())) {

                DisplayOutput.displayIfExtensionIsCorrect(frame, fileType);

            } else if (fileType != null) {

                DisplayOutput.restoreFileExtension(frame, file, fileType);

            } else {

                DisplayOutput.displayIfIdentifyFails();

            }

        } else {

            System.out.println("Файл не выбран. Попробуйте снова!");

        }

    }

}