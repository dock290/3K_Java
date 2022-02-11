package psuti.kirgizov.database;

import psuti.kirgizov.phone.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static final String DEFAULT_USER = "root";
    public static final String DEFAULT_PASS = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:3306?useUnicode=true&serverTimezone=UTC";

    public static final String DATABASE = "JAVA_LAB_5";

    public static final String TABLE = "Phones";

    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String MANUFACTURER = "manufacturer";
    public static final String OPERATING_SYSTEM = "operatingsystem";
    public static final String RELEASE_YEAR = "releaseyear";
    public static final String SITE = "site";
    public static final String SCORE = "score";

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String user;
    private String pass;

    public Database() {
        setUser(DEFAULT_USER, DEFAULT_PASS);
    }

    public void setUser(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void add(int number, String name, String manufacturer, String operatingSystem, int releaseYear, String site, int score) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        statement.execute(String.format("INSERT INTO %s VALUES (%d, '%s', '%s', '%s', %d, '%s', %d)",
                TABLE,
                number, manufacturer, manufacturer,
                operatingSystem, releaseYear, site, score)
        );

        statement.close();
        connection.close();
    }

    public void set(int number, String name, String manufacturer, String operatingSystem, int releaseYear, String site, int score) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        statement.execute(String.format("UPDATE %s SET %s = \"%s\", %s = \"%s\", %s = \"%s\", %s = %d, %s = \"%s\", %s = %d WHERE %s = %d",
                TABLE,
                NAME, name, MANUFACTURER, manufacturer,
                OPERATING_SYSTEM, operatingSystem,
                RELEASE_YEAR, releaseYear,
                SITE, site, SCORE, score,
                NUMBER, number)
        );

        statement.close();
        connection.close();
    }

    public List<Phone> getByNumber(int number) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        ResultSet resultSet = statement.executeQuery(
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE, NUMBER, number));

        List<Phone> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Phone(resultSet.getInt(NUMBER),
                    resultSet.getString(NAME),
                    resultSet.getString(MANUFACTURER),
                    resultSet.getString(OPERATING_SYSTEM),
                    resultSet.getInt(RELEASE_YEAR),
                    resultSet.getString(SITE),
                    resultSet.getInt(SCORE))
            );
        }

        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    public List<Phone> getByName(String name) throws SQLException {
        List<Phone> phoneList = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE %s = \"%s\"",
                TABLE, NAME, name));

        while (resultSet.next()) {
            phoneList.add(new Phone(resultSet.getInt(NUMBER), resultSet.getString(NAME),
                    resultSet.getString(MANUFACTURER), resultSet.getString(OPERATING_SYSTEM),
                    resultSet.getInt(RELEASE_YEAR), resultSet.getString(SITE), resultSet.getInt(SCORE)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return phoneList;
    }

    public List<Phone> getAll() throws SQLException {
        List<Phone> phoneList = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", TABLE));

        while (resultSet.next()) {
            phoneList.add(new Phone(resultSet.getInt(NUMBER), resultSet.getString(NAME),
                    resultSet.getString(MANUFACTURER), resultSet.getString(OPERATING_SYSTEM),
                    resultSet.getInt(RELEASE_YEAR),
                    resultSet.getString(SITE), resultSet.getInt(SCORE)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return phoneList;
    }

    public void remove(int number) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        statement.execute(String.format("DELETE FROM %s WHERE %s=%d", TABLE, NUMBER, number));

        statement.close();
        connection.close();
    }

    public void reset() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("DROP DATABASE IF EXISTS %s", DATABASE));
        statement.execute(String.format("CREATE DATABASE %s", DATABASE));
        statement.execute(String.format("USE %s", DATABASE));
        statement.execute(String.format("CREATE TABLE %s (" +
                " %s INT NOT NULL,\n" +
                " %s VARCHAR(255) NOT NULL,\n" +
                " %s VARCHAR(255) NOT NULL,\n" +
                " %s VARCHAR(255) NOT NULL,\n" +
                " %s INT NOT NULL,\n" +
                " %s VARCHAR(255) NOT NULL,\n" +
                " %s INT,\n" +
                " PRIMARY KEY ( %s ) )", TABLE, NUMBER, NAME, MANUFACTURER, OPERATING_SYSTEM, RELEASE_YEAR, SITE, SCORE, NUMBER));

        statement.execute("INSERT INTO Phones VALUES (1584, 'HONOR 9', 'HUAWEI', 'Android 9', 2017, 'WebPack', 5)");
        statement.execute("INSERT INTO Phones VALUES (2765, 'Xiaomi Redmi Note 10', 'Xiaomi', 'Android 10', 2020, 'Maven', 4)");
        statement.execute("INSERT INTO Phones VALUES (3543, 'Samsung Galaxy S10e', 'Samsung Electronics', 'Android 10', 2002, 'Gradle', 5)");
        statement.execute("INSERT INTO Phones VALUES (4123, 'Nokia 6131', 'Nokia', 'Series 40', 2003, 'MySQL', 4)");
        statement.execute("INSERT INTO Phones VALUES (532, 'Moto E', 'Motorola Mobility', 'Android 5.0.2', 2005, 'phpMyAdmin', 3)");
        statement.execute("INSERT INTO Phones VALUES (63, 'Redmi Note 9', 'Xiaomi Corporation', 'Android 10', 2020, 'Root', 5)");
        statement.execute("INSERT INTO Phones VALUES (432, 'P90', 'HUAWEI', 'Android 9', 2017, 'Root', 2)");
        statement.execute("INSERT INTO Phones VALUES (12, 'ZTE Axon 20 5G', 'ZTE', 'Android 10', 2020, 'tomcat', 4)");
        statement.execute("INSERT INTO Phones VALUES (42, 'Siemens A60', 'Siemens AG', 'Siemens', 2003, 'Java', 4)");
        statement.execute("INSERT INTO Phones VALUES (32, 'Nokia 6680', 'Nokia', 'Series 60', 2005, 'src', 3)");

        statement.close();
        connection.close();
    }

    public void cout() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, user, pass);
        Statement statement = connection.createStatement();

        statement.execute(String.format("USE %s", DATABASE));
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", TABLE));

        while (resultSet.next()) {
            int number = resultSet.getInt(NUMBER);
            String name = resultSet.getString(NAME);
            String manufacturer = resultSet.getString(MANUFACTURER);
            String operatingsystem = resultSet.getString(OPERATING_SYSTEM);
            int releaseyear = resultSet.getInt(RELEASE_YEAR);
            String site = resultSet.getString(SITE);
            int score = resultSet.getInt(SCORE);

            System.out.print("Number: " + number);
            System.out.print(", Name: " + name);
            System.out.print(", Manufacturer: " + manufacturer);
            System.out.print(", Operating System: " + operatingsystem);
            System.out.print(", Release Year: " + releaseyear);
            System.out.print(", Site: " + site);
            System.out.println(", Score: " + score);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
