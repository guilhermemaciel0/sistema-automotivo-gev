-- ============================================
-- SISTEMA AUTOMOTIVO - Gestão de Estoque
-- Script MySQL - Criação e Dados de Exemplo
-- ============================================

-- Criar e selecionar o banco
CREATE DATABASE IF NOT EXISTS concessionaria_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE concessionaria_db;

-- ============================================
-- TABELA: marcas
-- ============================================
CREATE TABLE IF NOT EXISTS marcas (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL UNIQUE,
    pais_origem VARCHAR(50)
);

-- ============================================
-- TABELA: modelos
-- ============================================
CREATE TABLE IF NOT EXISTS modelos (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    marca_id  BIGINT NOT NULL,
    CONSTRAINT fk_modelo_marca FOREIGN KEY (marca_id) REFERENCES marcas(id)
);

-- ============================================
-- TABELA: veiculos
-- ============================================
CREATE TABLE IF NOT EXISTS veiculos (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo_id       BIGINT NOT NULL,
    ano_fabricacao  INT NOT NULL,
    cor             VARCHAR(50) NOT NULL,
    preco           DECIMAL(12,2) NOT NULL,
    quilometragem   INT NOT NULL DEFAULT 0,
    status          ENUM('DISPONIVEL','VENDIDO','RESERVADO','MANUTENCAO') NOT NULL DEFAULT 'DISPONIVEL',
    placa           VARCHAR(20) UNIQUE,
    chassi          VARCHAR(17) UNIQUE,
    observacoes     TEXT,
    CONSTRAINT fk_veiculo_modelo FOREIGN KEY (modelo_id) REFERENCES modelos(id)
);

-- ============================================
-- DADOS DE EXEMPLO
-- ============================================

-- Marcas
INSERT INTO marcas (nome, pais_origem) VALUES
('Toyota',      'Japão'),
('Honda',       'Japão'),
('Ford',        'Estados Unidos'),
('Chevrolet',   'Estados Unidos'),
('Volkswagen',  'Alemanha'),
('Hyundai',     'Coreia do Sul'),
('Fiat',        'Itália');

-- Modelos
INSERT INTO modelos (nome, categoria, marca_id) VALUES
('Corolla',     'Sedan',   1),
('Hilux',       'Pickup',  1),
('Civic',       'Sedan',   2),
('HR-V',        'SUV',     2),
('Ranger',      'Pickup',  3),
('Onix',        'Hatch',   4),
('Tracker',     'SUV',     4),
('Gol',         'Hatch',   5),
('T-Cross',     'SUV',     5),
('HB20',        'Hatch',   6),
('Creta',       'SUV',     6),
('Pulse',       'SUV',     7),
('Strada',      'Pickup',  7);

-- Veículos
INSERT INTO veiculos (modelo_id, ano_fabricacao, cor, preco, quilometragem, status, placa, chassi) VALUES
(1,  2023, 'Prata',    135000.00,     0, 'DISPONIVEL', 'ABC1D23', 'VIN00000000000001'),
(1,  2022, 'Branco',   125000.00, 15000, 'DISPONIVEL', 'DEF2E34', 'VIN00000000000002'),
(3,  2023, 'Preto',    145000.00,     0, 'DISPONIVEL', 'GHI3F45', 'VIN00000000000003'),
(4,  2022, 'Vermelho', 130000.00, 22000, 'DISPONIVEL', 'JKL4G56', 'VIN00000000000004'),
(6,  2023, 'Branco',    85000.00,     0, 'DISPONIVEL', 'MNO5H67', 'VIN00000000000005'),
(7,  2022, 'Cinza',    120000.00, 18000, 'VENDIDO',    'PQR6I78', 'VIN00000000000006'),
(9,  2023, 'Azul',     115000.00,     0, 'DISPONIVEL', 'STU7J89', 'VIN00000000000007'),
(11, 2022, 'Prata',    125000.00, 30000, 'DISPONIVEL', 'VWX8K90', 'VIN00000000000008'),
(12, 2023, 'Branco',    95000.00,     0, 'DISPONIVEL', 'YZA9L01', 'VIN00000000000009'),
(2,  2022, 'Preto',    295000.00, 12000, 'RESERVADO',  'BCD0M12', 'VIN00000000000010');

-- ============================================
-- CONSULTAS ÚTEIS PARA TESTE
-- ============================================

-- Ver todos os veículos com marca e modelo
-- SELECT v.id, m2.nome AS marca, m.nome AS modelo, v.ano_fabricacao, v.cor, v.preco, v.status
-- FROM veiculos v
-- JOIN modelos m ON v.modelo_id = m.id
-- JOIN marcas m2 ON m.marca_id = m2.id;

-- Filtrar por disponíveis
-- SELECT * FROM veiculos WHERE status = 'DISPONIVEL';

-- Filtrar por faixa de preço
-- SELECT * FROM veiculos WHERE preco BETWEEN 80000 AND 130000;
