package model;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
}
