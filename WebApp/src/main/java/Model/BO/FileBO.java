package Model.BO;

import Model.Bean.File;
import Model.Bean.TaiKhoan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class FileBO {
    private static String userFileStoragePath = null;
    static {
        Properties prop = new Properties();
        try {
            prop.load(File.class.getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userFileStoragePath = prop.getProperty("UserFileStoragePath");
    }
    public void TiepNhanFile(List<File> files) throws Exception{
        java.io.File downloadDir = new java.io.File(userFileStoragePath + java.io.File.separator + files.getFirst().getTaiKhoan().getID());
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
        for (File file : files) {
            java.io.File file1 = new java.io.File(downloadDir.getAbsolutePath() + java.io.File.separator + file.getFileName());
            if (file1.exists()) file1.delete();
            file1.createNewFile();
            try(FileOutputStream fos = new FileOutputStream(file1)){
                fos.write(file.getFileData());
            }
        }
    }

    public void NenFile(TaiKhoan taikhoan) throws Exception{

    }

    public File getFileNen(TaiKhoan taiKhoan) throws Exception{

    }
}
