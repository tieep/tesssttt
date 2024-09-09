-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2024 at 10:15 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlba`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `SOHD` varchar(10) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `DONGIA` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`SOHD`, `MASP`, `MASIZE`, `SOLUONG`, `DONGIA`) VALUES
('HD1', 'SP3', 'SIZE1', 2, 152000),
('HD1', 'SP4', 'SIZE5', 4, 189000),
('HD10', 'SP8', 'SIZE1', 1, 78600),
('HD11', 'SP2', 'SIZE5', 1, 202000),
('HD2', 'SP7', 'SIZE5', 10, 75000),
('HD3', 'SP1', 'SIZE2', 3, 139000),
('HD3', 'SP2', 'SIZE3', 2, 202000),
('HD3', 'SP4', 'SIZE2', 3, 189000),
('HD3', 'SP5', 'SIZE3', 2, 89000),
('HD4', 'SP10', 'SIZE1', 2, 129900),
('HD4', 'SP9', 'SIZE4', 2, 156000),
('HD5', 'SP1', 'SIZE5', 1, 139000),
('HD5', 'SP4', 'SIZE5', 3, 189000),
('HD6', 'SP1', 'SIZE5', 1, 139000),
('HD6', 'SP3', 'SIZE5', 2, 152000),
('HD6', 'SP5', 'SIZE1', 1, 89000),
('HD6', 'SP9', 'SIZE1', 2, 156000),
('HD7', 'SP6', 'SIZE5', 1, 169000),
('HD8', 'SP4', 'SIZE5', 1, 189000),
('HD8', 'SP5', 'SIZE1', 2, 89000),
('HD9', 'SP4', 'SIZE5', 2, 189000);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
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
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MAPN`, `MASP`, `SOLUONG`, `GIANHAP`, `THANHTIEN`, `MASIZE`) VALUES
('PN001', 'SP9', 1, 4000000, 4000000, 'SIZE2'),
('PN002', 'SP1', 6, 8500000, 51000000, 'SIZE5'),
('PN003', 'SP2', 2, 6400000, 12800000, 'SIZE5'),
('PN004', 'SP2', 2, 6400000, 12800000, 'SIZE5'),
('PN005', 'SP3', 3, 2500000, 7500000, 'SIZE5'),
('PN006', 'SP4', 3, 200000, 600000, 'SIZE5'),
('PN006', 'SP5', 3, 2000000, 6000000, 'SIZE1'),
('PN006', 'SP5', 3, 2000000, 6000000, 'SIZE2'),
('PN006', 'SP5', 3, 2000000, 6000000, 'SIZE3'),
('PN006', 'SP6', 5, 1500000, 7500000, 'SIZE5'),
('PN006', 'SP7', 9, 170000, 1530000, 'SIZE5');

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `MAQUYEN` varchar(10) NOT NULL,
  `MACHUCNANG` varchar(10) NOT NULL,
  `HANHDONG` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietquyen`
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
-- Table structure for table `chitietsanpham`
--

CREATE TABLE `chitietsanpham` (
  `MASP` varchar(10) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietsanpham`
--

INSERT INTO `chitietsanpham` (`MASP`, `MASIZE`, `SOLUONG`) VALUES
('SP1', 'SIZE5', 4),
('SP10', 'SIZE2', 3),
('SP10', 'SIZE3', 4),
('SP2', 'SIZE5', 3),
('SP3', 'SIZE5', 1),
('SP4', 'SIZE5', 4),
('SP5', 'SIZE1', 2),
('SP5', 'SIZE2', 7),
('SP5', 'SIZE3', 3),
('SP6', 'SIZE5', 4),
('SP7', 'SIZE5', 0),
('SP8', 'SIZE1', 3),
('SP8', 'SIZE2', 7),
('SP8', 'SIZE3', 6),
('SP8', 'SIZE4', 3),
('SP9', 'SIZE1', 0),
('SP9', 'SIZE2', 7),
('SP9', 'SIZE3', 4);

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `MACHUCNANG` varchar(10) NOT NULL,
  `TENCHUCNANG` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
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
('SIZE', 'Size'),
('SP', 'Sản phẩm'),
('TK', 'Tài khoản');

-- --------------------------------------------------------

--
-- Table structure for table `hinh`
--

CREATE TABLE `hinh` (
  `TENHINH` varchar(50) NOT NULL,
  `MASP` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hinh`
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
-- Table structure for table `hoadon`
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
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`SOHD`, `NGAYHD`, `MAKH`, `MANV`, `TONGTIEN`, `TIENGIAMGIA`, `THOIGIAN`) VALUES
('HD1', '2023-08-13', 3, 'NV3', 960000, 100000, '10:00:00'),
('HD10', '2023-05-17', 1, 'NV1', 78600, 0, '08:03:34'),
('HD11', '2023-05-17', 1, 'AD1', 202000, 0, '08:04:59'),
('HD2', '2023-07-22', 6, 'NV3', 750000, 0, '10:30:20'),
('HD3', '2024-05-09', 2, 'NV1', 1566000, 0, '15:20:10'),
('HD4', '2023-05-09', 1, 'NV2', 571800, 0, '09:00:01'),
('HD5', '2024-01-18', 5, 'AD1', 426000, 280000, '09:43:53'),
('HD6', '2024-02-21', 6, 'NV2', 664000, 180000, '12:55:12'),
('HD7', '2024-05-12', 3, 'NV3', 169000, 0, '10:57:11'),
('HD8', '2024-08-17', 2, 'NV2', 178000, 0, '11:57:58'),
('HD9', '2024-09-07', 5, 'NV3', 378000, 0, '15:58:32');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MAKH` int(11) NOT NULL,
  `TENKH` varchar(50) NOT NULL,
  `SDT` varchar(11) NOT NULL,
  `DIEMTICHLUY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MAKH`, `TENKH`, `SDT`, `DIEMTICHLUY`) VALUES
(1, 'Tuấn Khùng', '0908112345', 257),
(2, 'Quỳnh Quỳnh', '0909112443', 217),
(3, 'Trịnh Trần Phương Tuấn', '0907545661', 220),
(4, 'Hà Lê', '0909887123', 220),
(5, 'Hương Võ', '0909700813', 387),
(6, 'Yến Hoàng', '0907661234', 264);

-- --------------------------------------------------------

--
-- Table structure for table `loai`
--

CREATE TABLE `loai` (
  `MALOAI` varchar(10) NOT NULL,
  `TENLOAI` varchar(50) NOT NULL,
  `TINHTRANG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loai`
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
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MANCC` varchar(10) NOT NULL,
  `TENNCC` varchar(30) NOT NULL,
  `SDT` int(10) NOT NULL,
  `TRANGTHAI` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MANCC`, `TENNCC`, `SDT`, `TRANGTHAI`) VALUES
('NCC1', 'MLB', 901123444, 1),
('NCC2', 'Supreme', 907345556, 1),
('NCC3', 'OFF-WHITE ', 909557689, 1),
('NCC4', 'LEVIS', 907112580, 1),
('NCC5', 'Sakura', 901558739, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
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
-- Dumping data for table `nhanvien`
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
-- Table structure for table `phieunhap`
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
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MAPN`, `MANV`, `NGAYNHAP`, `TONGTIEN`, `MANCC`, `DEM`) VALUES
('PN001', 'NV1', '2024-06-05', 4000000, 'NCC1', 1),
('PN002', 'NV2', '2024-06-05', 51000000, 'NCC2', 2),
('PN003', 'NV3', '2024-04-03', 12800000, 'NCC3', 3),
('PN004', 'NV3', '2024-01-25', 12800000, 'NCC3', 4),
('PN005', 'QL1', '2024-02-11', 7500000, 'NCC4', 5),
('PN006', 'QL1', '2024-05-12', 27630000, 'NCC5', 6);

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `MAQUYEN` varchar(10) NOT NULL,
  `TENQUYEN` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`MAQUYEN`, `TENQUYEN`) VALUES
('QNV', 'Quyền nhân viên'),
('QQLBH', 'Quyền quản lí bán hàng'),
('QQLHT', 'Quyền quản lí hệ thống'),
('QQLK', 'Quyền quản lí kho');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` varchar(10) NOT NULL,
  `MALOAI` varchar(10) NOT NULL,
  `PRICE` double NOT NULL,
  `TENSP` varchar(50) NOT NULL,
  `TRANGTHAI` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `MALOAI`, `PRICE`, `TENSP`, `TRANGTHAI`) VALUES
('SP1', 'LOAI6', 139000, 'Áo sơ mi dài tay Odin Club', 1),
('SP10', 'LOAI4', 129900, 'DIDIKA Áo Len Tay Dài Cổ Tròn Dáng Rộng', 0),
('SP2', 'LOAI6', 202000, 'Áo sơ mi sọc Boizclub Premium', 1),
('SP3', 'LOAI6', 152000, 'Áo Sơ Mi Oxford Kẻ Sọc', 1),
('SP4', 'LOAI2', 189000, 'Áo Khoác Hoodie Phối Zip INTERBREAK', 1),
('SP5', 'LOAI2', 89000, 'ÁO KHOÁC HOODIE MẪU HOT TREND ESTIAL', 1),
('SP6', 'LOAI2', 169000, 'Áo Hoodie Zip', 1),
('SP7', 'LOAI5', 75000, 'ÁO NỈ DÀI TAY NAM NỮ', 1),
('SP8', 'LOAI5', 78600, 'Áo Nỉ Nam Dài Tay', 1),
('SP9', 'LOAI3', 156000, 'Lovito Áo khoác dài tay có nút trơn', 1);

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `MASIZE` varchar(10) NOT NULL,
  `TENSIZE` varchar(50) NOT NULL,
  `TRANGTHAI` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`MASIZE`, `TENSIZE`, `TRANGTHAI`) VALUES
('SIZE1', 'S', 1),
('SIZE2', 'M', 1),
('SIZE3', 'L', 1),
('SIZE4', 'XL', 1),
('SIZE5', 'onesize', 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
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
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MANV`, `USERNAME`, `PASSWORD`, `NGAYTAOTK`, `TINHTRANG`, `MAQUYEN`) VALUES
('AD1', 'AD1', 'SangHard!', '2023-02-13', 1, 'QQLHT'),
('NV1', 'NV1', 'Phuong1234', '2024-05-17', 1, 'QNV'),
('NV2', 'NV2', 'anhLong1111', '2023-11-20', 0, 'QNV'),
('NV3', 'NV3', 'Oanh2004!', '2023-10-22', 1, 'QNV'),
('QL1', 'QL1', 'PhuongUyen!', '2023-09-01', 1, 'QQLK'),
('QL2', 'QL2', 'TriAnhhh', '2023-06-22', 1, 'QQLBH');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`SOHD`,`MASP`,`MASIZE`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MAPN`,`MASP`,`MASIZE`);

--
-- Indexes for table `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD PRIMARY KEY (`MAQUYEN`,`MACHUCNANG`,`HANHDONG`);

--
-- Indexes for table `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD PRIMARY KEY (`MASP`,`MASIZE`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`MACHUCNANG`);

--
-- Indexes for table `hinh`
--
ALTER TABLE `hinh`
  ADD PRIMARY KEY (`TENHINH`,`MASP`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`SOHD`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MAKH`);

--
-- Indexes for table `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`MALOAI`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MAPN`),
  ADD UNIQUE KEY `DEM` (`DEM`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`MAQUYEN`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`MASIZE`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`USERNAME`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MAKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `DEM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
