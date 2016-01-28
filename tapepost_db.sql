-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 29, 2016 at 12:05 AM
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
) ENGINE=InnoDB AUTO_INCREMENT=794 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentId`, `commentContent`, `commentTime`, `commentMemberId`, `commentPostId`) VALUES
(790, 'nean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridic', '2016-01-28 08:11:02', 65, 75),
(791, 'jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks ', '2016-01-28 08:11:15', 65, 74),
(792, 'Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymph ', '2016-01-28 22:56:33', 63, 77),
(793, 'Li Europan lingues es membres del sam familie. Lor separat existentie es un myth.\r\nPor scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pro ', '2016-01-28 23:04:50', 62, 74);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `memberId` int(11) NOT NULL,
  `memberFullName` varchar(255) DEFAULT NULL,
  `memberUsername` varchar(255) DEFAULT NULL,
  `memberPassword` varchar(255) DEFAULT NULL,
  `memberEmail` varchar(255) DEFAULT NULL,
  `memberCountry` varchar(255) DEFAULT NULL,
  `memberRole` varchar(255) DEFAULT NULL,
  `memberBio` varchar(255) DEFAULT NULL,
  `memberJoined` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberFullName`, `memberUsername`, `memberPassword`, `memberEmail`, `memberCountry`, `memberRole`, `memberBio`, `memberJoined`) VALUES
(1, 'Administrator', 'admin', 'admin', 'admin@admin.com', 'Serbia', 'Administrator', 'Super user - main admin', '2016-01-26 20:55:13'),
(26, 'Member 2', 'mem2', 'mem2', 'daddw', 'Serbia', 'User', NULL, '2016-01-26 20:55:13'),
(62, 'Member 4', 'mem4', 'mem4', 'mem4@mem4.com', NULL, 'User', 'Yet another member', '2016-01-27 21:13:41'),
(63, 'Member 3', 'mem3', 'mem3', 'mem3@mem3.com', NULL, 'User', 'Member 3 profile', '2016-01-27 21:14:44'),
(65, 'mem', 'mem', 'mem', NULL, NULL, 'User', NULL, '2016-01-28 08:10:48'),
(66, 'mem1', 'mem1', 'mem1', 'mem1@mem1.com', NULL, 'User', 'Member 1 profile', '2016-01-28 22:49:46');

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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postId`, `postContent`, `postTime`, `postTitle`, `postVotes`, `postMemberId`) VALUES
(74, 'No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or. ', '2016-01-27 21:11:02', 'But I must explain to you ', 0, 26),
(75, 'The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To achieve this, it would be necessary to have unif', '2016-01-27 22:06:43', 'The European languages are members of the same family. ', 0, 62),
(77, 'New post by new member', '2016-01-28 08:11:46', 'New post', 0, 65);

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
  MODIFY `commentId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=794;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=67;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `postId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=78;
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
