package Controller;

import Model.BO.FileBO;
import Model.Bean.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/api/upload")
@MultipartConfig()
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Model.Bean.File> filesToSave = new ArrayList<>();
        try {
            for (Part part : req.getParts()) {
                Model.Bean.File file = new Model.Bean.File();
                file.setFileName(part.getSubmittedFileName());
                file.setFileSize(part.getSize());
                file.setTaiKhoan((TaiKhoan) req.getSession(false).getAttribute("TaiKhoan"));
                file.setFileData(part.getInputStream().readAllBytes());
                filesToSave.add(file);
            }
            FileBO fileBO = new FileBO();
            fileBO.TiepNhanFile(filesToSave);
        } catch (Exception e) {
            throw new ServletException("File upload failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");

    }
}
