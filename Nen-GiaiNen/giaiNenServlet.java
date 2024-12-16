package controller;

import model.thongtin;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/unzip")
@MultipartConfig
public class giaiNenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("zipFile");
        String fileName = getFileName(part);
        
        // Kiểm tra định dạng file có phải là ZIP không
        if (!fileName.toLowerCase().endsWith(".zip")) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Lỗi: Chỉ chấp nhận file định dạng .zip</h2>");
            return;
        }
        
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        // Tạo thư mục lưu file ZIP nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Lưu file ZIP vào thư mục uploads
        String filePath = uploadPath + File.separator + fileName;
        part.write(filePath);

        // Thêm công việc giải nén vào hàng đợi
        String outputFolder = getServletContext().getRealPath("") + File.separator + "unzip_output";
        thongtin tt = new thongtin(new File(filePath), outputFolder);
        khac.queueXuLy.addJob(tt);

        // Thông báo cho người dùng
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>Yêu cầu giải nén đã được đưa vào hàng đợi. Vui lòng chờ...</h2>");
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}
