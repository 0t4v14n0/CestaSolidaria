CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    tipo_beneficio ENUM('TIPO1','TIPO2','TIPO3'),
    data_nascimento VARCHAR(100) NOT NULL,
    renda_total DECIMAL(10, 2) DEFAULT 0.00,
    creditos DECIMAL(10, 2) DEFAULT 0.00,
    status ENUM('PENDENTE', 'APROVADO', 'REJEITADO') DEFAULT 'PENDENTE',
    situacao ENUM('TRABALHANDO', 'ESTUDANDO', 'DESEMPREGADO'),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    residencia_id INT
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuario_roles (
    usuario_id INT,
    role_id INT,
    PRIMARY KEY (usuario_id, role_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE dependentes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    data_nascimento VARCHAR(100) NOT NULL,
    cpf VARCHAR(100) UNIQUE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    situacao ENUM('TRABALHANDO', 'ESTUDANDO', 'DESENPREGADO'),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    criado_em TIMESTAMP
);

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    volume VARCHAR(100) NOT NULL,
    categoria_id INT,
    url_imagem VARCHAR(500) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE carrinho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    total DECIMAL(10, 2) DEFAULT 0.00,
    StatusCarrinho ENUM('ABERTO', 'PREPARANDO', 'ESPERANDO_COLETA', 'CANCELADO', 'COLETADO') DEFAULT 'ABERTO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE carrinho_itens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carrinho_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (carrinho_id) REFERENCES carrinho(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

CREATE TABLE residencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    referencia VARCHAR(100) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE historico_creditos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo ENUM('ADICIONADO', 'GASTO') NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    descricao TEXT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

INSERT INTO roles (role_name) VALUES ('USER'), ('ADMIN'), ('BENEFICIARIO');

INSERT INTO categorias (nome) 
VALUES 
('ALIMENTO'),
('BEBIDA'),
('LIMPEZA'),
('HIGIENE'),
('HORTIFRUTI'),
('PADARIA'),
('MERCEARIA'),
('PAPELARIA');
