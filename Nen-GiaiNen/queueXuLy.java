package khac;

import model.nenFile;
import model.giaiNenFile;
import model.thongtin;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class queueXuLy {
    private static BlockingQueue<thongtin> hangdoi = new LinkedBlockingQueue<>();

    // Thêm công việc vào hàng đợi
    public static void addJob(thongtin tt) {
        hangdoi.add(tt);
    }

    // Luồng xử lý công việc ngầm
    static {
        new Thread(() -> {
            while (true) {
                try {
                    thongtin tt = hangdoi.take();
                    File file = tt.getFile();
                    String outputPath = tt.getOutputPath();

                    if (file.getName().toLowerCase().endsWith(".zip")) {
                        // Giải nén file ZIP
                        giaiNenFile.giaiNen(file, outputPath);
                    } else {
                        // Nén file
                        nenFile.nenfile(file, outputPath);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
