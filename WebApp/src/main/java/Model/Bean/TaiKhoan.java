package Model.Bean;

public class TaiKhoan {
    private Integer ID;
    private String TenDangNhap;
    private String MatKhau;

    public TaiKhoan() {
        ID = null;
        TenDangNhap = null;
        MatKhau = null;
    }

    public TaiKhoan(Integer _ID, String _TenDangNhap, String _MatKhau) {
        ID = _ID;
        TenDangNhap = _TenDangNhap;
        MatKhau = _MatKhau;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
