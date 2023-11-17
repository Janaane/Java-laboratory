package org.example;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        //createTable();
        //fillingTable();
        task();
    }
    public static void createTable() {
        try {
            connection();
            statement.executeUpdate("drop table if exists progress, subjects, students;");
            statement.executeUpdate("create table students(" +
                    "id serial PRIMARY KEY, " +
                    "name varchar NOT NULL, " +
                    "passportSeries int NOT NULL, " +
                    "passportNumber int NOT NULL," +
                    "UNIQUE(passportSeries)," +
                    "UNIQUE(passportNumber)" +
                    ")");
            statement.executeUpdate("create table subjects(" +
                    "nameSubjects varchar PRIMARY KEY)");

            statement.executeUpdate("create table progress(" +
                    "id serial, " +
                    "idStudent integer," +
                    "foreign key (idStudent) references students(id) on delete cascade, " +
                    "nameSubjects varchar NOT NULL, " +
                    "FOREIGN KEY (nameSubjects) REFERENCES subjects(nameSubjects) ON DELETE CASCADE," +
                    "score smallint NOT NULL," +
                    "CHECK(score >= 2 AND score <= 5)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnection();
        }
    }

    public static void fillingTable() {
        try {
            createTable();
            connection();
            statement.executeUpdate("insert into students(name, passportSeries, passportNumber) VALUES ('Дима', 3318, 123465)");
            statement.executeUpdate("insert into students(name, passportSeries, passportNumber) VALUES ('Денис', 4566, 123746)");
            statement.executeUpdate("insert into students(name, passportSeries, passportNumber) VALUES ('Андрей', 3565, 214746)");
            statement.executeUpdate("insert into students(name, passportSeries, passportNumber) VALUES ('Матвей', 4567, 124746)");
            statement.executeUpdate("insert into students(name, passportSeries, passportNumber) VALUES ('Саша', 5565, 555555)");

            statement.executeUpdate("insert into subjects(nameSubjects) VALUES ('Физика')");
            statement.executeUpdate("insert into subjects(nameSubjects) VALUES ('Биология')");
            statement.executeUpdate("insert into subjects(nameSubjects) VALUES ('Информатика')");
            statement.executeUpdate("insert into subjects(nameSubjects) VALUES ('Математика')");

            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (1, 'Физика', 5)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (2, 'Физика', 4)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (3, 'Физика', 3)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (4, 'Физика', 5)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (5, 'Физика', 4)");

            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (1, 'Математика', 4)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (2, 'Математика', 3)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (3, 'Математика', 3)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (4, 'Математика', 4)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (5, 'Математика', 3)");

            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (1, 'Информатика', 4)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (2, 'Информатика', 5)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (3, 'Информатика', 2)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (4, 'Информатика', 3)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (5, 'Информатика', 4)");

            //statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (1, 'Биология', 3)");
            //statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (2, 'Биология', 3)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (3, 'Биология', 5)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (4, 'Биология', 4)");
            statement.executeUpdate("insert into progress(idStudent, nameSubjects, score) VALUES (5, 'Биология', 3)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnection();
        }
    }

    public static void task() {
        try {
            connection();

            System.out.println("- Студенты сдавшие физику больше чем на 3: ");
            ResultSet rs = statement.executeQuery("SELECT s.id, s.name, score FROM progress JOIN students s on progress.idStudent = s.id WHERE score > 3 and nameSubjects = 'Физика';");
            while (rs.next()) {
                String name = rs.getString("name");
                int idStudent = rs.getInt("id");
                int score = rs.getInt("score");
                System.out.println(name + " (id: " + idStudent + ") сдал на " + score);
            }

            rs = statement.executeQuery("SELECT avg(score), idstudent\n" +
                    "FROM progress\n" +
                    "WHERE idStudent = (SELECT id FROM students WHERE name = 'Дима')\n" +
                    "group by idstudent;");
            if(rs.next()){
                System.out.println("\n- Средний балл Димы (id: " + rs.getInt("idstudent") + ") по всем предметам:");
                System.out.println(String.format("%.2f", rs.getFloat("avg")).replace(",", "."));
            }

            System.out.println("\n- Средний балл всех студентов по математике:");
            rs = statement.executeQuery("select avg(score) from progress where namesubjects = 'Математика'");
            if (rs.next()){
                System.out.println(rs.getFloat("avg"));
            }

            System.out.println("\n- Три предмета, которые сдали наибольшее кол-во студентов:");
            rs = statement.executeQuery("SELECT nameSubjects, COUNT(*) AS succes\n" +
                    "FROM progress\n" +
                    "WHERE score > 2\n" +
                    "GROUP BY nameSubjects ORDER BY succes DESC\n" +
                    "limit 3;");
            while (rs.next()) {
                String nameSub = rs.getString("nameSubjects");
                int count = rs.getInt("succes");
                String chel = "человек";
                if (2 <= count && count <= 4){
                    chel = "человека";
                }
                System.out.println(nameSub + " (сдало " + count + " " + chel + ")");
            }

            System.out.println("\n- Студенты, имеющие академ. задолженность (ИГППА):");
            rs = statement.executeQuery("SELECT s.id, s.name, ps.namesubjects, NULL AS score\n" +
                    "FROM students s\n" +
                    "         CROSS JOIN (\n" +
                    "    SELECT DISTINCT namesubjects\n" +
                    "    FROM progress\n" +
                    ") ps\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM progress p\n" +
                    "    WHERE p.idStudent = s.id AND p.namesubjects = ps.namesubjects\n" +
                    ")\n" +
                    "UNION ALL\n" +
                    "SELECT s.id, s.name, p.namesubjects, p.score\n" +
                    "FROM progress p\n" +
                    "         JOIN students s ON p.idStudent = s.id\n" +
                    "WHERE p.score = 2\n" +
                    "GROUP BY s.id, s.name, p.namesubjects, p.score\n" +
                    "ORDER BY id DESC;");
            while(rs.next()){
                String subj = rs.getString("namesubjects");
                String name = rs.getString("name");
                int idname = rs.getInt("id");
                String score = rs.getString("score");
                if (score == null)
                    System.out.println(name + " (id: " + idname + ") получил незачет/неявку по предмету " + subj.toLowerCase());
                else if (score.equals("2"))
                    System.out.println(name + " (id: " + idname + ") получил 2 по предмету " + subj.toLowerCase());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnection();
        }
    }

    public static void connection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java10", "postgres", "3232");
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Не удалось подключиться");
        }
    }

    public static void disconnection() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
