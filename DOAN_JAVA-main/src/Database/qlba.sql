-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 07, 2024 lúc 06:42 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlba`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `SOHD` varchar(10) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `DONGIA` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`SOHD`, `MASP`, `MASIZE`, `SOLUONG`, `DONGIA`) VALUES
('HD0', 'SP1', 'SIZE1', 3, 220000),
('HD0', 'SP3', 'SIZE5', 2, 418000),
('HD1', 'SP6', 'SIZE4', 1, 352000),
('HD2', 'SP1', 'SIZE1', 2, 264000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MAPN` varchar(10) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `GIANHAP` double NOT NULL,
  `THANHTIEN` double NOT NULL,
  `MASIZE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MAPN`, `MASP`, `SOLUONG`, `GIANHAP`, `THANHTIEN`, `MASIZE`) VALUES
('PN001', 'SP1', 10, 200000, 2000000, 'SIZE1'),
('PN001', 'SP1', 10, 200000, 2000000, 'SIZE2'),
('PN001', 'SP2', 10, 250000, 2500000, 'SIZE4'),
('PN001', 'SP3', 10, 380000, 3800000, 'SIZE5'),
('PN001', 'SP6', 5, 320000, 1600000, 'SIZE2'),
('PN001', 'SP6', 10, 320000, 3200000, 'SIZE4'),
('PN011', 'SP1', 4, 240000, 960000, 'SIZE5'),
('PN011', 'SP4', 10, 250000, 2500000, 'SIZE2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `MAQUYEN` varchar(10) NOT NULL,
  `MACHUCNANG` varchar(10) NOT NULL,
  `HANHDONG` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietquyen`
--

INSERT INTO `chitietquyen` (`MAQUYEN`, `MACHUCNANG`, `HANHDONG`) VALUES
('QNV', 'HD', 'Thêm'),
('QNV', 'HD', 'Xem'),
('QNV', 'KH', 'Sửa'),
('QNV', 'KH', 'Thêm'),
('QNV', 'KH', 'Xem'),
('QNV', 'SP', 'Xem'),
('QQLBH', 'HD', 'Sửa'),
('QQLBH', 'HD', 'Thêm'),
('QQLBH', 'HD', 'Xem'),
('QQLBH', 'HD', 'Xóa'),
('QQLBH', 'KH', 'Sửa'),
('QQLBH', 'KH', 'Thêm'),
('QQLBH', 'KH', 'Xem'),
('QQLBH', 'KH', 'Xóa'),
('QQLBH', 'NV', 'Sửa'),
('QQLBH', 'NV', 'Thêm'),
('QQLBH', 'NV', 'Xem'),
('QQLBH', 'NV', 'Xóa'),
('QQLBH', 'SP', 'Xem'),
('QQLHT', 'HD', 'Sửa'),
('QQLHT', 'HD', 'Thêm'),
('QQLHT', 'HD', 'Xem'),
('QQLHT', 'HD', 'Xóa'),
('QQLHT', 'KH', 'Sửa'),
('QQLHT', 'KH', 'Thêm'),
('QQLHT', 'KH', 'Xem'),
('QQLHT', 'KH', 'Xóa'),
('QQLHT', 'LOAI', 'Sửa'),
('QQLHT', 'LOAI', 'Thêm'),
('QQLHT', 'LOAI', 'Xem'),
('QQLHT', 'LOAI', 'Xóa'),
('QQLHT', 'NCC', 'Sửa'),
('QQLHT', 'NCC', 'Thêm'),
('QQLHT', 'NCC', 'Xem'),
('QQLHT', 'NCC', 'Xóa'),
('QQLHT', 'NULLThK', 'Xem'),
('QQLHT', 'NV', 'Sửa'),
('QQLHT', 'NV', 'Thêm'),
('QQLHT', 'NV', 'Xem'),
('QQLHT', 'NV', 'Xóa'),
('QQLHT', 'PN', 'Sửa'),
('QQLHT', 'PN', 'Thêm'),
('QQLHT', 'PN', 'Xem'),
('QQLHT', 'PN', 'Xóa'),
('QQLHT', 'PQ', 'Sửa'),
('QQLHT', 'PQ', 'Thêm'),
('QQLHT', 'PQ', 'Xem'),
('QQLHT', 'PQ', 'Xóa'),
('QQLHT', 'QLK', 'Xem'),
('QQLHT', 'SIZE', 'Sửa'),
('QQLHT', 'SIZE', 'Thêm'),
('QQLHT', 'SIZE', 'Xem'),
('QQLHT', 'SIZE', 'Xóa'),
('QQLHT', 'SP', 'Sửa'),
('QQLHT', 'SP', 'Thêm'),
('QQLHT', 'SP', 'Xem'),
('QQLHT', 'SP', 'Xóa'),
('QQLHT', 'TK', 'Sửa'),
('QQLHT', 'TK', 'Thêm'),
('QQLHT', 'TK', 'Xem'),
('QQLHT', 'TK', 'Xóa'),
('QQLK', 'HD', 'Xem'),
('QQLK', 'KH', 'Xem'),
('QQLK', 'NV', 'Xem'),
('QQLK', 'PN', 'Export Excel'),
('QQLK', 'PN', 'Sửa'),
('QQLK', 'PN', 'Thêm'),
('QQLK', 'PN', 'Xem'),
('QQLK', 'PN', 'Xóa'),
('QQLK', 'SP', 'Sửa'),
('QQLK', 'SP', 'Xem'),
('QQLK', 'SP', 'Xóa'),
('QUYEN0', 'HD', 'Thêm'),
('QUYEN0', 'HD', 'Xem'),
('QUYEN0', 'KH', 'Xem');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietsanpham`
--

CREATE TABLE `chitietsanpham` (
  `MASP` varchar(10) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietsanpham`
--

INSERT INTO `chitietsanpham` (`MASP`, `MASIZE`, `SOLUONG`) VALUES
('SP1', 'SIZE1', 5),
('SP1', 'SIZE2', 10),
('SP1', 'SIZE5', 4),
('SP10', 'SIZE2', 0),
('SP10', 'SIZE3', 0),
('SP2', 'SIZE2', 0),
('SP2', 'SIZE4', 10),
('SP2', 'SIZE5', 0),
('SP3', 'SIZE5', 8),
('SP4', 'SIZE2', 10),
('SP4', 'SIZE5', 0),
('SP5', 'SIZE1', 0),
('SP5', 'SIZE2', 0),
('SP5', 'SIZE3', 0),
('SP6', 'SIZE2', 5),
('SP6', 'SIZE4', 9),
('SP6', 'SIZE5', 0),
('SP7', 'SIZE5', 0),
('SP8', 'SIZE1', 0),
('SP8', 'SIZE2', 0),
('SP8', 'SIZE3', 0),
('SP8', 'SIZE4', 0),
('SP9', 'SIZE1', 0),
('SP9', 'SIZE2', 0),
('SP9', 'SIZE3', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucnang`
--

CREATE TABLE `chucnang` (
  `MACHUCNANG` varchar(10) NOT NULL,
  `TENCHUCNANG` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chucnang`
--

INSERT INTO `chucnang` (`MACHUCNANG`, `TENCHUCNANG`) VALUES
('HD', 'Hoá đơn'),
('KH', 'Khách hàng'),
('LOAI', 'Loại'),
('NCC', 'Nhà cung cấp'),
('NULLThK', 'Thống kê'),
('NV', 'Nhân viên'),
('PN', 'Phiếu nhập'),
('PQ', 'Phân quyền'),
('QLK', 'Kho'),
('SIZE', 'Size'),
('SP', 'Sản phẩm'),
('TK', 'Tài khoản');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hinh`
--

CREATE TABLE `hinh` (
  `TENHINH` varchar(50) NOT NULL,
  `MASP` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hinh`
--

INSERT INTO `hinh` (`TENHINH`, `MASP`) VALUES
('ao_hoodie_mau_hot.jpg', 'SP5'),
('ao_hoodie_phoi.jpg', 'SP4'),
('ao_hoodie_zip.jpg', 'SP6'),
('ao_ni_dai_tay.jpg', 'SP7'),
('ao_ni_nam_dai_tay.jpg', 'SP8'),
('asm_oxford_ke_soc_1.jpg', 'SP3'),
('asm_oxford_ke_soc_2.jpg', 'SP3'),
('asm_soc_1.jpg', 'SP2'),
('asm_soc_2.jpg', 'SP2'),
('asm_tay_dai.jpg', 'SP1'),
('didika_ao_len_1.jpg', 'SP10'),
('didita_ao_len_2.jpg', 'SP10'),
('lovita_ao_khoac_1.jpg', 'SP9'),
('lovita_ao_khoac_2.jpg', 'SP9');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `SOHD` varchar(10) NOT NULL,
  `NGAYHD` date NOT NULL,
  `MAKH` int(11) NOT NULL,
  `MANV` varchar(10) NOT NULL,
  `TONGTIEN` double NOT NULL,
  `TIENGIAMGIA` double DEFAULT NULL,
  `THOIGIAN` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`SOHD`, `NGAYHD`, `MAKH`, `MANV`, `TONGTIEN`, `TIENGIAMGIA`, `THOIGIAN`) VALUES
('HD0', '2024-09-05', 1, 'AD1', 1496000, 0, '19:33:07'),
('HD1', '2024-09-05', 2, 'AD1', 352000, 0, '19:34:03'),
('HD2', '2024-09-06', 3, 'AD1', 528000, 0, '11:32:21');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MAKH` int(11) NOT NULL,
  `TENKH` varchar(50) NOT NULL,
  `SDT` varchar(11) NOT NULL,
  `DIEMTICHLUY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MAKH`, `TENKH`, `SDT`, `DIEMTICHLUY`) VALUES
(1, 'Tuấn Khùng', '0908112345', 149),
(2, 'Quỳnh Quỳnh', '0909112443', 35),
(3, 'Trịnh Trần Phương Tuấn', '0907545661', 52),
(4, 'Hà Lê', '0909887123', 0),
(5, 'Hương Võ', '0909700813', 0),
(6, 'Yến Hoàng', '0907661234', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai`
--

CREATE TABLE `loai` (
  `MALOAI` varchar(10) NOT NULL,
  `TENLOAI` varchar(50) NOT NULL,
  `TINHTRANG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loai`
--

INSERT INTO `loai` (`MALOAI`, `TENLOAI`, `TINHTRANG`) VALUES
('LOAI1', 'Áo flannel', 1),
('LOAI2', 'Áo Hoodie', 1),
('LOAI3', 'Áo Khoác', 1),
('LOAI4', 'Áo Len', 0),
('LOAI5', 'Áo Nỉ', 1),
('LOAI6', 'Áo sơmi', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MANCC` varchar(10) NOT NULL,
  `TENNCC` varchar(30) NOT NULL,
  `SDT` int(10) NOT NULL,
  `TRANGTHAI` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MANCC`, `TENNCC`, `SDT`, `TRANGTHAI`) VALUES
('NCC1', 'MLB', 901123444, 1),
('NCC2', 'Supreme', 907345556, 1),
('NCC3', 'OFF-WHITE ', 909557689, 1),
('NCC4', 'LEVIS', 907112580, 1),
('NCC5', 'Sakura', 901558739, 1),
('NCC6', 'Esstensial', 173453789, 1),
('NCC7', 'Bad rabbits', 908223112, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MANV` varchar(10) NOT NULL,
  `TENNV` varchar(50) NOT NULL,
  `CHUCVU` varchar(50) NOT NULL,
  `SDT` int(11) NOT NULL,
  `DIACHI` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `TRANGTHAI` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MANV`, `TENNV`, `CHUCVU`, `SDT`, `DIACHI`, `EMAIL`, `TRANGTHAI`) VALUES
('AD1', 'Thanh Sang', 'Quản lí ứng dụng', 907665456, '273 An Dương Vương Quận 5 TPHCM', 'ngthanhsangsgu@gmail.com', 1),
('NV1', 'Thanh Phương', 'Nhân viên bán hàng', 909332432, '18 Nguyễn Tri Phương Quận 8 TPHCM', 'thanhphuong22@gmail.com', 1),
('NV2', 'Nhật Long', 'Nhân viên bán hàng', 908332112, '23/1 Lê Hồng Phong TPHCM', 'ntLong@gmail.com', 1),
('NV3', 'Oanh Le', 'Nhân viên bán hàng', 907665512, 'Quận 2 TPHCM', 'Oanhle204@gmail.com', 1),
('QL1', 'Phương Uyên', 'Quản lí kho', 338653321, 'Quận 3 TPHCM', 'phuongUyen11@gmail.com', 1),
('QL2', 'Trí Anh', 'Quản lí bán hàng', 901132445, 'Quận 5 TPHCM', 'triANH@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MAPN` varchar(10) NOT NULL,
  `MANV` varchar(10) NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` double NOT NULL,
  `MANCC` varchar(10) NOT NULL,
  `DEM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MAPN`, `MANV`, `NGAYNHAP`, `TONGTIEN`, `MANCC`, `DEM`) VALUES
('PN001', 'AD1', '2024-09-05', 15100000, 'NCC1', 10),
('PN011', 'AD1', '2024-09-06', 3460000, 'NCC1', 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `MAQUYEN` varchar(10) NOT NULL,
  `TENQUYEN` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`MAQUYEN`, `TENQUYEN`) VALUES
('QNV', 'Quyền nhân viên'),
('QQLBH', 'Quyền quản lí bán hàng'),
('QQLHT', 'Quyền quản lí hệ thống'),
('QQLK', 'Quyền quản lí kho');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` varchar(10) NOT NULL,
  `MALOAI` varchar(10) NOT NULL,
  `PRICE` double NOT NULL,
  `TENSP` varchar(50) NOT NULL,
  `TRANGTHAI` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `MALOAI`, `PRICE`, `TENSP`, `TRANGTHAI`) VALUES
('SP1', 'LOAI6', 264000, 'Áo sơ mi dài tay Odin Club', 1),
('SP10', 'LOAI4', 129900, 'DIDIKA Áo Len Tay Dài Cổ Tròn Dáng Rộng', 0),
('SP2', 'LOAI6', 275000, 'Áo sơ mi sọc Boizclub Premium', 1),
('SP3', 'LOAI6', 418000, 'Áo Sơ Mi Oxford Kẻ Sọc', 1),
('SP4', 'LOAI2', 275000, 'Áo Khoác Hoodie Phối Zip INTERBREAK', 1),
('SP5', 'LOAI2', 89000, 'ÁO KHOÁC HOODIE MẪU HOT TREND ESTIAL', 1),
('SP6', 'LOAI2', 352000, 'Áo Hoodie Zip', 1),
('SP7', 'LOAI5', 75000, 'ÁO NỈ DÀI TAY NAM NỮ', 1),
('SP8', 'LOAI5', 78600, 'Áo Nỉ Nam Dài Tay', 1),
('SP9', 'LOAI3', 156000, 'Lovito Áo khoác dài tay có nút trơn', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `size`
--

CREATE TABLE `size` (
  `MASIZE` varchar(10) NOT NULL,
  `TENSIZE` varchar(50) NOT NULL,
  `TRANGTHAI` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `size`
--

INSERT INTO `size` (`MASIZE`, `TENSIZE`, `TRANGTHAI`) VALUES
('SIZE1', 'S', 1),
('SIZE2', 'M', 1),
('SIZE3', 'L', 1),
('SIZE4', 'XL', 1),
('SIZE5', 'onesize', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MANV` varchar(10) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `NGAYTAOTK` date NOT NULL,
  `TINHTRANG` int(11) NOT NULL,
  `MAQUYEN` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MANV`, `USERNAME`, `PASSWORD`, `NGAYTAOTK`, `TINHTRANG`, `MAQUYEN`) VALUES
('AD1', 'AD1', 'SangHard!', '2023-02-13', 1, 'QQLHT'),
('NV1', 'NV1', 'Phuong1234', '2024-05-17', 1, 'QNV'),
('NV2', 'NV2', 'anhLong1111', '2023-11-20', 0, 'QNV'),
('NV3', 'NV3', 'Oanh2004!', '2023-10-22', 1, 'QNV'),
('QL1', 'QL1', 'PhuongUyen!', '2023-09-01', 1, 'QQLK'),
('QL2', 'QL2', 'TriAnhhh', '2023-06-22', 1, 'QQLBH');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`SOHD`,`MASP`,`MASIZE`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MAPN`,`MASP`,`MASIZE`);

--
-- Chỉ mục cho bảng `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD PRIMARY KEY (`MAQUYEN`,`MACHUCNANG`,`HANHDONG`);

--
-- Chỉ mục cho bảng `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD PRIMARY KEY (`MASP`,`MASIZE`);

--
-- Chỉ mục cho bảng `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`MACHUCNANG`);

--
-- Chỉ mục cho bảng `hinh`
--
ALTER TABLE `hinh`
  ADD PRIMARY KEY (`TENHINH`,`MASP`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`SOHD`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MAKH`);

--
-- Chỉ mục cho bảng `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`MALOAI`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MAPN`),
  ADD UNIQUE KEY `DEM` (`DEM`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`MAQUYEN`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`);

--
-- Chỉ mục cho bảng `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`MASIZE`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`USERNAME`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MAKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `DEM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
