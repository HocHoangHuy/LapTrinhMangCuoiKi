package model;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Lớp tiện ích để nén file thành định dạng ZIP.
 */
public class nenFile {

    /**
     * Phương thức nén một file thành file ZIP.
     *
     * @param inputFile   File cần nén
     * @param outputPath  Đường dẫn nơi lưu file ZIP đầu ra
     * @return            Đường dẫn tới file ZIP đã nén
     */
    public static String nenfile(File inputFile, String outputPath) {
        String zipFilePath = outputPath + File.separator + inputFile.getName() + ".zip";

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(inputFile)) {

            // Tạo entry mới trong file ZIP
            ZipEntry zipEntry = new ZipEntry(inputFile.getName());
            zos.putNextEntry(zipEntry);

            // Đọc dữ liệu từ file gốc và ghi vào file ZIP
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();

            System.out.println("File đã được nén thành công: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zipFilePath;
    }
}
