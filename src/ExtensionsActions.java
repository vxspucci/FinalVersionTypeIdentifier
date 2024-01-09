import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class ExtensionsActions {

    public static void copyFile(File source, File destination) throws IOException {

        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    public static String getFileType(File file) throws IOException {

        String extension = null;
        byte[] magicNumbers = new byte[4];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            if (fileInputStream.read(magicNumbers) != -1) {

                StringBuilder hex = new StringBuilder();

                for (byte b : magicNumbers) {

                    hex.append(String.format("%02X", b));

                }

                String magicNumber = hex.toString();

                for (Map.Entry<String, String> entry : MagicNumbers.fileTypes.entrySet()) {

                    if (magicNumber.startsWith(entry.getValue())) {

                        extension = entry.getKey();
                        break;

                    }

                }

            }

        }

        return extension;

    }

    public static String CompareExtensions(File file) {

        String name = file.getName();
        int dot = name.lastIndexOf('.');

        return (dot == -1) ? name : name.substring(dot + 1, name.length());

    }

}
