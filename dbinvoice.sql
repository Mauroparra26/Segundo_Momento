-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-04-2022 a las 21:58:09
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbinvoice`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

CREATE TABLE `customer` (
  `idcust` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `email` varchar(250) COLLATE utf8mb4_spanish_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `passwd` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`idcust`, `name`, `email`, `phone`, `passwd`) VALUES
('10', 'El Paisa - Restaurante', 'elpaisarest@gmail.com', '6042547893', ''),
('11', 'Pepe Perez', 'pperez@hotmail.com', '3102254178', ''),
('20', 'Pepe S.A', 'pepesa@gmail.com', '2541789', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `idinvoice` int(11) NOT NULL,
  `idcust` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `date` date NOT NULL,
  `obs` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `coste` int(11) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `invoice`
--

INSERT INTO `invoice` (`idinvoice`, `idcust`, `date`, `obs`, `coste`, `balance`) VALUES
(1, '10', '2022-02-01', 'acarreo', 2700000, 2700000),
(2, '10', '2022-02-12', 'pendiente', 3150000, 3150000),
(3, '11', '2022-02-09', 'Pueblo Rico', 2300000, 2300000),
(4, '11', '2022-02-11', 'Don Matias', 8000000, 8000000),
(5, '10', '2022-02-09', 'SDFSDFS', 288000, 288000),
(6, '20', '2022-02-02', 'dfdfdf', 0, 0),
(7, '20', '2022-02-02', 'dfdfdf', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `payment`
--

CREATE TABLE `payment` (
  `idpayment` int(11) NOT NULL,
  `idinvoice` int(11) NOT NULL,
  `date` date NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `payment`
--

INSERT INTO `payment` (`idpayment`, `idinvoice`, `date`, `value`) VALUES
(3, 3, '2022-02-09', 300000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`idcust`);

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`idinvoice`);

--
-- Indices de la tabla `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`idpayment`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `idinvoice` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `payment`
--
ALTER TABLE `payment`
  MODIFY `idpayment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
