-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le :  mar. 12 juin 2018 à 12:11
-- Version du serveur :  5.6.38
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `javaee_esgi_tasks`
--
CREATE DATABASE IF NOT EXISTS `javaee_esgi_tasks` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `javaee_esgi_tasks`;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
  (7),
  (7);

-- --------------------------------------------------------

--
-- Structure de la table `Priority`
--

DROP TABLE IF EXISTS `Priority`;
CREATE TABLE `Priority` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Priority`
--

INSERT INTO `Priority` (`ID`, `name`) VALUES
  (1, 'Basse'),
  (2, 'Moyenne'),
  (3, 'Élevée'),
  (4, 'Urgente');

-- --------------------------------------------------------

--
-- Structure de la table `Task`
--

DROP TABLE IF EXISTS `Task`;
CREATE TABLE `Task` (
  `ID` bigint(20) NOT NULL,
  `deadline` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Task`
--

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Priority`
--
ALTER TABLE `Priority`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `Task`
--
ALTER TABLE `Task`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK5qcjrc2iayov9ndrtsm5v7rwl` (`priority_ID`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Task`
--
ALTER TABLE `Task`
  ADD CONSTRAINT `FK5qcjrc2iayov9ndrtsm5v7rwl` FOREIGN KEY (`priority_ID`) REFERENCES `Priority` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;