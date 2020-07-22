-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-01-2020 a las 15:57:26
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_venta_pasajes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_comprobante`
--

CREATE TABLE `tb_comprobante` (
  `id_comprobante` int(11) NOT NULL,
  `tipo_documento` varchar(10) NOT NULL,
  `documento` varchar(12) NOT NULL,
  `nombre_razon` varchar(250) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  `telefono` int(11) NOT NULL,
  `observaciones` varchar(250) NOT NULL,
  `sub_total` decimal(11,2) NOT NULL,
  `igv` decimal(11,2) NOT NULL,
  `total` decimal(11,2) NOT NULL,
  `serie` varchar(10) DEFAULT NULL,
  `correlativo` varchar(15) DEFAULT NULL,
  `external_id` varchar(50) DEFAULT NULL,
  `link_pdf` varchar(200) DEFAULT NULL,
  `retorno_facturador` text DEFAULT NULL,
  `codigo_qr` text DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  `fechaventa` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_comprobante`
--

INSERT INTO `tb_comprobante` (`id_comprobante`, `tipo_documento`, `documento`, `nombre_razon`, `direccion`, `telefono`, `observaciones`, `sub_total`, `igv`, `total`, `serie`, `correlativo`, `external_id`, `link_pdf`, `retorno_facturador`, `codigo_qr`, `estado`, `fechaventa`) VALUES
(1, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '19.49', '3.51', '23.00', 'B001', '11', '5ae160aa-1c34-49fb-b847-beb82e7ff075', NULL, NULL, NULL, 1, '2020-01-07 22:05:54'),
(2, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '27.97', '5.03', '33.00', 'B001', '12', '79e9fd22-31ad-46f5-a4e0-698f9673f4f0', NULL, NULL, NULL, 1, '2020-01-07 22:06:38'),
(3, 'RUC', '20603844883', 'BYTE SOLUCIONES SOCIEDAD ANONIMA CERRADA- BYTE SOLUCIONES S.A.C.', 'CAL. LOS GORRIONES NRO. 104A URB. EL CARMEN AREQUIPA - AREQUIPA - AREQUIPA', 123, '', '27.12', '4.88', '32.00', 'F001', '2', '2d927c1d-da40-434e-87e1-3fe5de51adb2', NULL, NULL, NULL, 1, '2020-01-07 22:08:32'),
(4, 'RUC', '20603844883', 'BYTE SOLUCIONES SOCIEDAD ANONIMA CERRADA- BYTE SOLUCIONES S.A.C.', 'CAL. LOS GORRIONES NRO. 104A URB. EL CARMEN AREQUIPA - AREQUIPA - AREQUIPA', 123, '', '27.12', '4.88', '32.00', 'F001', '3', 'f7fe1201-cccf-4f83-8531-4088e8b8a33c', NULL, NULL, NULL, 1, '2020-01-07 22:08:37'),
(5, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '13', '0e74d3f2-bbda-42f4-8ec5-9627c02e0fe3', NULL, NULL, NULL, 1, '2020-01-07 22:09:49'),
(6, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, 'Pasaje Arequipa - Juliaca', '25.00', '0.00', '25.00', 'B001', '15', '86d53ade-b3f6-43e8-9d40-5565ba00df26', NULL, NULL, NULL, 1, '2020-01-07 22:42:33'),
(7, 'RUC', '20603844883', 'FABRICIO ANDRE VELA TEJADA', 'asdasadsdas', 0, 'Pasaje Arequipa - Juliaca', '25.00', '0.00', '25.00', 'F001', '4', '511ff279-4574-4fe3-a0a3-4fb37166f4b8', NULL, NULL, NULL, 1, '2020-01-07 22:44:03'),
(8, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '57.63', '10.37', '68.00', 'B001', '16', 'ed04bde9-f1f5-4104-bfb5-3f93217be730', NULL, NULL, NULL, 1, '2020-01-07 23:43:16'),
(9, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '60.17', '10.83', '71.00', 'B001', '17', '91bd63e0-b14d-4e23-b172-e955b265b3ca', NULL, NULL, NULL, 1, '2020-01-08 16:48:25'),
(10, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '19.49', '3.51', '23.00', 'B001', '18', 'a22e340f-0169-41e4-bd49-2f53bc02498a', NULL, NULL, NULL, 1, '2020-01-08 17:00:22'),
(11, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '19', 'b79ce880-4e38-44ad-8fc3-5ef15d2688ef', NULL, NULL, NULL, 1, '2020-01-08 17:03:30'),
(12, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '180.51', '32.49', '213.00', 'B001', '20', '461956ec-f2fe-4160-8afa-7d0423234199', NULL, NULL, NULL, 1, '2020-01-08 17:05:23'),
(13, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '21', 'a1ee698d-19a2-43fb-aef6-66d5fb1e0b32', NULL, NULL, NULL, 1, '2020-01-08 17:10:24'),
(14, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '22', '3d1f5173-5502-43c3-826d-fc1432b9ec3b', NULL, NULL, NULL, 1, '2020-01-08 17:11:11'),
(15, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '23', '384ae881-5492-4e24-a284-4127b04085c7', NULL, NULL, NULL, 1, '2020-01-08 17:13:03'),
(16, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '19.49', '3.51', '23.00', 'B001', '24', '44cb4aac-eaa5-404c-937a-41c7610d4524', NULL, NULL, NULL, 1, '2020-01-08 17:17:38'),
(17, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '25', 'c8711590-408e-4350-86fb-3bed6a89acc4', NULL, NULL, NULL, 1, '2020-01-08 17:19:34'),
(18, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '104.24', '18.76', '123.00', 'B001', '26', '6a84be38-6dc5-4577-8322-b308d4399223', NULL, NULL, NULL, 1, '2020-01-08 17:20:55'),
(19, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '160.17', '28.83', '189.00', 'B001', '27', '6100045f-fe1d-464a-8f3f-8a3954fbbe47', NULL, NULL, NULL, 1, '2020-01-08 17:21:21'),
(20, 'DNI', '73044100as', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '7.63', '1.37', '9.00', 'B001', '28', '4d9f3b71-e66e-4e12-b951-720f66ff4adb', NULL, NULL, NULL, 1, '2020-01-08 17:41:44'),
(21, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '5.08', '0.92', '6.00', 'B001', '29', '1163b27c-2abe-48a7-b53d-def710973e8c', NULL, NULL, NULL, 1, '2020-01-08 17:44:37'),
(22, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '6.78', '1.22', '8.00', 'B001', '30', 'fadf086c-646c-4082-baec-9141d5783572', NULL, NULL, NULL, 1, '2020-01-08 17:49:32'),
(23, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '11.86', '2.14', '14.00', 'B001', '31', '3a2e8bdf-8d8a-47e1-a79f-c13a676498fc', NULL, NULL, NULL, 1, '2020-01-08 17:55:05'),
(24, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '226.27', '40.73', '267.00', 'B001', '32', '5f318e5a-4701-4a0f-820f-f60f50ce54ca', NULL, NULL, NULL, 1, '2020-01-08 18:04:12'),
(25, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '51.69', '9.31', '61.00', 'B001', '33', '2fadee85-b31a-4a0a-a4dd-d1bdf1cb6597', NULL, NULL, NULL, 1, '2020-01-08 19:59:50'),
(26, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '200.00', '36.00', '236.00', 'B001', '35', '632092c0-ca1f-4f04-8154-ddc8681e6c92', NULL, NULL, NULL, 1, '2020-01-08 20:51:05'),
(27, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '7.63', '1.37', '9.00', 'B001', '36', 'b686040c-06cf-479a-8ba5-f4002ba16720', NULL, NULL, NULL, 1, '2020-01-08 20:53:45'),
(28, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '7.63', '1.37', '9.00', 'B001', '37', '6bf228e7-7f18-436e-8bf5-bebcc2486202', NULL, NULL, NULL, 1, '2020-01-08 20:59:46'),
(29, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, 'asdsadass', '1.69', '0.31', '2.00', 'B001', '38', '6de6ce3d-df27-4b5e-8e1e-522824774740', NULL, NULL, NULL, 1, '2020-01-08 21:00:17'),
(30, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '16.95', '3.05', '20.00', 'B001', '50', '0366a247-6a3d-467e-a675-741c99296089', NULL, NULL, NULL, 1, '2020-01-09 17:05:32'),
(31, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, 'Pasaje Arequipa - Espinar', '25.00', '0.00', '25.00', 'B001', '51', '04633ecb-c7f3-4be6-ad4d-425237317acc', NULL, NULL, NULL, 1, '2020-01-09 17:08:25'),
(32, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, '', '19.49', '3.51', '23.00', 'B001', '53', '177d8170-0be1-4b7c-ab89-67bec8cdb64a', NULL, NULL, NULL, 1, '2020-01-09 18:15:56'),
(33, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, 'Pasaje Arequipa - Espinar', '25.00', '0.00', '25.00', 'B001', '58', '2a0bd63e-2dc2-4066-a155-c9ddc69917df', NULL, NULL, NULL, 1, '2020-01-09 18:26:16'),
(34, 'DNI', '73044100', 'FABRICIO ANDRE VELA TEJADA', '', 0, 'Pasaje Arequipa - Espinar', '25.00', '0.00', '25.00', 'B001', '59', '5399e6a2-ffc0-4e7c-9385-c1a8d9075f23', NULL, NULL, 'iVBORw0KGgoAAAANSUhEUgAAAJYAAACWCAIAAACzY+a1AAAACXBIWXMAAA7EAAAOxAGVKw4bAAAEcklEQVR4nO2d247bMAxEN0X//5fThy6CANGyQ3Fsd+BzHhNb0mZAXUiK+3g+n1+QzK+rBwBTkDAeJIwHCeNBwniQMB4kjAcJ40HCeJAwHiSMBwnj+S0+93g85p29u9RfDb4+fO9i+WG3ZQv1mJfYf6sarDAeJIxHnUhfbMQXlxNLPVXWc9HnGOpJWJ/Aiy42cP1WNVhhPEgYDxLG014L3+muWK53J9Qti6us+O1PT3ZHVYMVxoOE8Ywm0hOoDwYv6m/rY4M+iXn9Pi6wwniQMJ7rJ9LlPNb1busu8smusvb7XAVWGA8SxoOE8YzWwslKUK9Jn49tBISXj30uY/piLIap68HYwQrjQcJ42hOpy0NR78stYVvRs1NPhpOsnHO8OVhhPEgYz+N/8C/8Rc9/Wb5S4Pobr4px1mCF8SBhPEgYj7oWTladycGg7sWevGNZgyevbMRAsMJ4kDAeT0L+xsTSDa7W3bnyX8RvXT+C2HINVhgPEsZjdnPrk4l3Utq4nST2O/l7l0/anThYYTxIGA8SxqOuhWJypuseUHfBOCdK0A0Iu36NGqwwHiSMpx3y3Tg2iBznIp9MU5NqOPVgNkLcS7DCeJAwnnYBr0kQTm9QnKnENEOxkeVgJi6e5ajsbhqsMB4kjAcJ4xnlznTXRdexwZLvY/EobeT7iC3rYIXxIGE8noT8yTRrOXLoJ5MTSh5s/GnkztwaJIzHXF7dNaGJ092G37nr91lij55OwArjQcJ4kDAeT8h34umwH0hqLBGSDbprISHfG4GE8RxYwGty/0hp7WtrRhUPM8t+xWo4y9YmhcNqsMJ4kDAec+7Mkg1fhmXPKY5q+e5yurNEMe1ghfEgYTxIGI/nZpP4ir4FP45JhKQbLt44kBDyvSNIGI/5H8HWB4OJm9tSjeufo6oR3z35dIEVxoOE8YxyZ7xZ1Trd/dvEo6Q/Vt9dou4M/AgSxoOE8Zj/f6Fr9vfWI9BH1T0tuLYI3Gy6NUgYj6e8+qQgiyUbsW558i51Z+BwkDAeTxKi5VZ7HU1cvmvJYdGTASdXU8V+N8AK40HCeJAwnlHIV0xBr9eGjTvAV91OetHdBCyfrFd3HawwHiSMZ5SE+PnhxO3rSujrvquPSixIOVkalv3WYIXxIGE8nkqIk2/FLupRubamE3+TBXakdwQJ40HCeDzFLHc6Pqwg5SRtfpKuP4kwd1t7ByuMBwnjMRezrKmvW9bduZJoREfSht/n88M6z1FvsAYrjAcJ4zFfEV2ykSp4gpdE3JHq39Yt141w1/7WIGE8SBjPKI/Ucn1XDIHqmaLiAjmpO1M3eDJYYTxIGI/5iqjOpCrNpFyCpe5M3cvkMW423REkjOeyibTGXqqm6KLeCU9wpRnWYIXxIGE8SBjPgf9qROS4epAbaa6WKwbi3WPqzsA3SBhPeyI92Zm7sfWfpDd2HSuTyHDdL96ZG4GE8VyWzQ0usMJ4kDAeJIwHCeNBwniQMB4kjAcJ40HCeJAwHiSMBwnjQcJ4/gAhDsRIB5RbcAAAAABJRU5ErkJggg==', 1, '2020-01-09 18:45:14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_comprobantes_emitidos`
--

CREATE TABLE `tb_comprobantes_emitidos` (
  `nserie1` varchar(10) NOT NULL,
  `nserie2` varchar(10) NOT NULL,
  `idempresa` int(11) DEFAULT NULL,
  `idorigen` int(11) DEFAULT NULL,
  `iddestino` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `comprobante` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_comprobantes_emitidos`
--

INSERT INTO `tb_comprobantes_emitidos` (`nserie1`, `nserie2`, `idempresa`, `idorigen`, `iddestino`, `descripcion`, `importe`, `fecha`, `comprobante`) VALUES
('45', '454', 2, 1, 1, 'wewer', 25, '2019-12-28', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_conductor`
--

CREATE TABLE `tb_conductor` (
  `dniconductor` int(11) NOT NULL,
  `licencia` varchar(30) DEFAULT NULL,
  `conductor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_conductor`
--

INSERT INTO `tb_conductor` (`dniconductor`, `licencia`, `conductor`) VALUES
(76782001, 'F76782001', 'Conductor1'),
(76782002, 'F76782002', 'Conductor2'),
(76782003, 'F76782003', 'Conductor3'),
(76782004, 'F76782004', 'Conductor4'),
(76782005, 'F76782005', 'Conductor5'),
(76782006, 'F76782006', 'Conductor6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_configuracion_inicial`
--

CREATE TABLE `tb_configuracion_inicial` (
  `estado` int(11) NOT NULL,
  `idsede` int(11) DEFAULT NULL,
  `nserie` varchar(3) DEFAULT NULL,
  `nviajeinicial` int(11) DEFAULT NULL,
  `nboletoinicial` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_configuracion_inicial`
--

INSERT INTO `tb_configuracion_inicial` (`estado`, `idsede`, `nserie`, `nviajeinicial`, `nboletoinicial`) VALUES
(1, 1, '111', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_detalle_comprobante`
--

CREATE TABLE `tb_detalle_comprobante` (
  `id_detalle` int(11) NOT NULL,
  `id_comprobante` int(11) NOT NULL,
  `servicio` varchar(150) NOT NULL,
  `precio_unitario` decimal(11,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_total` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_detalle_comprobante`
--

INSERT INTO `tb_detalle_comprobante` (`id_detalle`, `id_comprobante`, `servicio`, `precio_unitario`, `cantidad`, `precio_total`) VALUES
(1, 1, 'prueba 1', '23.00', 1, '23.00'),
(2, 2, 'asdads', '33.00', 1, '33.00'),
(3, 3, 'asads', '32.00', 1, '32.00'),
(4, 4, 'asads', '32.00', 1, '32.00'),
(5, 5, 'asdas', '123.00', 1, '123.00'),
(6, 5, 'Boleto de Viaje', '25.00', 1, '25.00'),
(7, 6, 'Boleto de Viaje', '25.00', 1, '25.00'),
(8, 7, 'Boleto de Viaje', '25.00', 1, '25.00'),
(9, 8, 'caja 1', '12.00', 1, '12.00'),
(10, 8, 'caja negra', '23.00', 1, '23.00'),
(11, 8, 'caja blanca', '33.00', 1, '33.00'),
(12, 9, 'camarotes', '23.00', 2, '46.00'),
(13, 9, 'caja negra', '10.00', 1, '10.00'),
(14, 9, 'saco rojo', '15.00', 1, '15.00'),
(15, 10, 'asdads', '23.00', 1, '23.00'),
(16, 11, 'asdasd', '123.00', 1, '123.00'),
(17, 12, 'sdsddfsdf1', '213.00', 1, '213.00'),
(18, 13, 'asdas', '123.00', 1, '123.00'),
(19, 14, 'asda', '123.00', 1, '123.00'),
(20, 15, 'addsa', '123.00', 1, '123.00'),
(21, 16, 'asdda', '23.00', 1, '23.00'),
(22, 17, 'adas', '123.00', 1, '123.00'),
(23, 18, 'asads', '123.00', 1, '123.00'),
(24, 19, 'asdas', '123.00', 1, '123.00'),
(25, 19, 'w3234432', '34.00', 1, '34.00'),
(26, 19, 'fdffggd', '32.00', 1, '32.00'),
(27, 20, 'caja 1', '2.00', 1, '2.00'),
(28, 20, 'caja 2', '3.00', 1, '3.00'),
(29, 20, 'caja 4', '4.00', 1, '4.00'),
(30, 21, 'caja 1', '1.00', 1, '1.00'),
(31, 21, 'caja 2', '2.00', 1, '2.00'),
(32, 21, 'caja 3', '3.00', 1, '3.00'),
(33, 22, 'asd', '2.00', 1, '2.00'),
(34, 22, 'qeew', '2.00', 1, '2.00'),
(35, 22, 'dgfg', '4.00', 1, '4.00'),
(36, 23, 'asd', '2.00', 1, '2.00'),
(37, 23, 'rwer', '3.00', 1, '3.00'),
(38, 23, 'ddf', '4.00', 1, '4.00'),
(39, 23, 'asd', '5.00', 1, '5.00'),
(40, 24, 'sdfs', '123.00', 1, '123.00'),
(41, 24, 'dass', '44.00', 1, '44.00'),
(42, 24, 'vccb', '45.00', 1, '45.00'),
(43, 24, 'sdfs', '55.00', 1, '55.00'),
(44, 25, 'qweqwe', '23.00', 1, '23.00'),
(45, 25, 'gtbbbb', '34.00', 1, '34.00'),
(46, 25, 'hhhhh', '4.00', 1, '4.00'),
(47, 26, 'saasdd', '23.00', 1, '23.00'),
(48, 26, 'asddwer', '213.00', 1, '213.00'),
(49, 27, 'were', '2.00', 1, '2.00'),
(50, 27, 'dfwfr', '3.00', 1, '3.00'),
(51, 27, 'sdfsdfd', '4.00', 1, '4.00'),
(52, 28, 'adsads', '2.00', 1, '2.00'),
(53, 28, 'sffdf', '3.00', 1, '3.00'),
(54, 28, 'ddfgdfg', '4.00', 1, '4.00'),
(55, 29, 'asdda', '2.00', 1, '2.00'),
(56, 30, 'kjhjkb', '20.00', 1, '20.00'),
(57, 31, 'Boleto de Viaje', '25.00', 1, '25.00'),
(58, 32, 'assad', '23.00', 1, '23.00'),
(59, 32, 'Boleto de Viaje', '25.00', 1, '25.00'),
(60, 32, 'Boleto de Viaje', '25.00', 1, '25.00'),
(61, 32, 'Boleto de Viaje', '25.00', 1, '25.00'),
(62, 33, 'Boleto de Viaje', '25.00', 1, '25.00'),
(63, 34, 'Boleto de Viaje', '25.00', 1, '25.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_detalle_viaje`
--

CREATE TABLE `tb_detalle_viaje` (
  `nviaje` int(11) NOT NULL,
  `nboleto` int(11) NOT NULL,
  `dnipasajero` int(11) DEFAULT NULL,
  `asiento` int(11) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `prepasaje` float DEFAULT NULL,
  `contratante` int(11) DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  `id_comprobante` int(11) DEFAULT NULL,
  `fechaventa` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_detalle_viaje`
--

INSERT INTO `tb_detalle_viaje` (`nviaje`, `nboleto`, `dnipasajero`, `asiento`, `edad`, `prepasaje`, `contratante`, `estado`, `id_comprobante`, `fechaventa`) VALUES
(2, 1, 73044100, 13, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(2, 2, 73044102, 10, 17, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(2, 3, 73044100, 16, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(3, 4, 10551866, 9, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(4, 5, 73044100, 6, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 6, 73044100, 9, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 7, 73044100, 12, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 8, 73044100, 15, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 9, 73044100, 19, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 10, 73044100, 18, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 11, 73044100, 17, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 12, 73044100, 16, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 13, 73044100, 14, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 14, 73044100, 11, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 15, 73044100, 6, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 16, 73044115, 3, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 17, 73044100, 8, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 18, 73044100, 2, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 19, 73044100, 5, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 20, 73044100, 20, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 21, 73044100, 1, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 22, 73044100, 4, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 23, 73044100, 7, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(6, 24, 73044100, 10, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 25, 73044100, 20, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 26, 73044000, 5, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 27, 73044100, 9, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 28, 73044100, 12, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 29, 73044100, 6, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 30, 73044100, 3, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 31, 73044100, 15, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 32, 73044100, 19, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 33, 73044100, 2, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 34, 73044100, 8, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 35, 73044100, 11, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 36, 73044100, 18, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 37, 73044100, 14, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(7, 38, 73044100, 17, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(8, 39, 73044100, 16, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(8, 40, 73044100, 13, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(8, 41, 73044100, 10, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(8, 42, 73044100, 7, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(8, 43, 73044100, 4, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(8, 44, 73044100, 1, 0, 25, 0, 1, NULL, '2020-01-02 04:34:00'),
(9, 47, 73044100, 28, 0, 20, 0, 1, NULL, '2020-01-02 04:34:00'),
(9, 48, 73044100, 20, 0, 20, 0, 1, NULL, '2020-01-02 04:34:00'),
(9, 49, 73044100, 36, 0, 20, 0, 1, NULL, '2020-01-02 04:34:00'),
(9, 50, 73044100, 24, 0, 20, 0, 1, NULL, '2020-01-02 04:40:09'),
(9, 51, 73044100, 32, 0, 20, 0, 1, NULL, '2020-01-02 04:40:41'),
(9, 53, 73044100, 40, 0, 20, 0, 1, NULL, '2020-01-02 04:50:43'),
(9, 54, 73044100, 12, 0, 20, 0, 1, NULL, '2020-01-02 04:53:38'),
(9, 55, 73044100, 19, 0, 20, 0, 1, NULL, '2020-01-02 05:00:22'),
(9, 56, 73044100, 23, 0, 20, 0, 1, NULL, '2020-01-02 05:03:01'),
(9, 57, 73044100, 27, 0, 20, 0, 1, NULL, '2020-01-02 05:06:00'),
(9, 58, 73044100, 31, 0, 20, 0, 1, NULL, '2020-01-02 05:08:28'),
(9, 59, 73044100, 15, 24, 20, 0, 1, NULL, '2020-01-02 14:39:48'),
(9, 60, 73044100, 16, 0, 20, 0, 1, NULL, '2020-01-02 17:26:33'),
(10, 61, 73044100, 24, 0, 25, 0, 1, NULL, '2020-01-06 20:03:03'),
(10, 62, 10551866, 20, 0, 25, 0, 1, NULL, '2020-01-06 20:50:52'),
(10, 63, 73044100, 16, 0, 25, 0, 1, NULL, '2020-01-06 20:53:13'),
(10, 64, 73044100, 12, 0, 25, 0, 1, NULL, '2020-01-06 20:57:13'),
(10, 65, 73044100, 8, 0, 25, 0, 1, NULL, '2020-01-06 21:00:59'),
(10, 66, 73044100, 28, 0, 25, 0, 1, NULL, '2020-01-06 22:00:18'),
(10, 67, 73044100, 32, 0, 25, 0, 1, NULL, '2020-01-06 22:02:23'),
(10, 68, 73044100, 36, 0, 25, 0, 1, NULL, '2020-01-07 18:04:56'),
(10, 69, 73044100, 40, 0, 25, 0, 1, NULL, '2020-01-07 22:03:33'),
(10, 70, 73044100, 44, 0, 25, 0, 1, NULL, '2020-01-07 22:05:11'),
(10, 71, 73044100, 49, 0, 25, 0, 1, NULL, '2020-01-07 22:05:28'),
(10, 72, 73044100, 4, 0, 25, 0, 1, NULL, '2020-01-07 22:39:01'),
(10, 73, 73044100, 15, 0, 25, 0, 1, NULL, '2020-01-07 22:42:26'),
(10, 74, 73044100, 19, 0, 25, 0, 1, NULL, '2020-01-07 22:44:01'),
(10, 75, 73044100, 11, 0, 25, 0, 1, NULL, '2020-01-07 22:44:55'),
(11, 77, 73044100, 12, 0, 25, 0, 1, NULL, '2020-01-09 18:10:01'),
(11, 78, 73044100, 16, 0, 25, 0, 1, NULL, '2020-01-09 18:15:33'),
(11, 79, 73044100, 20, 0, 25, 0, 1, NULL, '2020-01-09 18:22:16'),
(11, 80, 73044100, 8, 0, 25, 0, 1, 32, '2020-01-09 18:22:31'),
(11, 81, 73044100, 24, 0, 25, 0, 1, 32, '2020-01-09 18:23:04'),
(11, 82, 73044100, 28, 0, 25, 0, 1, 32, '2020-01-09 18:23:38'),
(11, 83, 73044100, 32, 0, 25, 0, 1, 33, '2020-01-09 18:26:11'),
(12, 76, 73044100, 19, 0, 25, 0, 1, 31, '2020-01-09 17:08:24'),
(12, 84, 73044100, 24, 0, 25, 0, 1, 34, '2020-01-09 18:45:08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_detalle_viaje_otros`
--

CREATE TABLE `tb_detalle_viaje_otros` (
  `nviaje` int(11) NOT NULL,
  `standar` int(11) DEFAULT NULL,
  `escalacom` int(11) DEFAULT NULL,
  `ciudaddesde` varchar(50) DEFAULT NULL,
  `ciudadhasta` varchar(50) DEFAULT NULL,
  `puntoencuentro` varchar(60) DEFAULT NULL,
  `escalas` varchar(150) DEFAULT NULL,
  `dniconductor1` int(11) DEFAULT NULL,
  `horainicio1` varchar(5) DEFAULT NULL,
  `horainicio2` varchar(5) DEFAULT NULL,
  `dniconductor2` int(11) DEFAULT NULL,
  `horafin1` varchar(5) DEFAULT NULL,
  `horafin2` varchar(5) DEFAULT NULL,
  `modalidad` int(11) DEFAULT NULL,
  `totalmodif` float DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_detalle_viaje_otros`
--

INSERT INTO `tb_detalle_viaje_otros` (`nviaje`, `standar`, `escalacom`, `ciudaddesde`, `ciudadhasta`, `puntoencuentro`, `escalas`, `dniconductor1`, `horainicio1`, `horainicio2`, `dniconductor2`, `horafin1`, `horafin2`, `modalidad`, `totalmodif`, `usuario`) VALUES
(1, 1, 0, NULL, NULL, NULL, NULL, 76782006, '00:00', NULL, 0, NULL, '00:00', 0, -1, 'ADMINISTRADOR'),
(2, 1, 0, 'Arequipa', 'Juliaca', 'Terminal de Arequipa', 'Sin escalas ni paradas.', 76782003, '02:10', '01:15', 76782002, '01:15', '1:15', 1, 50, 'ADMINISTRADOR'),
(3, 1, 0, NULL, NULL, NULL, NULL, 76782002, '03:15', NULL, 0, NULL, '00:00', 0, -1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_empresa`
--

CREATE TABLE `tb_empresa` (
  `idempresa` int(11) NOT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `empresa` varchar(60) DEFAULT NULL,
  `direccion` varchar(250) DEFAULT NULL,
  `ubigeo` varchar(10) DEFAULT NULL,
  `telefono` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `serie_boletas` varchar(10) DEFAULT NULL,
  `serie_facturas` varchar(10) DEFAULT NULL,
  `url_api` varchar(100) DEFAULT NULL,
  `token_api` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_empresa`
--

INSERT INTO `tb_empresa` (`idempresa`, `ruc`, `empresa`, `direccion`, `ubigeo`, `telefono`, `email`, `serie_boletas`, `serie_facturas`, `url_api`, `token_api`) VALUES
(1, '20406468677', 'Alex EIRL.', 'AV. JAVIER PEREZ DE CUELLAR NRO. 223 AREQUIPA - AREQUIPA - JACOBO HUNTER', NULL, NULL, NULL, 'B001', 'F001', 'https://bytesoluciones.info/', 'ePunyOYoAXNxic6fPl7njh1MNFPVjrS61AlV2wfgkuf9969SHm'),
(2, '20601642444', 'Byte Soluciones SAC', NULL, NULL, NULL, NULL, 'B002', 'F002', 'https://bytesoluciones.info/', 'ePunyOYoAXNxic6fPl7njh1MNFPVjrS61AlV2wfgkuf9969SHm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_gastos`
--

CREATE TABLE `tb_gastos` (
  `idgasto` int(11) NOT NULL,
  `nserie1` varchar(10) DEFAULT NULL,
  `nserie2` varchar(10) DEFAULT NULL,
  `idempresa` int(11) DEFAULT NULL,
  `idorigen` int(11) DEFAULT NULL,
  `iddestino` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `monto` float DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_modelo_vehiculo`
--

CREATE TABLE `tb_modelo_vehiculo` (
  `idmodelo` int(11) NOT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  `casientos` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_modelo_vehiculo`
--

INSERT INTO `tb_modelo_vehiculo` (`idmodelo`, `modelo`, `casientos`) VALUES
(1, 'Mercedes Sprinter 413 19+1', 20),
(2, 'Mercedes Sprinter 515 19+1', 20),
(3, 'Mercedes Sprinter 515 20+1', 21),
(4, 'Renault Master 2012 15', 15),
(5, 'Renault Master Moderna 15', 15),
(6, 'Wolkswagen Crafter 20+1', 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pasajero`
--

CREATE TABLE `tb_pasajero` (
  `dnipasajero` int(11) NOT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `fnacimiento` date DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `razsocial` varchar(80) DEFAULT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL,
  `direccion` varchar(80) DEFAULT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_pasajero`
--

INSERT INTO `tb_pasajero` (`dnipasajero`, `ruc`, `fnacimiento`, `nombre`, `razsocial`, `nacionalidad`, `direccion`, `telefono`) VALUES
(10551866, '', '2020-01-01', 'ROSA ESTHER TEJADA GANDARILLAS DE VELA', NULL, 'Perú', '', ''),
(73044000, '', '2019-01-01', 'MARCOS ADRIAN AGURTO YLLESCAS', NULL, 'Perú', '', ''),
(73044100, '', '2020-01-01', 'FABRICIO ANDRE VELA TEJADA', NULL, 'Perú', '', ''),
(73044102, '', '2002-02-02', 'carlos vera', NULL, 'Perú', '', ''),
(73044115, '', '2019-01-01', 'fgfdsfgdsf', NULL, 'Perú', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pasajeros_temporal`
--

CREATE TABLE `tb_pasajeros_temporal` (
  `asiento` int(11) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `nboleto` int(11) DEFAULT NULL,
  `dnipasajero` int(11) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `prepasaje` float DEFAULT NULL,
  `contratante` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_pasajeros_temporal`
--

INSERT INTO `tb_pasajeros_temporal` (`asiento`, `estado`, `nboleto`, `dnipasajero`, `edad`, `prepasaje`, `contratante`) VALUES
(20, 1, 45, 73044100, 0, 25, 0),
(39, 1, 46, 73044100, 0, 25, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_sedes`
--

CREATE TABLE `tb_sedes` (
  `idsede` int(11) NOT NULL,
  `sede` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_sedes`
--

INSERT INTO `tb_sedes` (`idsede`, `sede`) VALUES
(1, 'Arequipa'),
(2, 'Juliaca'),
(3, 'Espinar'),
(4, 'Sicuani');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_servicios`
--

CREATE TABLE `tb_servicios` (
  `id_servicio` int(11) NOT NULL,
  `detalle` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_socio`
--

CREATE TABLE `tb_socio` (
  `codsocio` int(11) NOT NULL,
  `idempresa` int(11) DEFAULT NULL,
  `dnisocio` int(11) NOT NULL,
  `nombresocio` varchar(70) DEFAULT NULL,
  `dniconductor` int(11) DEFAULT NULL,
  `placa` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_socio`
--

INSERT INTO `tb_socio` (`codsocio`, `idempresa`, `dnisocio`, `nombresocio`, `dniconductor`, `placa`) VALUES
(1001, 1, 76781001, 'Socio1', 76782001, 'AAA-111'),
(1002, 2, 76781002, 'Socio2', 76782002, 'BBB-222'),
(1003, 1, 76781003, 'Socio3', 76782003, 'CCC-333'),
(1004, 2, 76781004, 'Socio4', 76782004, 'DDD-444'),
(1005, 1, 76781005, 'Socio5', 76782005, 'EEE-555'),
(1006, 2, 76781006, 'Socio6', 76782006, 'FFF-666');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `usuario` varchar(20) NOT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`usuario`, `pass`, `nombre`, `tipo`) VALUES
('admin', 'admin', 'ADMINISTRADOR', 0),
('alex', 'Aa123', 'Alexander Gamarra', 1),
('bxb', 'stand207', 'BYTE X BYTE', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_vehiculo`
--

CREATE TABLE `tb_vehiculo` (
  `placa` varchar(7) NOT NULL,
  `idmodelo` int(11) DEFAULT NULL,
  `detalle` varchar(100) DEFAULT NULL,
  `mtc` varchar(20) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_vehiculo`
--

INSERT INTO `tb_vehiculo` (`placa`, `idmodelo`, `detalle`, `mtc`, `estado`) VALUES
('AAA-111', 1, 'Detalle1', 'MTC1', 0),
('BBB-222', 2, 'Detalle2', 'MTC2', 0),
('CCC-333', 3, 'Detalle3', 'MTC3', 0),
('DDD-444', 4, 'Detalle4', 'MTC4', 0),
('EEE-555', 5, 'Detalle5', 'MTC5', 0),
('FFF-666', 6, 'Detalle6', 'MTC6', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_venta_temporal`
--

CREATE TABLE `tb_venta_temporal` (
  `id` int(11) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `codsocio` int(11) DEFAULT NULL,
  `empresa` int(11) DEFAULT NULL,
  `dniconductor` int(11) DEFAULT NULL,
  `placa` varchar(7) DEFAULT NULL,
  `modelovh` int(11) DEFAULT NULL,
  `idorigen` int(11) DEFAULT NULL,
  `iddestino` int(11) DEFAULT NULL,
  `fpartida` datetime DEFAULT NULL,
  `fllegada` datetime DEFAULT NULL,
  `prepasaje` float DEFAULT NULL,
  `nviaje` int(11) DEFAULT NULL,
  `standar` int(11) DEFAULT NULL,
  `escalacom` int(11) DEFAULT NULL,
  `ciudaddesde` varchar(50) DEFAULT NULL,
  `ciudadhasta` varchar(50) DEFAULT NULL,
  `puntoencuentro` varchar(60) DEFAULT NULL,
  `escalas` varchar(150) DEFAULT NULL,
  `horainicio2` varchar(5) DEFAULT NULL,
  `dniconductor2` int(11) DEFAULT NULL,
  `licencia2` varchar(30) DEFAULT NULL,
  `horafin1` varchar(5) DEFAULT NULL,
  `horafin2` varchar(5) DEFAULT NULL,
  `modalidad` int(11) DEFAULT NULL,
  `totalmodif` float DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `verificarInfAdi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_viaje`
--

CREATE TABLE `tb_viaje` (
  `nviaje` int(11) NOT NULL,
  `codsocio` int(11) DEFAULT NULL,
  `empresa` int(11) DEFAULT NULL,
  `idorigen` int(11) DEFAULT NULL,
  `iddestino` int(11) DEFAULT NULL,
  `fpartida` datetime DEFAULT NULL,
  `fllegada` datetime DEFAULT NULL,
  `placa` varchar(7) DEFAULT NULL,
  `dniconductor` int(11) DEFAULT NULL,
  `prepasaje` float DEFAULT NULL,
  `totpasajes` float DEFAULT NULL,
  `totalasientos` int(11) DEFAULT NULL,
  `asientosven` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_viaje`
--

INSERT INTO `tb_viaje` (`nviaje`, `codsocio`, `empresa`, `idorigen`, `iddestino`, `fpartida`, `fllegada`, `placa`, `dniconductor`, `prepasaje`, `totpasajes`, `totalasientos`, `asientosven`) VALUES
(1, 1006, 2, 1, 2, '2019-12-20 00:00:00', '2019-12-20 00:00:00', 'BBB-222', 76782006, 25, 0, 20, 0),
(2, 1003, 1, 1, 2, '2019-12-20 02:10:00', '2019-12-20 01:15:00', 'CCC-333', 76782003, 25, 75, 21, 20),
(3, 1003, 1, 1, 2, '2019-12-29 03:15:00', '2019-12-29 06:00:00', 'AAA-111', 76782002, 25, 25, 20, 20),
(4, 1001, 1, 1, 1, NULL, NULL, 'AAA-111', 76782001, 25, 25, 20, 20),
(5, 1002, 2, 1, 2, '2019-12-29 15:00:00', '2019-12-30 01:00:00', 'BBB-222', 76782002, 25, 0, 0, 0),
(6, 1002, 2, 1, 1, NULL, NULL, 'BBB-222', 76782002, 25, 0, 20, 20),
(7, 1001, 1, 1, 1, NULL, NULL, 'AAA-111', 76782001, 25, 500, 20, 20),
(8, 1001, 1, 1, 2, '2020-01-02 02:05:00', '2019-01-03 02:05:00', 'AAA-111', 76782001, 25, 0, 0, 0),
(9, 1003, 1, 2, 1, '2020-01-03 02:10:00', '2020-01-04 03:10:00', 'BBB-222', 76782002, 30, 0, 0, 0),
(10, 1001, 1, 1, 2, '2020-01-07 01:00:00', '2020-01-07 00:00:00', 'AAA-111', 76782001, 25, 0, 0, 0),
(11, 1005, 1, 1, 3, '2020-01-10 00:00:00', '2020-01-11 00:00:00', 'EEE-555', 76782005, 25, 0, 0, 0),
(12, 1001, 1, 1, 3, '2020-01-10 00:00:00', '2020-01-11 00:00:00', 'AAA-111', 76782001, 25, 0, 0, 0),
(13, 1001, 1, 1, 3, '2020-01-18 01:00:00', '2020-01-23 02:00:00', 'BBB-222', 76782002, 30, 0, 0, 0),
(14, 1001, 1, 1, 1, '2020-01-10 00:00:00', '2020-01-08 02:00:00', 'AAA-111', 76782002, 25, 0, 0, 0),
(15, 1006, 1, 1, 1, '2020-01-10 00:00:00', '2020-01-26 00:00:00', 'AAA-111', 76782001, 25, 0, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_comprobante`
--
ALTER TABLE `tb_comprobante`
  ADD PRIMARY KEY (`id_comprobante`);

--
-- Indices de la tabla `tb_comprobantes_emitidos`
--
ALTER TABLE `tb_comprobantes_emitidos`
  ADD PRIMARY KEY (`nserie1`,`nserie2`),
  ADD KEY `idempresa` (`idempresa`),
  ADD KEY `idorigen` (`idorigen`),
  ADD KEY `iddestino` (`iddestino`);

--
-- Indices de la tabla `tb_conductor`
--
ALTER TABLE `tb_conductor`
  ADD PRIMARY KEY (`dniconductor`);

--
-- Indices de la tabla `tb_configuracion_inicial`
--
ALTER TABLE `tb_configuracion_inicial`
  ADD PRIMARY KEY (`estado`);

--
-- Indices de la tabla `tb_detalle_comprobante`
--
ALTER TABLE `tb_detalle_comprobante`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `FK_venta_detalle_comprobante` (`id_comprobante`);

--
-- Indices de la tabla `tb_detalle_viaje`
--
ALTER TABLE `tb_detalle_viaje`
  ADD PRIMARY KEY (`nviaje`,`nboleto`),
  ADD KEY `dnipasajero` (`dnipasajero`);

--
-- Indices de la tabla `tb_detalle_viaje_otros`
--
ALTER TABLE `tb_detalle_viaje_otros`
  ADD PRIMARY KEY (`nviaje`);

--
-- Indices de la tabla `tb_empresa`
--
ALTER TABLE `tb_empresa`
  ADD PRIMARY KEY (`idempresa`);

--
-- Indices de la tabla `tb_gastos`
--
ALTER TABLE `tb_gastos`
  ADD PRIMARY KEY (`idgasto`),
  ADD KEY `idempresa` (`idempresa`);

--
-- Indices de la tabla `tb_modelo_vehiculo`
--
ALTER TABLE `tb_modelo_vehiculo`
  ADD PRIMARY KEY (`idmodelo`);

--
-- Indices de la tabla `tb_pasajero`
--
ALTER TABLE `tb_pasajero`
  ADD PRIMARY KEY (`dnipasajero`);

--
-- Indices de la tabla `tb_pasajeros_temporal`
--
ALTER TABLE `tb_pasajeros_temporal`
  ADD PRIMARY KEY (`asiento`),
  ADD KEY `dnipasajero` (`dnipasajero`);

--
-- Indices de la tabla `tb_sedes`
--
ALTER TABLE `tb_sedes`
  ADD PRIMARY KEY (`idsede`);

--
-- Indices de la tabla `tb_servicios`
--
ALTER TABLE `tb_servicios`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `tb_socio`
--
ALTER TABLE `tb_socio`
  ADD PRIMARY KEY (`codsocio`,`dnisocio`),
  ADD KEY `dniconductor` (`dniconductor`),
  ADD KEY `placa` (`placa`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`usuario`);

--
-- Indices de la tabla `tb_vehiculo`
--
ALTER TABLE `tb_vehiculo`
  ADD PRIMARY KEY (`placa`),
  ADD KEY `idmodelo` (`idmodelo`);

--
-- Indices de la tabla `tb_venta_temporal`
--
ALTER TABLE `tb_venta_temporal`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tb_viaje`
--
ALTER TABLE `tb_viaje`
  ADD PRIMARY KEY (`nviaje`),
  ADD KEY `codsocio` (`codsocio`),
  ADD KEY `idorigen` (`idorigen`),
  ADD KEY `iddestino` (`iddestino`),
  ADD KEY `placa` (`placa`),
  ADD KEY `dniconductor` (`dniconductor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_comprobante`
--
ALTER TABLE `tb_comprobante`
  MODIFY `id_comprobante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `tb_detalle_comprobante`
--
ALTER TABLE `tb_detalle_comprobante`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `tb_empresa`
--
ALTER TABLE `tb_empresa`
  MODIFY `idempresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_gastos`
--
ALTER TABLE `tb_gastos`
  MODIFY `idgasto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_modelo_vehiculo`
--
ALTER TABLE `tb_modelo_vehiculo`
  MODIFY `idmodelo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tb_sedes`
--
ALTER TABLE `tb_sedes`
  MODIFY `idsede` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tb_servicios`
--
ALTER TABLE `tb_servicios`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_viaje`
--
ALTER TABLE `tb_viaje`
  MODIFY `nviaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_comprobantes_emitidos`
--
ALTER TABLE `tb_comprobantes_emitidos`
  ADD CONSTRAINT `tb_comprobantes_emitidos_ibfk_1` FOREIGN KEY (`idempresa`) REFERENCES `tb_empresa` (`idempresa`),
  ADD CONSTRAINT `tb_comprobantes_emitidos_ibfk_2` FOREIGN KEY (`idorigen`) REFERENCES `tb_sedes` (`idsede`),
  ADD CONSTRAINT `tb_comprobantes_emitidos_ibfk_3` FOREIGN KEY (`iddestino`) REFERENCES `tb_sedes` (`idsede`);

--
-- Filtros para la tabla `tb_detalle_comprobante`
--
ALTER TABLE `tb_detalle_comprobante`
  ADD CONSTRAINT `FK_venta_detalle_comprobante` FOREIGN KEY (`id_comprobante`) REFERENCES `tb_comprobante` (`id_comprobante`);

--
-- Filtros para la tabla `tb_detalle_viaje`
--
ALTER TABLE `tb_detalle_viaje`
  ADD CONSTRAINT `tb_detalle_viaje_ibfk_1` FOREIGN KEY (`nviaje`) REFERENCES `tb_viaje` (`nviaje`),
  ADD CONSTRAINT `tb_detalle_viaje_ibfk_2` FOREIGN KEY (`dnipasajero`) REFERENCES `tb_pasajero` (`dnipasajero`);

--
-- Filtros para la tabla `tb_detalle_viaje_otros`
--
ALTER TABLE `tb_detalle_viaje_otros`
  ADD CONSTRAINT `tb_detalle_viaje_otros_ibfk_1` FOREIGN KEY (`nviaje`) REFERENCES `tb_viaje` (`nviaje`);

--
-- Filtros para la tabla `tb_gastos`
--
ALTER TABLE `tb_gastos`
  ADD CONSTRAINT `tb_gastos_ibfk_1` FOREIGN KEY (`idempresa`) REFERENCES `tb_empresa` (`idempresa`);

--
-- Filtros para la tabla `tb_pasajeros_temporal`
--
ALTER TABLE `tb_pasajeros_temporal`
  ADD CONSTRAINT `tb_pasajeros_temporal_ibfk_1` FOREIGN KEY (`dnipasajero`) REFERENCES `tb_pasajero` (`dnipasajero`);

--
-- Filtros para la tabla `tb_socio`
--
ALTER TABLE `tb_socio`
  ADD CONSTRAINT `tb_socio_ibfk_1` FOREIGN KEY (`dniconductor`) REFERENCES `tb_conductor` (`dniconductor`),
  ADD CONSTRAINT `tb_socio_ibfk_2` FOREIGN KEY (`placa`) REFERENCES `tb_vehiculo` (`placa`);

--
-- Filtros para la tabla `tb_vehiculo`
--
ALTER TABLE `tb_vehiculo`
  ADD CONSTRAINT `tb_vehiculo_ibfk_1` FOREIGN KEY (`idmodelo`) REFERENCES `tb_modelo_vehiculo` (`idmodelo`);

--
-- Filtros para la tabla `tb_viaje`
--
ALTER TABLE `tb_viaje`
  ADD CONSTRAINT `tb_viaje_ibfk_1` FOREIGN KEY (`codsocio`) REFERENCES `tb_socio` (`codsocio`),
  ADD CONSTRAINT `tb_viaje_ibfk_2` FOREIGN KEY (`idorigen`) REFERENCES `tb_sedes` (`idsede`),
  ADD CONSTRAINT `tb_viaje_ibfk_3` FOREIGN KEY (`iddestino`) REFERENCES `tb_sedes` (`idsede`),
  ADD CONSTRAINT `tb_viaje_ibfk_4` FOREIGN KEY (`placa`) REFERENCES `tb_vehiculo` (`placa`),
  ADD CONSTRAINT `tb_viaje_ibfk_5` FOREIGN KEY (`dniconductor`) REFERENCES `tb_conductor` (`dniconductor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
