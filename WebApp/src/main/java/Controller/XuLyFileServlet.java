package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/compress")
public class XuLyFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        switch (mode)
        {
            case "Compress":
            {

                break;
            }
            case "Download":
            {

                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
    }
}
