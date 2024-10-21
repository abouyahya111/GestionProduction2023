--script table sub catégorie

INSERT INTO sub_categorie_mp (code,nom)
VALUES ("TH001","THE");

INSERT INTO sub_categorie_mp (code,nom)
VALUES ("CA001","CARTON");

INSERT INTO sub_categorie_mp (code,nom)
VALUES ("CE001","CELLOPHANE");

INSERT INTO sub_categorie_mp (code,nom)
VALUES ("PAP001","PAPIER");

INSERT INTO sub_categorie_mp (code,nom)
VALUES ("CEN001","CENTURE");

--script table catégorie
-- catégorie thé 

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("CH001","CHAARA",1);
INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("HB001","MKARKAB",1);

-- catégorie carton
INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("100G","CARTON 100G",2);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("200G","CARTON 200G",2);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("250G","CARTON 250G",2);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("500G","CARTON 500G",2);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("1KG","CARTON 1KG",2);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("5KG","CARTON 5KG",2);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("10KG","CARTON 10KG",2);

-- catégorie cellophane
INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("CEL100G","CELLOPHANE 100G",3);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("CEL200G","CELLOPHANE 200G",3);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("CEL250G","CELLOPHANE 250G",3);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("CEL500G","CELLOPHANE 500G",3);


-- catégorie alluminium
INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("ALU100G","ALUMINIUM 100G",3);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("ALU100G","ALUMINIUM 200G",3);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("ALU100G","ALUMINIUM 250G",3);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("ALU100G","ALUMINIUM 500G",3);

-- catégorie papier
INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("PAP002","PAPIER INTERNE CARTON 10KG",4);

INSERT INTO categorie_mp (code,nom,id_sub_cat)
VALUES ("CEN001","CENTURE PLASTIQUE",5);

/*tableau sequenceur pour générer les codes */
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("PARAM_","Parametre",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("MAG_","Magasin",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("MP_","MatierePremier",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("LIM_","LigneMachine",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("EQU_","Equipe",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("EMP_","Employe",1);

INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("OF_","TANTAN",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("OF_","LAAYOUNE",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("M","Machine",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("C","Client",1);
INSERT INTO sequenceur (code,LIBELLE,VALEUR)
VALUES ("2017","FactureProduction",1);



/*Type Equipe*/
insert into `type_equipe`(`id`,`CODE`,`LIBELLE`,`SEQUENCE`) 
values (1,'E_PROD','EQUIPE PRPDUCTION',1);
insert into `type_equipe`(`id`,`CODE`,`LIBELLE`,`SEQUENCE`) 
values (2,'E_GEN','EQUIPE GENERIQUE',1);


/*Type Resonsabilité employé */
insert into `type_res_employe`(`id`,`code`,`libelle`) values (1,'REP','CHEF PRODUCTION');
insert into `type_res_employe`(`id`,`code`,`libelle`) values (2,'REE','CHEF EQUIPE');
insert into `type_res_employe`(`id`,`code`,`libelle`) values  (3,'MOP','OUVRIER PRODUCTION');
insert into `type_res_employe`(`id`,`code`,`libelle`) values  (4,'TEC','TECHNICIEN');







