<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<% String contextPath = request.getContextPath();
    if (contextPath.isBlank()) contextPath = "/";
    String mode = request.getParameter("mode");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><%=(mode.equals("DangNhap"))? "Đăng nhập": "Đăng ký"%></title>
    <style>
        .form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-template-rows: 1fr 1fr;
            gap: 10px;
        }

        .form-label {
            align-content: center;
        }

        .container {
            display: flex;
            flex-direction: column;
            width: 27%;
            row-gap: 20px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <script>
        function DangKyButtonClick() {
            window.location.href = "<%=request.getContextPath()%>/TaiKhoan?mode=DangKy";
        }

        function DangNhapButtonClick() {
            window.location.href = "<%=request.getContextPath()%>/TaiKhoan?mode=DangNhap";
        }
    </script>
</head>
<body>
<div class="container">
    <h1 style="text-align: center"><%= (mode.equals("DangNhap"))? "Đăng nhập":"Đăng ký" %></h1>
    <form id="login-form" class="form" action="" method="post">
        <label for="username" class="form-label">Tên đăng nhập:</label>
        <input type="text" id="username" name="username" required />

        <label for="password" class="form-label">Mật khẩu:</label>
        <input type="password" id="password" name="password" required />
    </form>
    <div
            id="error"
            style="color: red; font-size: 14px; height: 16px; margin-top: -15px; align-content: center"
    ><%=(request.getAttribute("error")==null)?"":request.getAttribute("error")%></div>
    <div style="display: flex; flex-direction: row; gap: 10px;">
        <button
                form="login-form"
                type="submit"
                style="width: fit-content; margin: 1px"
        >
            <%=(mode.equals("DangNhap"))? "Đăng nhập": "Đăng ký"%>
        </button>
        <button
                onclick="<%= (mode.equals("DangNhap"))? "DangKyButtonClick()":"DangNhapButtonClick()"%>"
                type="button"
                style="width: fit-content; margin: 1px">
            <%=(mode.equals("DangNhap"))? "Đăng ký": "Đăng nhập"%>
        </button>
    </div>
</div>
</body>
</html>

