package Model.Bean;

public class File {
    private String fileName = null;
    private String filePath = null;
    private long fileSize = 0;
    private byte[] fileData = null;
    private TaiKhoan taikhoan = null;

    public File(String fileName, String filePath, long fileSize, byte[] fileData, TaiKhoan taikhoan) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.taikhoan = taikhoan;
    }

    public File() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public TaiKhoan getTaiKhoan() {
        return taikhoan;
    }

    public void setTaiKhoan(TaiKhoan taikhoan) {
        this.taikhoan = taikhoan;
    }
}
