package Model.BO;

import Model.Bean.TaiKhoan;
import Model.DAO.TaiKhoanDAO;

import java.util.Objects;

public class TaiKhoanBO {
    private TaiKhoanDAO dao = new TaiKhoanDAO();
    public void DangKyTaiKhoanMoi(TaiKhoan tk) throws AuthenticationException,Exception{
        if (dao.selectTaiKhoan(tk) != null) {
            throw new AuthenticationException("Tên đăng nhập đã tồn tại");
        };
        dao.insertTaiKhoan(tk);
    }

    public void DangNhap(TaiKhoan tk) throws AuthenticationException, Exception{
        TaiKhoan taiKhoan = dao.selectTaiKhoan(tk);
        if (taiKhoan == null) {
            throw new AuthenticationException("Tên đăng nhập không tồn tại");
        } else if (!Objects.equals(taiKhoan.getMatKhau(), tk.getMatKhau())) {
            throw new AuthenticationException("Sai mật khẩu");
        }
    }
}

