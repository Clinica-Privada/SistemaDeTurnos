
CREATE DATABASE IF NOT EXISTS clinik;
USE clinik;



	
CREATE TABLE Admin (
    idAdmin INT PRIMARY KEY,  -- Cambiar a clave primaria que será igual al ID de Usuario
    numeroEmpleado INT,
    FOREIGN KEY (idAdmin) REFERENCES Usuario(dni) ON DELETE CASCADE  -- Referencia al ID de Usuario
);

CREATE TABLE AdminEspecialidad(
    idAdmin INT NOT NULL,
    idEspecialidad INT NOT NULL, 
    PRIMARY KEY (idAdmin,idEspecialidad),
    FOREIGN KEY (idAdmin) REFERENCES Admin(idAdmin) ON DELETE CASCADE,
    FOREIGN KEY (idEspecialidad) REFERENCES Especialidad(idEspecialidad) ON DELETE CASCADE
);

CREATE TABLE AdminServicio(
    idAdmin INT NOT NULL,
    idServicio INT NOT NULL,
    PRIMARY KEY(idAdmin, idServicio),
    FOREIGN KEY(idAdmin) REFERENCES Admin(idAdmin),
    FOREIGN KEY(idServicio) REFERENCES Servicio(idServicio)
);

CREATE TABLE AdminTurno(
    idAdmin INT NOT NULL,
    idTurno INT NOT NULL,
    PRIMARY KEY (idAdmin, idTurno),
    FOREIGN KEY (idAdmin) REFERENCES Admin(idAdmin) ON DELETE CASCADE,
    FOREIGN KEY (idTurno) REFERENCES Turno(idTurno) ON DELETE CASCADE
);
CREATE TABLE Especialidad (
    idEspecialidad INT AUTO_INCREMENT NOT NULL,
    nombreEspecialidad VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    idServicio INT NOT NULL,
    PRIMARY KEY (idEspecialidad),
    FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio) ON DELETE CASCADE -- Referencia al idServicio de Servicio.
);
CREATE TABLE IntervaloHorario (
    idIntervaloHorario INT  AUTO_INCREMENT NOT NULL,
    horaInicio TIME NOT NULL,
    horaFin TIME NOT NULL,
    dia ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
    idProfesional INT,
    PRIMARY KEY (idIntervaloHorario),
    FOREIGN KEY (idProfesional) REFERENCES Profesional(idProfesional)  -- Cambiado a INT para referenciar id de Profesional
);
CREATE TABLE Paciente (
    idPaciente INT UNIQUE NOT NULL,  -- Cambiar a clave primaria que será igual al ID de Usuario
    numeroHistoriaClinica INT NOT NULL,
    PRIMARY KEY (idPaciente),
    FOREIGN KEY (idPaciente) REFERENCES Usuario(dni) ON DELETE CASCADE  -- Referencia al ID de Usuario
);

CREATE TABLE Profesional (
    idProfesional INT NOT NULL,  -- Cambiar a clave primaria que será igual al ID de Usuario
    matricula INT UNIQUE NOT NULL,
    PRIMARY KEY(idProfesional),
    FOREIGN KEY (idProfesional) REFERENCES Usuario(dni) ON DELETE CASCADE  -- Referencia al DNI de Usuario
);

CREATE TABLE ProfesionalEspecialidad (
    matriculaProfesional INT NOT NULL,
    idEspecialidad INT NOT NULL,
    PRIMARY KEY (matriculaProfesional, idEspecialidad),
    FOREIGN KEY (matriculaProfesional) REFERENCES Profesional(matricula) ON DELETE CASCADE,
    FOREIGN KEY (idEspecialidad) REFERENCES Especialidad(idEspecialidad) ON DELETE CASCADE
);

CREATE TABLE Servicio (
    idServicio INT AUTO_INCREMENT,
    nombreServicio VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    PRIMARY KEY (idServicio)
);

CREATE TABLE Turno (
    idTurno INT AUTO_INCREMENT NOT NULL ,
    fechaTurno DATE NOT NULL,
    horaTurno TIME NOT NULL,
    idPaciente INT NOT NULL,  -- Cambiado a INT para referenciar id de Paciente
    idProfesional INT NOT NULL,  -- Cambiado a INT para referenciar id de Profesional
    idServicio INT NOT NULL,
    idAdmin INT,  -- Cambiado a INT para referenciar id de Admin
    estado ENUM('pendiente', 'confirmado', 'cancelado', 'completado') DEFAULT 'pendiente',
    PRIMARY KEY (idTurno),
    FOREIGN KEY (idPaciente) REFERENCES Paciente(idPaciente) ON DELETE CASCADE,  -- Referencia a id de Paciente
    FOREIGN KEY (idProfesional) REFERENCES Profesional(idProfesional) ON DELETE CASCADE,  -- Referencia a id de Profesional
    FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio),
    FOREIGN KEY (idAdmin) REFERENCES Admin(idAdmin)  -- Referencia a id de Admin
);

CREATE TABLE Usuario(
    dni INT UNIQUE NOT NULL,     -- DNI único
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol ENUM('Paciente', 'Admin', 'Profesional') NOT NULL,  -- Asegurar que cada usuario tenga un rol
    fechaNacimiento DATE NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR (20) NOT NULL,
    PRIMARY KEY (dni)
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

