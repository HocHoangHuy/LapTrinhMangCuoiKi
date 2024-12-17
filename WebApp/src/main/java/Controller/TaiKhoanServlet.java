package Controller;

import Model.BO.AuthenticationException;
import Model.BO.TaiKhoanBO;
import Model.Bean.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/TaiKhoan")
public class TaiKhoanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        switch (mode) {
            case "DangNhap":
                HttpSession session = req.getSession(false);
                if (session != null) {
                    resp.sendRedirect(getServletContext().getContextPath() + "/");
                    return;
                }
                req.setAttribute("mode", "DangNhap");
                getServletContext().getRequestDispatcher("/WEB-INF/DangNhap.jsp").forward(req, resp);
                break;
            case "DangXuat":
                HttpSession currentsession = req.getSession(false);
                if (currentsession != null) {
                    currentsession.invalidate();
                }
                String returnURL = getServletContext().getContextPath() + "/TaiKhoan?mode=DangNhap";
                resp.sendRedirect(returnURL);
                break;
            case "DangKy":
                HttpSession session2 = req.getSession(false);
                if (session2 != null) {
                    resp.sendRedirect(getServletContext().getContextPath() + "/");
                    return;
                }
                req.setAttribute("mode", "DangKy");
                getServletContext().getRequestDispatcher("/WEB-INF/DangNhap.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaiKhoanBO bo = new TaiKhoanBO();
        TaiKhoan taiKhoanDangNhap = new TaiKhoan();
        taiKhoanDangNhap.setTenDangNhap(req.getParameter("TenDangNhap"));
        taiKhoanDangNhap.setMatKhau(req.getParameter("MatKhau"));
        String mode = req.getParameter("mode");
        switch (mode) {
            case "DangNhap":
                try {
                    bo.DangNhap(taiKhoanDangNhap);
                    HttpSession session = req.getSession();
                    session.setAttribute("TaiKhoan", taiKhoanDangNhap);
                    resp.sendRedirect(getServletContext().getContextPath().isBlank() ? "/" : getServletContext().getContextPath());
                } catch (AuthenticationException e) {
                    req.setAttribute("error", e.getMessage());
                    req.setAttribute("mode", "DangNhap");
                    getServletContext().getRequestDispatcher("/WEB-INF/DangNhap.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            case "DangKy":
                try {
                    bo.DangKyTaiKhoanMoi(taiKhoanDangNhap);
                    resp.sendRedirect(getServletContext().getContextPath() + "/TaiKhoan?mode=DangNhap");
                } catch (AuthenticationException e) {
                    req.setAttribute("error", e.getMessage());
                    req.setAttribute("mode", "DangKy");
                    getServletContext().getRequestDispatcher("/WEB-INF/DangNhap.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                break;
        }


    }
}
