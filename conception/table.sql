-- Création de la table User
CREATE TABLE "User" (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL,
    mdp VARCHAR(255) NOT NULL,
    dtn DATE NOT NULL,
    solde NUMERIC(15, 2) DEFAULT 0 CHECK (solde >= 0)
);

-- Création de la table crypto_monnaie
CREATE TABLE crypto_monnaie (
    id SERIAL PRIMARY KEY,
    disignation VARCHAR(255) NOT NULL,
    symbol VARCHAR(10) NOT NULL,
    prix_unitaire NUMERIC(15, 2) NOT NULL CHECK (prix_unitaire >= 0),
    variation NUMERIC(5, 2)
);

-- Création de la table portefeuille
CREATE TABLE portefeuille (
    id SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    id_crypto_monnaie INT NOT NULL,
    quantite NUMERIC(15, 6) NOT NULL CHECK (quantite >= 0),
    FOREIGN KEY (id_user) REFERENCES "User" (id) ON DELETE CASCADE,
    FOREIGN KEY (id_crypto_monnaie) REFERENCES crypto_monnaie (id) ON DELETE CASCADE
);

-- Création de la table mvt_solde
CREATE TABLE mvt_solde (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    id_user INT NOT NULL,
    depot NUMERIC(15, 2) DEFAULT 0 CHECK (depot >= 0),
    retrait NUMERIC(15, 2) DEFAULT 0 CHECK (retrait >= 0),
    solde_restant NUMERIC(15, 2) NOT NULL CHECK (solde_restant >= 0),
    FOREIGN KEY (id_user) REFERENCES "User" (id) ON DELETE CASCADE
);

-- Création de la table transaction_crypto
CREATE TABLE transaction_crypto (
    id SERIAL PRIMARY KEY,
    id_crypto_monnaie INT NOT NULL,
    quantite NUMERIC(15, 6) NOT NULL CHECK (quantite > 0),
    prix_total NUMERIC(15, 2) NOT NULL CHECK (prix_total >= 0),
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    type_transaction VARCHAR(10) NOT NULL CHECK (type_transaction IN ('achat', 'vente')),
    FOREIGN KEY (id_crypto_monnaie) REFERENCES crypto_monnaie (id) ON DELETE CASCADE
);