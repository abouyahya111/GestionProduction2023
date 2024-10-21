-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Jeu 23 Juin 2016 à 19:44
-- Version du serveur: 5.6.11-log
-- Version de PHP: 5.4.14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `production`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie_mp`
--

CREATE TABLE IF NOT EXISTS `categorie_mp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `id_sub_cat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_sub_cat` (`id_sub_cat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_four` int(11) NOT NULL,
  `date_laivraison` date NOT NULL,
  `montant` float(8,2) NOT NULL,
  `statut` varchar(30) NOT NULL,
  `creer_par` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `annuler_par` int(11) NOT NULL,
  `modifier_par` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_four` (`id_four`,`creer_par`,`annuler_par`,`modifier_par`),
  KEY `modifier_par` (`modifier_par`),
  KEY `annuler_par` (`annuler_par`),
  KEY `creer_par` (`creer_par`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `detail_commande`
--

CREATE TABLE IF NOT EXISTS `detail_commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_commande` int(11) NOT NULL,
  `id_mat_pre` int(11) NOT NULL,
  `quantite` float(8,2) NOT NULL,
  `prix_unitaire` float(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_commande` (`id_commande`,`id_mat_pre`),
  KEY `id_mat_pre` (`id_mat_pre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `detail_facture`
--

CREATE TABLE IF NOT EXISTS `detail_facture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_facture` int(11) NOT NULL,
  `id_mat_pre` int(11) NOT NULL,
  `quantite_recu` float(8,2) NOT NULL,
  `prix_unitaire` float(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_facture` (`id_facture`),
  KEY `id_mat_pre` (`id_mat_pre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `detail_production`
--

CREATE TABLE IF NOT EXISTS `detail_production` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_production` int(11) NOT NULL,
  `id_employe` int(11) NOT NULL,
  `delai_employe` float(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_production` (`id_production`),
  KEY `id_employe` (`id_employe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `detail_produit`
--

CREATE TABLE IF NOT EXISTS `detail_produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_produit` int(11) NOT NULL,
  `id_mat_pre` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_produit` (`id_produit`),
  KEY `id_mat_pre` (`id_mat_pre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

CREATE TABLE IF NOT EXISTS `devise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `taux_esitime` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE IF NOT EXISTS `employe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricule` varchar(30) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `responsabilite` varchar(30) NOT NULL,
  `cout_horaire` float(8,2) NOT NULL,
  `remise` float(8,2) NOT NULL,
  ID_EQUIPE int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE IF NOT EXISTS `facture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_commande` int(11) NOT NULL,
  `num_contenaire` varchar(30) NOT NULL,
  `num_bouquine` varchar(30) NOT NULL,
  `id_fournisseur` int(11) NOT NULL,
  `nim_fact_four` varchar(30) NOT NULL,
  `date_laivraison` date NOT NULL,
  `etat` varchar(30) NOT NULL,
  `date_en_port` date NOT NULL,
  `delai_paiement` varchar(30) NOT NULL,
  `montant` float(8,2) NOT NULL,
  `situation` varchar(30) NOT NULL,
  `satut` varchar(30) NOT NULL,
  `creer_par` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `annuler_par` int(11) NOT NULL,
  `modifier_par` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_commande` (`id_commande`),
  KEY `id_fournisseur` (`id_fournisseur`),
  KEY `creer_par` (`creer_par`),
  KEY `annuler_par` (`annuler_par`),
  KEY `modifier_par` (`modifier_par`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `email` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `machine`
--

CREATE TABLE IF NOT EXISTS `machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `matricule` varchar(30) NOT NULL,
  `stock_solofane` float(8,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `matiere_premier`
--

CREATE TABLE IF NOT EXISTS `matiere_premier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `id_cat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cat` (`id_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `production`
--

CREATE TABLE IF NOT EXISTS `production` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  NUM_OF varchar(50);
	DESCRIPTION_OF varchar(50);
  `id_produit` int(11) NOT NULL,
  `id_machine` int(11) NOT NULL,
  `date-deb_fab_pre` date NOT NULL,
  `date_deb_fab_ree` date NOT NULL,
  `date_fin_fab_pre` date NOT NULL,
  `date_fin_fab_ree` date NOT NULL,
  `quantite_estime` float(8,2) NOT NULL,
  `quantite_reel` float(8,2) NOT NULL,
  `montant_estime` float(8,2) NOT NULL,
  `montant_reel` float(8,2) NOT NULL,
  `statut` varchar(30) NOT NULL,
  `creer_par` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `annuler_par` int(11) NOT NULL,
  `modifier_par` int(11) NOT NULL,
  
	ID_GROUPE INT(11);
	QTE_REBUTE FLOAT(8,2);
	CODE_DEPOT INT(11);
  PRIMARY KEY (`id`),
  KEY `id_produit` (`id_produit`),
  KEY `id_machine` (`id_machine`),
  KEY `creer_par` (`creer_par`),
  KEY `annuler_par` (`annuler_par`),
  KEY `modifier_par` (`modifier_par`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reglement`
--

CREATE TABLE IF NOT EXISTS `reglement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_fournisseur` int(11) NOT NULL,
  `id_rip` int(11) NOT NULL,
  `montant_total` float(8,2) NOT NULL,
  `montant_reglement` float(8,2) NOT NULL,
  `id_devise` int(11) NOT NULL,
  `taux_reel_devise` float(8,2) NOT NULL,
  `prix_moyen` float(8,2) NOT NULL,
  `statut` varchar(30) NOT NULL,
  `creer_par` int(11) NOT NULL,
  `date` date NOT NULL,
  `annuler_par` int(11) NOT NULL,
  `modifier_par` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_fournisseur` (`id_fournisseur`),
  KEY `id_rip` (`id_rip`),
  KEY `id_devise` (`id_devise`),
  KEY `creer_par` (`creer_par`),
  KEY `annuler_par` (`annuler_par`),
  KEY `modifier_par` (`modifier_par`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rip_fournisseur`
--

CREATE TABLE IF NOT EXISTS `rip_fournisseur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rip` varchar(30) NOT NULL,
  `id_four` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_four` (`id_four`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_mat_pre` int(11) NOT NULL,
  `stock` float(8,2) NOT NULL,
  `stock_min` float(8,2) NOT NULL,
  `quantite_commande` float(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_mat_pre` (`id_mat_pre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `sub_categorie_mp`
--

CREATE TABLE IF NOT EXISTS `sub_categorie_mp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `login`, `password`) VALUES
(1, '', 'admin', 'admin');




CREATE TABLE SOUS_MACHINE
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `matricule` varchar(30) NOT NULL,
  `stock_solofane` float(8,2) NOT NULL,
  ID_MACHINE int (11),
  CONSTRAINT FK_MACHINE FOREIGN KEY (ID_MACHINE)  REFERENCES MACHINE (ID),
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE Equipe
(
    id      int(11)        NOT NULL,
    code_Equipe  VARCHAR(32)    NOT NULL,
    nom_Equipe      VARCHAR(100)    NOT NULL,
 CONSTRAINT C1 PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS detail_estimation (
  id int(11) NOT NULL AUTO_INCREMENT,
  id_article int(11) NOT NULL,
  id_mat_pre int(11) NOT NULL,
  quantite float(8,2) NOT NULL,
  

  PRIMARY KEY (id)
 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `cout_MP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_production` int(11) NOT NULL,
  `id_mat_pre` int(11) NOT NULL,
  `quantite` float(8,2) NOT NULL,
  `prix` float(8,2) NOT NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `cout_MP_ibfk_1` FOREIGN KEY (`id_production`) REFERENCES `production` (`id`),
  CONSTRAINT `cout_MP_ibfk_2` FOREIGN KEY (`id_mat_pre`) REFERENCES `matiere_premier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `DEPOT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(30) NOT NULL,
  `LIBELLE` varchar(30) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `MAGASIN` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(30) NOT NULL,
  `LIBELLE` varchar(30) NOT NULL,
 
  `ID_DEPOT` int(11) NOT NULL,
  
  PRIMARY KEY (`id`),
  KEY `FK_MACHINE` (`ID_DEPOT`),
  CONSTRAINT `FK_MAGASIN` FOREIGN KEY (`ID_DEPOT`) REFERENCES `DEPOT` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

ALTER TABLE `detail_estimation`
  ADD CONSTRAINT `detail_estimation_ibfk_2` FOREIGN KEY (`id_mat_pre`) REFERENCES `matiere_premier` (`id`),
  ADD CONSTRAINT `detail_estimation_ibfk_1` FOREIGN KEY (`id_article`) REFERENCES `articles` (`id`) ;
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `categorie_mp`
--
ALTER TABLE `categorie_mp`
  ADD CONSTRAINT `categorie_mp_ibfk_4` FOREIGN KEY (`id_sub_cat`) REFERENCES `sub_categorie_mp` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_4` FOREIGN KEY (`modifier_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`id_four`) REFERENCES `fournisseur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`creer_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `commande_ibfk_3` FOREIGN KEY (`annuler_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `detail_commande`
--

  
  
ALTER TABLE `detail_commande`
  ADD CONSTRAINT `detail_commande_ibfk_2` FOREIGN KEY (`id_mat_pre`) REFERENCES `matiere_premier` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `detail_commande_ibfk_1` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `detail_facture`
--
ALTER TABLE `detail_facture`
  ADD CONSTRAINT `detail_facture_ibfk_2` FOREIGN KEY (`id_mat_pre`) REFERENCES `matiere_premier` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `detail_facture_ibfk_1` FOREIGN KEY (`id_facture`) REFERENCES `facture` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `detail_production`
--
ALTER TABLE `detail_production`
  ADD CONSTRAINT `detail_production_ibfk_2` FOREIGN KEY (`id_employe`) REFERENCES `employe` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `detail_production_ibfk_1` FOREIGN KEY (`id_production`) REFERENCES `production` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `detail_produit`
--
ALTER TABLE `detail_produit`
  ADD CONSTRAINT `detail_produit_ibfk_2` FOREIGN KEY (`id_mat_pre`) REFERENCES `matiere_premier` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `detail_produit_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `detail_produit` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `facture_ibfk_5` FOREIGN KEY (`modifier_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `facture_ibfk_1` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `facture_ibfk_2` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `facture_ibfk_3` FOREIGN KEY (`creer_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `facture_ibfk_4` FOREIGN KEY (`annuler_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `matiere_premier`
--
ALTER TABLE `matiere_premier`
  ADD CONSTRAINT `matiere_premier_ibfk_2` FOREIGN KEY (`id_cat`) REFERENCES `categorie_mp` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `production`
--
ALTER TABLE `production`
  ADD CONSTRAINT `production_ibfk_5` FOREIGN KEY (`modifier_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `production_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `production_ibfk_2` FOREIGN KEY (`id_machine`) REFERENCES `machine` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `production_ibfk_3` FOREIGN KEY (`creer_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `production_ibfk_4` FOREIGN KEY (`annuler_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `reglement`
--
ALTER TABLE `reglement`
  ADD CONSTRAINT `reglement_ibfk_6` FOREIGN KEY (`modifier_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `reglement_ibfk_1` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `reglement_ibfk_2` FOREIGN KEY (`id_rip`) REFERENCES `rip_fournisseur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `reglement_ibfk_3` FOREIGN KEY (`id_devise`) REFERENCES `devise` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `reglement_ibfk_4` FOREIGN KEY (`creer_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `reglement_ibfk_5` FOREIGN KEY (`annuler_par`) REFERENCES `utilisateur` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `rip_fournisseur`
--
ALTER TABLE `rip_fournisseur`
  ADD CONSTRAINT `rip_fournisseur_ibfk_2` FOREIGN KEY (`id_four`) REFERENCES `fournisseur` (`id`) ON UPDATE NO ACTION;

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`id_mat_pre`) REFERENCES `matiere_premier` (`id`) ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
