-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 26, 2016 at 01:32 AM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tapepost.db`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `commentId` int(11) NOT NULL,
  `commentContent` varchar(255) DEFAULT NULL,
  `commentTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `commentMemberId` int(11) DEFAULT NULL,
  `commentPostId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentId`, `commentContent`, `commentTime`, `commentMemberId`, `commentPostId`) VALUES
(22, 'This is an auto-generated comment!', '2016-01-26 00:27:48', 1, 35),
(23, 'This is an auto-generated comment!', '2016-01-26 00:28:09', 26, 35),
(24, 'This is an auto-generated comment!', '2016-01-26 00:28:44', 25, 35),
(25, 'This is an auto-generated comment!', '2016-01-26 00:29:01', 25, 34),
(26, 'This is an auto-generated comment!', '2016-01-26 00:29:12', 1, 34),
(27, 'This is an auto-generated comment!', '2016-01-26 00:29:31', 1, 31),
(29, 'This is an auto-generated comment!', '2016-01-26 00:30:59', 1, 34),
(30, 'This is an auto-generated comment!', '2016-01-26 00:31:02', 1, 31),
(31, 'This is an auto-generated comment!', '2016-01-26 00:31:05', 1, 31),
(32, 'This is an auto-generated comment!', '2016-01-26 00:31:07', 1, 31),
(33, 'This is an auto-generated comment!', '2016-01-26 00:31:11', 1, 31),
(34, 'This is an auto-generated comment!', '2016-01-26 00:31:14', 1, 35),
(35, 'This is an auto-generated comment!', '2016-01-26 00:31:17', 1, 35);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `memberId` int(11) NOT NULL,
  `memberBio` varchar(255) DEFAULT NULL,
  `memberCountry` varchar(255) DEFAULT NULL,
  `memberEmail` varchar(255) DEFAULT NULL,
  `memberFullName` varchar(255) DEFAULT NULL,
  `memberPassword` varchar(255) DEFAULT NULL,
  `memberRole` varchar(255) DEFAULT NULL,
  `memberUsername` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberBio`, `memberCountry`, `memberEmail`, `memberFullName`, `memberPassword`, `memberRole`, `memberUsername`) VALUES
(1, 'Super user - main admin', 'Serbia', 'admin@admin.com', 'Administrator', 'admin', 'Administrator', 'admin'),
(25, 'Some information about the member', 'Serbia', 'de@de.com', 'de', 'de', 'User', 'de'),
(26, NULL, 'Serbia', 'daddw', 'Member 2', 'mem2', 'User', 'mem2');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `postId` int(11) NOT NULL,
  `postContent` varchar(255) DEFAULT NULL,
  `postTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `postTitle` varchar(255) DEFAULT NULL,
  `postVotes` int(11) NOT NULL,
  `postMemberId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postId`, `postContent`, `postTime`, `postTitle`, `postVotes`, `postMemberId`) VALUES
(23, 'dawdaw', '2016-01-25 20:42:33', 'dwadaw', 0, 1),
(24, 'dawdawd', '2016-01-25 20:43:26', 'awdawd', 0, 1),
(25, 'eafaef', '2016-01-25 20:43:45', 'daeafa', 0, 25),
(26, 'afeaefaef', '2016-01-25 20:43:48', 'faefaef', 0, 25),
(27, 'zzz', '2016-01-25 20:44:00', 'zzz', 0, 25),
(28, 'aaaa', '2016-01-25 20:44:10', 'aaa', 0, 25),
(31, 'vvv', '2016-01-25 20:46:23', 'vvv', 0, 1),
(32, 'ggg', '2016-01-25 20:46:29', 'ggg', 0, 1),
(33, 'asdasdas', '2016-01-25 20:49:14', 'asdasd', 0, 1),
(34, 'The European languages are members of the same family. Their separate existence is a myth.\r\n\r\nFor science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar,.', '2016-01-25 23:11:08', 'The European languages are members of the ', 0, 1),
(35, 'Li Europan lingues es membres del sam familie. Lor separat existentie es un myth.\r\n\r\nPor scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pro', '2016-01-25 23:24:56', 'Europan lingues es membres', 0, 1),
(36, 'But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the gre', '2016-01-25 23:26:34', 'must explain to you how all this mistak', 0, 26);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`commentId`),
  ADD KEY `FK_hh2396bjx9bdw7kx31908fifn` (`commentPostId`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`memberId`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`postId`),
  ADD KEY `FK_9yeym75y060mjajfvl5g8000t` (`postMemberId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `commentId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `postId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_hh2396bjx9bdw7kx31908fifn` FOREIGN KEY (`commentPostId`) REFERENCES `post` (`postId`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK_9yeym75y060mjajfvl5g8000t` FOREIGN KEY (`postMemberId`) REFERENCES `member` (`memberId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
