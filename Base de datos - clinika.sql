CREATE DATABASE clinik; 
USE clinik;


CREATE TABLE Usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Agregar ID como clave primaria
    dni VARCHAR(20) UNIQUE NOT NULL,     -- DNI único
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    contraseña VARCHAR(255),
    rol ENUM('Paciente', 'Admin', 'Profesional') NOT NULL  -- Asegurar que cada usuario tenga un rol
);

CREATE TABLE Paciente (
    id INT PRIMARY KEY,  -- Cambiar a clave primaria que será igual al ID de Usuario
    numeroHistoriaClinica INT,
    fechaNacimiento DATE,
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE  -- Referencia al ID de Usuario
);

CREATE TABLE Admin (
    id INT PRIMARY KEY,  -- Cambiar a clave primaria que será igual al ID de Usuario
    numeroEmpleado INT,
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE  -- Referencia al ID de Usuario
);

CREATE TABLE Profesional (
    id INT PRIMARY KEY,  -- Cambiar a clave primaria que será igual al ID de Usuario
    matricula INT UNIQUE NOT NULL,
    telefonoProfesional VARCHAR(20) NOT NULL,
    horarioAtencion TIME,
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE  -- Referencia al ID de Usuario
);

CREATE TABLE Servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreServicio VARCHAR(100),
    descripcion TEXT
);

CREATE TABLE Especialidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreEspecialidad VARCHAR(100),
    descripcion TEXT
);

CREATE TABLE ProfesionalEspecialidad (
    matriculaProfesional INT,
    idEspecialidad INT,
    PRIMARY KEY (matriculaProfesional, idEspecialidad),
    FOREIGN KEY (matriculaProfesional) REFERENCES Profesional(matricula) ON DELETE CASCADE,
    FOREIGN KEY (idEspecialidad) REFERENCES Especialidad(id) ON DELETE CASCADE
);

CREATE TABLE Turno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechaTurno DATE,
    horaTurno TIME,
    idPaciente INT,  -- Cambiado a INT para referenciar id de Paciente
    idProfesional INT,  -- Cambiado a INT para referenciar id de Profesional
    idServicio INT,
    idAdmin INT,  -- Cambiado a INT para referenciar id de Admin
    estado ENUM('pendiente', 'confirmado', 'cancelado', 'completado') DEFAULT 'pendiente',
    FOREIGN KEY (idPaciente) REFERENCES Paciente(id) ON DELETE CASCADE,  -- Referencia a id de Paciente
    FOREIGN KEY (idProfesional) REFERENCES Profesional(id) ON DELETE CASCADE,  -- Referencia a id de Profesional
    FOREIGN KEY (idServicio) REFERENCES Servicio(id),
    FOREIGN KEY (idAdmin) REFERENCES Admin(id)  -- Referencia a id de Admin
);

CREATE TABLE IntervaloHorario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    horaInicio TIME NOT NULL,
    horaFin TIME NOT NULL,
    dia ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
    id_profesional INT,
    FOREIGN KEY (id_profesional) REFERENCES Profesional(id)  -- Cambiado a INT para referenciar id de Profesional
);

DELIMITER //

CREATE TRIGGER before_insert_paciente
BEFORE INSERT ON Paciente
FOR EACH ROW
BEGIN
    DECLARE user_role ENUM('Paciente', 'Admin', 'Profesional');

    -- Obtener el rol del usuario usando el DNI que se intenta insertar
    SELECT rol INTO user_role FROM Usuario WHERE dni = (SELECT dni FROM Paciente WHERE id = NEW.id);

    -- Si el rol es Admin o Profesional, lanzar un error
    IF user_role IN ('Admin', 'Profesional') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario ya es Admin o Profesional y no puede ser paciente.';
    END IF;
END; //

DELIMITER ;


SELECT * FROM clinik.paciente;

SELECT * FROM clinik.profesional;
SELECT * FROM clinik.usuario;
SELECT * FROM clinik.admin;



DROP TRIGGER IF EXISTS before_insert_paciente;

DELIMITER //

CREATE TRIGGER before_insert_profesional
BEFORE INSERT ON Profesional
FOR EACH ROW
BEGIN
    DECLARE user_role ENUM('Paciente', 'Admin', 'Profesional');

    -- Obtener el rol del usuario usando el id que se intenta insertar
    SELECT rol INTO user_role FROM Usuario WHERE id = NEW.id;

    -- Si el rol es Admin o Paciente, lanzar un error
    IF user_role IN ('Admin', 'Paciente') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario ya es Admin o Paciente y no puede ser profesional.';
    END IF;
END; //

DELIMITER ;


DELIMITER //

CREATE TRIGGER before_insert_admin
BEFORE INSERT ON Admin
FOR EACH ROW
BEGIN
    DECLARE user_role ENUM('Paciente', 'Admin', 'Profesional');

    -- Obtener el rol del usuario usando el id que se intenta insertar
    SELECT rol INTO user_role FROM Usuario WHERE id = NEW.id;

    -- Si el rol es Profesional o Paciente, lanzar un error
    IF user_role IN ('Profesional', 'Paciente') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario ya es Profesional o Paciente y no puede ser admin.';
    END IF;
END; //

DELIMITER ;
