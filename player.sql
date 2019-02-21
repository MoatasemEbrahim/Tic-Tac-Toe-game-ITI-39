-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 21, 2019 at 12:24 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `player`
--

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `flag` int(4) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `username`, `password`, `points`, `flag`, `nickname`) VALUES
(1, 'nour', 'nour', 125, 0, 'nour'),
(2, 'nourhan', 'nourhan', 110, 0, 'nourhan'),
(3, 'motasm', 'motasm', 0, 0, 'motasm'),
(4, 'mahmoud', 'mahmoud', 0, 0, 'mahmoud'),
(5, 'aya', 'ayaaya', 0, 0, 'aya'),
(6, 'ahmed', 'ahmedahmed', 0, 0, 'aya'),
(7, 'hend', 'hend', 0, 0, 'hend'),
(8, 'fatema', 'fatema', 0, 0, 'fatema'),
(9, 'mayar', 'mayar', 0, 0, 'mayar'),
(10, 'eman', 'eman', 10, 1, 'eman');

-- --------------------------------------------------------

--
-- Table structure for table `saved_games`
--

DROP TABLE IF EXISTS `saved_games`;
CREATE TABLE IF NOT EXISTS `saved_games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player1` varchar(255) NOT NULL,
  `player2` varchar(255) NOT NULL,
  `pos1` int(11) NOT NULL,
  `pos2` int(11) NOT NULL,
  `pos3` int(11) NOT NULL,
  `pos4` int(11) NOT NULL,
  `pos5` int(11) NOT NULL,
  `pos6` int(11) NOT NULL,
  `pos7` int(11) NOT NULL,
  `pos8` int(11) NOT NULL,
  `pos9` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `saved_games`
--

INSERT INTO `saved_games` (`id`, `player1`, `player2`, `pos1`, `pos2`, `pos3`, `pos4`, `pos5`, `pos6`, `pos7`, `pos8`, `pos9`) VALUES
(3, 'nour', 'nourhan', 0, 0, 2, 0, 1, 0, 0, 0, 0),
(4, 'nour', 'nourhan', 0, 0, 2, 0, 1, 0, 0, 0, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
