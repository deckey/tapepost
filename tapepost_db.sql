-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 25, 2016 at 11:47 PM
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
  `commentTime` datetime DEFAULT NULL,
  `commentPostId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberBio`, `memberCountry`, `memberEmail`, `memberFullName`, `memberPassword`, `memberRole`, `memberUsername`) VALUES
(1, 'Super user - main admin', 'Serbia', 'admin@admin.com', 'Administrator', 'admin', 'Administrator', 'admin'),
(25, 'Some information about the member', 'Serbia', 'de@de.com', 'de', 'de', 'User', 'de');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

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
(33, 'asdasdas', '2016-01-25 20:49:14', 'asdasd', 0, 1);

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
  MODIFY `commentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `postId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=34;
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
