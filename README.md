# 🛒 E-commerce Web Application – MVC2 & JPA

## 📌 Présentation du projet

Ce projet consiste à développer une application web E-commerce permettant de simuler le fonctionnement d'une boutique en ligne.

L'application permet aux internautes de consulter les produits disponibles, de gérer leur panier et de gérer leurs informations personnelles.

Le projet est réalisé dans le cadre de l'atelier **JEE**, en mettant en pratique l'architecture **MVC 2** et l'API **JPA (Java Persistence API)** pour la gestion de la persistance des données.

## 🎯 Objectifs

- Comprendre et appliquer le modèle architectural MVC 2.
- Maîtriser l'utilisation des Servlets et des JSP.
- Manipuler les entités et les relations avec JPA.
- Effectuer les opérations CRUD avec JPA.
- Assurer la communication entre Java et MySQL.
- Séparer les différentes couches de l'application.

## 🛠️ Technologies utilisées

| Technologie | Utilisation |
|---|---|
| Java | Langage de programmation |
| JEE / Servlet | Gestion des requêtes HTTP |
| JSP | Interface utilisateur |
| JSTL | Affichage dynamique |
| JPA | Persistance des données |
| EclipseLink | Implémentation de JPA |
| MySQL | Système de gestion de base de données |
| Maven | Gestion des dépendances |
| Apache Tomcat 9 | Serveur web |
| HTML / CSS / JavaScript | Interface utilisateur |

## 🏗️ Architecture du projet

Le projet adopte une architecture MVC 2 organisée en plusieurs packages.

```text
ecommerce-jpa/
│
├── src/main/java/
│   └── com.fstt/
│       ├── controller/
│       │   ├── CategorieServlet.java
│       │   ├── PanierServlet.java
│       │   ├── ProduitServlet.java
│       │   └── UtilisateurServlet.java
│       │
│       ├── dao/
│       │   ├── CategorieDao.java
│       │   ├── CategorieDaoImpl.java
│       │   ├── PanierDao.java
│       │   ├── PanierDaoImpl.java
│       │   ├── ProduitDao.java
│       │   ├── ProduitDaoImpl.java
│       │   ├── UtilisateurDao.java
│       │   └── UtilisateurDaoImpl.java
│       │
│       ├── model/
│       │   ├── Categorie.java
│       │   ├── LignePanier.java
│       │   ├── Panier.java
│       │   ├── Produit.java
│       │   └── Utilisateur.java
│       │
│       └── util/
│           ├── JPAUtil.java
│           └── TestJPA.java
│
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml
│
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── views/
│   │   ├── beans.xml
│   │   └── web.xml
│   └── index.jsp
│
└── pom.xml
```


## 🔄 Architecture MVC 2

L'application repose sur quatre composants principaux :

- **Model :** représente les entités métier et les données persistées avec JPA.
- **View :** les pages JSP responsables de l'affichage.
- **Controller :** les Servlets qui traitent les requêtes et coordonnent les opérations.
- **DAO :** assure l'accès aux données et les opérations de persistance avec JPA.

## ⚙️ Configuration et installation

### Prérequis

- JDK compatible avec le projet.
- Eclipse IDE.
- Apache Tomcat 9.
- MySQL Server.
- Maven.

### Étapes d'installation

**1. Cloner le repository**

```bash
git clone https://github.com/salma2134/ecommerce-jpa.git
```

**2. Importer le projet dans Eclipse**

- Ouvrir Eclipse.
- Choisir File → Import.
- Sélectionner Existing Maven Projects.
- Choisir le dossier du projet.

**3. Créer la base de données MySQL**

```sql
CREATE DATABASE ecommerce_db;
```

**4. Configurer la connexion**

Modifier les paramètres de connexion dans :

```text
src/main/resources/META-INF/persistence.xml
```

Configurer le nom de la base, l'utilisateur et le mot de passe MySQL.

**5. Installer les dépendances Maven**

```bash
mvn clean install
```

**6. Exécuter l'application**

- Configurer Apache Tomcat 9 dans Eclipse.
- Ajouter le projet au serveur.
- Démarrer Tomcat.
- Accéder à l'application :

```text
http://localhost:8080/ecommerce-jpa/
```

## 🗄️ Persistance des données

La persistance est assurée par JPA avec EclipseLink comme fournisseur.

Le fichier `persistence.xml` définit :
- L'unité de persistance `ecommercePU`.
- Le fournisseur EclipseLink.
- La connexion à la base MySQL.
- Les entités JPA.
- Les paramètres de génération des tables.

## 👩‍💻 Réalisé dans le cadre de

**Université Abdelmalek Essaâdi**  
Faculté des Sciences et Techniques de Tanger  
Département Génie Informatique  
Cycle Ingénieur – Logiciels et Systèmes Intelligents
