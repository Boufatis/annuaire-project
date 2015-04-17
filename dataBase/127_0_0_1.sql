-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 17 Avril 2015 à 17:01
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `annuaire`
--
CREATE DATABASE IF NOT EXISTS `annuaire` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `annuaire`;

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `prenom` varchar(60) NOT NULL,
  `mail` varchar(60) NOT NULL,
  `telephone` varchar(60) DEFAULT NULL,
  `promo` varchar(60) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `photo` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `contact`
--

INSERT INTO `contact` (`id`, `nom`, `prenom`, `mail`, `telephone`, `promo`, `date_naissance`, `photo`) VALUES
(2, 'alami', 'Faycal', 'f.alami@insta.fr', NULL, NULL, '2014-07-15', NULL),
(3, 'r.boufatis', 'romaric', 'r.boufatis@insta.fr', NULL, '138', '2015-04-21', NULL),
(4, 'Khaled', 'Bilel', 'b.khaled@insta.fr', NULL, '138', '1991-12-13', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE IF NOT EXISTS `profil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `profil`
--

INSERT INTO `profil` (`id`, `libelle`) VALUES
(4, 'etudiant'),
(5, 'professeur'),
(6, 'administrateur');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `profilid` int(11) DEFAULT NULL,
  `contactid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `profilid` (`profilid`,`contactid`),
  KEY `contactid` (`contactid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `profilid`, `contactid`) VALUES
(4, 'b.khaled', 'azerty', 4, 4),
(5, 'f.alami', '1234', 5, 2),
(6, 'r.boufatis', 'aqzs', 4, 3);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`profilid`) REFERENCES `profil` (`id`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`contactid`) REFERENCES `contact` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
