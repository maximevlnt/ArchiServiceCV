# Création de la base de données

```sql
CREATE DATABASE MicroservicesDB;
USE MicroservicesDB;

-- Création de la base de données
CREATE DATABASE MicroservicesDB;
USE MicroservicesDB;

-- Table des utilisateurs et de leurs détails (Users)
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Helper', 'InNeed') NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    address TEXT,
    validation_status ENUM('pending', 'validated', 'rejected') DEFAULT 'pending',  -- Statut de validation
    validation_reason TEXT NULL,  -- Raison de rejet
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table des demandes (Requests)
CREATE TABLE Requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_by INT NOT NULL,  -- Référence à Users.id
    description TEXT NOT NULL,
    status ENUM('waiting', 'validated', 'rejected', 'chosen', 'realized') DEFAULT 'waiting',
    validation_reason TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table des affectations (Assignments)
CREATE TABLE Assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    request_id INT NOT NULL,  -- Référence à Requests.id
    helper_id INT NOT NULL,  -- Référence à Users.id
    assignment_status ENUM('pending', 'active', 'completed') DEFAULT 'pending',
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP NULL,
    FOREIGN KEY (request_id) REFERENCES Requests(id) ON DELETE CASCADE,
    FOREIGN KEY (helper_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table des retours (Feedback)
CREATE TABLE Feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_user INT NOT NULL,  -- Référence à Users.id
    to_user INT NOT NULL,    -- Référence à Users.id
    rating INT CHECK (rating BETWEEN 1 AND 5),  -- Note de 1 à 5
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (from_user) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (to_user) REFERENCES Users(id) ON DELETE CASCADE
);

