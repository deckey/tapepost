-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 29, 2016 at 12:23 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=807 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentId`, `commentContent`, `commentTime`, `commentMemberId`, `commentPostId`) VALUES
(797, 'I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When,. ', '2016-01-29 00:13:49', 67, 79),
(798, ' Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules.\r\n\r\nOmnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At sol', '2016-01-29 00:16:28', 72, 81),
(799, 'I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents.', '2016-01-29 00:16:39', 72, 82),
(800, 'His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. "What''s happened to me? " he tho ', '2016-01-29 00:16:55', 72, 80),
(801, 'The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps.\r\n\r\nBawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz.\r\n\r\nBrick quiz whangs jumpy', '2016-01-29 00:17:10', 72, 82),
(803, 'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi temp', '2016-01-29 00:18:20', 70, 81),
(805, 'It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic lif ', '2016-01-29 11:21:08', 79, 87),
(806, 'One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin.', '2016-01-29 11:22:28', 80, 81);

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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberFullName`, `memberUsername`, `memberPassword`, `memberEmail`, `memberCountry`, `memberRole`, `memberBio`, `memberJoined`) VALUES
(1, 'Administrator', 'admin', 'admin', 'admin@admin.com', 'Serbia', 'Administrator', 'Super user - main admin', '2016-01-26 20:55:13'),
(67, 'William	Phelps', 'william', 'wp', 'ffsef@egsgse', NULL, 'User', NULL, '2016-01-29 00:10:23'),
(69, 'Courtney Coleman', 'courtney ', 'cc', 'cc@c.com', 'USA', 'Administrator', 'dvsvsdvd', '2016-01-29 00:11:04'),
(70, 'Danny	Hamilton', 'danny', 'dh', NULL, NULL, 'User', NULL, '2016-01-29 00:11:21'),
(71, 'Carrie Maldonado', 'carrie', 'cm', NULL, NULL, 'User', NULL, '2016-01-29 00:11:44'),
(72, 'Arnold	Chavez', 'arnold', 'ac', NULL, NULL, 'User', NULL, '2016-01-29 00:15:14'),
(73, 'Verna Bryan', 'verna', 'vb', NULL, NULL, 'User', NULL, '2016-01-29 00:18:52'),
(74, 'Albert	Carter', 'albert', 'ac', NULL, NULL, 'User', NULL, '2016-01-29 00:20:59'),
(79, 'user', 'user', 'user', NULL, NULL, 'User', NULL, '2016-01-29 11:20:31'),
(80, 'member', 'member', 'member', NULL, NULL, 'User', NULL, '2016-01-29 11:21:37');

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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postId`, `postContent`, `postTime`, `postTitle`, `postVotes`, `postMemberId`) VALUES
(79, ' Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim.\r\n\r\nQuick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my bi', '2016-01-29 00:12:20', 'Bawds jog, flick quartz, vex nymphs. ', 0, 71),
(80, 'I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When,. ', '2016-01-29 00:13:18', 'I am so happy, my dear friend, so absorbed in the exquisite', 0, 67),
(81, 'The bedding was hardly able to cover it and seemed ready to slide off any moment.', '2016-01-29 00:16:01', 'He lay on his armour-like back', 0, 72),
(82, ' He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections.\r\n\r\nThe bedding was hardly able to cover it and seemed ready to slide off any moment.', '2016-01-29 00:16:17', 'he found himself transformed in his bed ', 0, 72),
(83, 'but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.\r\n\r\nNor again is there anyone who loves or.', '2016-01-29 00:17:49', 'No one rejects, dislikes, or avoids pleasure itself,', 0, 70),
(84, 'It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic lif ', '2016-01-29 00:19:20', 'A small river named Duden flows by their place and supplies it ', 0, 73),
(85, ' Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim.\r\n\r\nQuick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my bi', '2016-01-29 00:19:57', 'nk MTV quiz graced by fox whelps.', 0, 73),
(86, ' Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules.\r\n\r\nOmnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At solmen va esser necessi far uniform grammatica, pronunci', '2016-01-29 10:23:59', 'Lor separat existentie es un myth.', 0, 73),
(87, 'I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When,. ', '2016-01-29 11:13:08', 'I am so happy, my dear friend, so absorbed in the exquisite ', 0, 73),
(88, ' Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.\r\n\r\nA small river named Duden flows by their place and supplies it with the necessary regelialia.', '2016-01-29 11:20:47', 'It is a paradisematic country, in which roasted parts of ', 0, 79),
(89, 'His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture tha', '2016-01-29 11:22:03', 'The bedding was hardly able to cover it and seemed ready to slide off any moment.', 0, 80);

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
  MODIFY `commentId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=807;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=81;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `postId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=90;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_hh2396bjx9bdw7kx31908fifn` FOREIGN KEY (`commentPostId`) REFERENCES `post` (`postId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK_9yeym75y060mjajfvl5g8000t` FOREIGN KEY (`postMemberId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
