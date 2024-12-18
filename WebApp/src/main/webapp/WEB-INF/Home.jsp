<%@ page import="Model.Bean.TaiKhoan" %>
<%@ page contentType="text/html;charset=UTF-8" session="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Trang chủ</title>
    <style>
        header {
            background-color: black;
            color: white;
            padding: 7px;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .list-container {
            display: flex;
            flex-direction: column;
            width: 45%;
            gap: 5px;
            /* border: 1px solid black; */
        }

        .file-list {
            display: flex;
            flex-direction: column;
            gap: 0px;
            margin: 0px;
            padding: 0px;
            list-style-type: none;
            overflow-y: auto;
            height: 350px;
            border: 1px black solid;
            background-color: gray;
        }

        .file-list li {
            box-sizing: border-box;
            width: 100%;
            display: flex;
            flex-direction: row;
            padding: 20px;
            border-bottom: 1px solid black;
            justify-content: space-between;
            background-color: white;
        }

        .file-list li:last-child {
            border-bottom: none;
        }

        button {
            cursor: pointer;
        }

        .upload-button {
            background-color: #1890ff;
            color: white;
            border: none;
            padding: 6px 12px;
            font-size: 16px;
            border-radius: 3px;
            font-weight: 700;
        }

        .upload-button:hover {
            background-color: #005397;
        }

        .compress-button {
            background-color: #cb0000;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 3px;
            font-weight: 700;
            font-size: 16px;
            width: fit-content;
            align-self: flex-end;
        }

        .compress-button:hover {
            background-color: #740000;
        }

        .remove-button {
            background-color: #ff4d4f;
            color: white;
            border: none;
            padding: 3px 6px;
            border-radius: 3px;
        }

        .dialog {
            border-radius: 30px;
            border: none;
            width: 30%;
            height: 40%;
        }

        .dialog-content {
            border: none;
            background-color: white;
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            gap: 10px;
            justify-content: center;
            align-items: center;
        }

        .dialog ::backdrop {
            background-color: rgba(0, 0, 0, 0.5);
        }

        .download-button {
            background-color: #00ff22;
            color: rgb(0, 0, 0);
            border: none;
            font-weight: 700;
            padding: 6px 12px;
            border-radius: 3px;
        }

        .waiting {
            font-size: 20px;
            font-weight: 700;
        }

        .output-container {
            display: flex;
            flex-direction: column;
            width: 45%;
            gap: 5px;
        }
    </style>
    <script>
        function logoutButtonClick() {
            window.location.href =
            <%=request.getContextPath()+"/TaiKhoan?mode=DangXuat"%>
        }
    </script>
</head>
<body>
<header>
    <div style="display: flex; flex-direction: row; gap: 5px">
        <div style="font-family: 'Times New Roman'; color: gainsboro">
            Người dùng
            <span style="font-weight: 700; color: white"
            ><%=((TaiKhoan) request.getSession(false).getAttribute("TaiKhoan")).getTenDangNhap()%></span
            >
        </div>
        <button onclick="logoutButtonClick()">Đăng xuất</button>
    </div>
</header>
<div
        style="
                width: 70%;
                padding: 10px;
                box-sizing: border-box;
                margin: auto;
                display: flex;
                flex-direction: column;
                gap: 10px;
            "
>
    <h1 style="text-align: center;">Nén file</h1>
    <div
            style="
                    display: flex;
                    flex-direction: row;
                    justify-content: space-between;
                "
    >
        <div class="list-container">
            <div
                    style="
                            display: flex;
                            flex-direction: row-reverse;
                            gap: 5px;
                            justify-content: space-between;
                        "
            >
                <button id="upload-button" class="upload-button">
                    Chọn file để tải lên
                </button>
                <span style="align-content: center;">(Có thể chọn nhiều file)</span>
            </div>

            <input
                    type="file"
                    id="file-input"
                    style="display: none"
                    multiple
            />
            <ul id="file-list" class="file-list">
                <!-- Files will appear here dynamically -->
            </ul>
        </div>
        <div class="output-container">
            <label for="output-name">Đặt tên file kết quả:</label>
            <div style="display: flex; flex-direction: row;">
                <input type="text" id="output-name" style="padding: 5px; width: 100%;" placeholder="Tên file nén"/>
                <span style="align-content: center;">.zip</span>
            </div>

            <button id="compress-button" class="compress-button">
                Nén
            </button>
        </div>
    </div>

    <dialog id="dialog" class="dialog">
        <div id="dialog-content" class="dialog-content">
            <div id="waiting" class="waiting">Đang xử lý...</div>
            <button id="download-button" class="download-button">
                Tải xuống
            </button>
        </div>
    </dialog>
</div>
<script src="homeScript.js"></script>
<div id="contextPath"><%=request.getContextPath()%>
</div>
</body>
</html>

