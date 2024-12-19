package Nen_GiaiNen;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class giaiNenFile {

    /**
     * Phương thức giải nén file ZIP.
     *
     * @param zipFile       File ZIP cần giải nén
     * @param outputFolder  Thư mục để lưu các file được giải nén
     * @return              Đường dẫn đến thư mục đã giải nén
     */
    public static String giaiNen(File zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];

        //đọc nội dung của file zip
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry;

            // Tạo thư mục đích nếu chưa tồn tại
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Đọc từng entry trong file ZIP
            while ((zipEntry = zis.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
                //tạo file mới từ entry
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("Giải nén file: " + newFile.getAbsolutePath());

                // Tạo các thư mục con nếu cần thiết
                new File(newFile.getParent()).mkdirs();

                // Ghi dữ liệu vào file mới
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }

                zis.closeEntry();
            }

            System.out.println("Giải nén thành công vào thư mục: " + outputFolder);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFolder;
    }

    /**
     * Phương thức nén một file thành file ZIP.
     *
     * @param inputFiles  Danh sách file cần nén
     * @param outputPath  Đường dẫn file ZIP đầu ra
     * @return            Đường dẫn tới file ZIP đã nén
     */
    public static String compressFiles(List<File> inputFiles, String outputPath) {
        if (inputFiles == null || inputFiles.isEmpty()) {
            throw new IllegalArgumentException("Input file list is empty.");
        }

        if (outputPath == null || outputPath.isBlank()) {
            throw new IllegalArgumentException("Output path is invalid.");
        }

        // Ensure the output directory exists
        File outputFile = new File(outputPath);
        File outputDir = new File(outputFile.getParent());
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new RuntimeException("Failed to create output directory: " + outputPath);
        }
        String outputName = outputFile.getName();
        if (!outputName.endsWith(".zip")) {
            outputPath = outputPath + ".zip";
        }


        try (FileOutputStream fos = new FileOutputStream(outputPath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (File inputFile : inputFiles) {
                if (inputFile == null || !inputFile.exists()) {
                    System.out.println("Skipping invalid file: " + inputFile);
                    continue;
                }

                try (FileInputStream fis = new FileInputStream(inputFile)) {
                    // Add file to the ZIP archive
                    ZipEntry zipEntry = new ZipEntry(inputFile.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[8192];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    System.out.println("File added to ZIP: " + inputFile.getName());
                } catch (IOException e) {
                    System.err.println("Failed to add file to ZIP: " + inputFile.getName());
                    e.printStackTrace();
                }
            }

            System.out.println("ZIP file created successfully: " + outputPath);

        } catch (IOException e) {
            throw new RuntimeException("Error while creating ZIP file: " + outputPath, e);
        }

        return outputPath;
    }
}
