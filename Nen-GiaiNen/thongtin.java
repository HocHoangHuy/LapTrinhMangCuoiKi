package model;

import java.io.File;

public class thongtin {
    private File file;
    private String outputPath;

    public thongtin(File file, String outputPath) {
        this.file = file;
        this.outputPath = outputPath;
    }

    public File getFile() {
        return file;
    }

    public String getOutputPath() {
        return outputPath;
    }
}