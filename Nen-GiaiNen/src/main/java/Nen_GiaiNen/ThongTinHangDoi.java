package Nen_GiaiNen;

import java.io.File;
import java.util.List;

public class ThongTinHangDoi {
    private boolean isToCompress;
    private File zipfile = null;
    private List<File> filesToCompress = null;
    private String outputPath = null;

    public ThongTinHangDoi(File zipFile, String outputPath) {
        this.zipfile = zipFile;
        this.outputPath = outputPath;
        this.isToCompress = false;
    }

    public ThongTinHangDoi(List<File> filesToCompress, String outputPath) {
        this.filesToCompress = filesToCompress;
        this.outputPath = outputPath;
        this.isToCompress = true;
    }

    public File getZipfile() {
        return zipfile;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public List<File> getFilesToCompress() {
        return filesToCompress;
    }
    public boolean isToCompress() {
        return isToCompress;
    }
}