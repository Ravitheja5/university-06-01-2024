CREATE TABLE Professor (
    id INTEGER PRIMARY  KEY AUTO_INCREMENT,
    name TEXT,
    department TEXT
);

CREATE TABLE Course (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    credits INTEGER,
    professorId INTEGER,
    FOREIGN KEY (professorId) REFERENCES Professor (id)
);

CREATE TABLE Student (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    email TEXT
);


CREATE TABLE Course_Student (
    courseId INTEGER,
    studentId INTEGER,
    PRIMARY KEY (courseId, studentId),
    FOREIGN KEY (courseId) REFERENCES Course (id),
    FOREIGN KEY (studentId) REFERENCES Student (id)
);