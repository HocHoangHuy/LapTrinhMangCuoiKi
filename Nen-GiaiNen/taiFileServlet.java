package controller;

import model.thongtin;
import khac.queueXuLy;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")  // Định nghĩa đường dẫn URL cho Servlet
@MultipartConfig
public class taiFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("files");
        String fileName = getFileName(part);
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        // Tạo thư mục lưu file nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Lưu file vào thư mục uploads
        String filePath = uploadPath + File.separator + fileName;
        part.write(filePath);

        // Thêm công việc nén file vào hàng đợi
        String compressedPath = getServletContext().getRealPath("") + File.separator + "download";
        thongtin job = new thongtin(new File(filePath), compressedPath);
        queueXuLy.addJob(job);

        // Chuyển hướng đến trang kết quả
        response.sendRedirect("result.jsp");
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
