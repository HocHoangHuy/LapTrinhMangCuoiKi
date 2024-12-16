package Model.DAO;

import Model.Bean.TaiKhoan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaiKhoanDAO {
    public TaiKhoan selectTaiKhoan(TaiKhoan tk) throws Exception {
        try (Connection connection = DbHelper.getConnection(); Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = "select * from taikhoan where ";
            if (tk.getID() != null){
                query += "ID = " + tk.getID();
            }
            else if (tk.getTenDangNhap() != null){
                query += "TenDangNhap = '" + tk.getTenDangNhap() + "'";
            }
            else throw new Exception("Không có dữ liệu để tìm kiếm");
            ResultSet rs = statement.executeQuery(query);
            if (rs.first())
                return new TaiKhoan(rs.getInt("ID"), rs.getString("TenDangNhap"), rs.getString("MatKhau"));
            else return null;
        }
    }

    public void insertTaiKhoan(TaiKhoan taikhoan) throws Exception {
        try (Connection connection = DbHelper.getConnection(); Statement statement = connection.createStatement()) {
            String query = "insert into taikhoan(TenDangNhap, MatKhau) values ('" + taikhoan.getTenDangNhap() + "','" + taikhoan.getMatKhau() + "')";
            statement.executeUpdate(query);
        }
    }

    public void updateTaiKhoan(TaiKhoan taikhoan) throws Exception {
        try (Connection connection = DbHelper.getConnection(); Statement statement = connection.createStatement()) {
            String query = "update taikhoan set TenDangNhap = '" + taikhoan.getTenDangNhap() + "', MatKhau = '" + taikhoan.getMatKhau() + "' where ID = " + taikhoan.getID();
            statement.executeUpdate(query);
        }
    }

    public void deleteTaiKhoan(TaiKhoan taikhoan) throws Exception {
        try (Connection connection = DbHelper.getConnection(); Statement statement = connection.createStatement()) {
            String query = "delete from taikhoan where ID = " + taikhoan.getID();
            statement.executeUpdate(query);
        }
    }
}
