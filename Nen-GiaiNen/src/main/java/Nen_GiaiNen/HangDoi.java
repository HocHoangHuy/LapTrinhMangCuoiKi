package Nen_GiaiNen;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class HangDoi {
    private static final BlockingQueue<ThongTinHangDoi> hangdoi = new LinkedBlockingQueue<>();

    private static HangDoi instance;

    // Luồng xử lý công việc ngầm
    static {


    }

    private HangDoi() {
    }

    public static HangDoi getInstance() {
        if (instance == null) {
            instance = new HangDoi();
        }
        return instance;
    }

    // Thêm công việc vào hàng đợi
    public static void addJob(ThongTinHangDoi tt) {
        hangdoi.add(tt);
    }

    public void StartQueueThread() {
        new Thread(() -> {
            try {
                while (true) {
                    ThongTinHangDoi tt = hangdoi.take();
                    if (!tt.isToCompress()) {
                        // Giải nén file ZIP
                        giaiNenFile.giaiNen(tt.getZipfile(), tt.getOutputPath());
                    } else {
                        // Nén file
                        giaiNenFile.compressFiles(tt.getFilesToCompress(), tt.getOutputPath());
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
