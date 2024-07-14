CREATE TABLE curso (
    id bigint PRIMARY KEY auto_increment,
    nome varchar(30),
    categoria varchar(30)
);

CREATE TABLE usuario (
    id bigint PRIMARY KEY auto_increment,
    nome varchar(50),
    email varchar(30),
    senha varchar(20)
);

CREATE TABLE topico (
    id bigint PRIMARY KEY auto_increment,
    titulo varchar(255),
    mensagem varchar(255),
    data_criacao Date,
    estado_do_topico varchar(20),
    autor_id bigint REFERENCES usuario(id),
    curso_id bigint REFERENCES curso(id)
);

CREATE TABLE resposta (
    id bigint PRIMARY KEY auto_increment,
    mensagem varchar(255),
    data_criacao Date,
    solucao varchar(255),
    topico_id bigint REFERENCES topico(id),
    autor_id bigint REFERENCES usuario(id)
);