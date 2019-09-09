import java.sql.*;
import java.util.Scanner;

public class ConsoleReader {

    private static Connection connection;
    private static Scanner scanner;
    private static Statement statement;

    public enum CMD {
        CREATE, READ, UPDATE
    }

    public static void main(String[] args) {
        initConnection();

        scanner = new Scanner(System.in);
        listen();

    }

    private static void listen() {
        System.out.println("Type command create, read, update");
        if (scanner.hasNext()) {
            String next = scanner.nextLine();
            parseCommand(next);
//        parseCommand("create");
//        parseCommand("read");
        }
    }

    private static void initConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");

            statement = connection.createStatement();
            statement.execute("CREATE TABLE Person(id INTEGER, name VARCHAR, age INTEGER)");

            statement.execute("INSERT INTO Person values (0, 'Jora', 29)");
            statement.execute("INSERT INTO Person values (1, 'Liusia', 23)");
            statement.execute("INSERT INTO Person values (2, 'Petia', 321)");
            statement.execute("INSERT INTO Person values (2, 'Tania', 123)");

//            printTable();
//            closeStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeStatement() throws SQLException {
        statement.close();
        statement = null;
    }

    private static void parseCommand(String next) {
        CMD cmd = CMD.valueOf(next.toUpperCase());


        switch (cmd) {
            case CREATE:
                createCmd();
                break;
            case READ:
                readCmd();
                break;
            case UPDATE:
                break;


        }


    }

    private static void readCmd() {
        System.out.println("Read user by:");
        System.out.println("1 - Id;\n" +
                "2 - Name;\n" +
                "3 - Age;");
        int num = scanner.nextInt();
        String type = null;

        switch (num) {
            case 1:
                type = "id";
                break;
            case 2:
                type = "name";
                break;
            case 3:
                type = "age";
                break;
        }


        System.out.println("enter " + type + ": ");
        String value = scanner.next();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person WHERE " + type + " = '" + value + "'");

            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getString("id") +
                        ", name: " + resultSet.getString("name") +
                        ", age: " + resultSet.getString("age"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listen();
    }

    private static void createCmd() {
        System.out.println("Create new user:");
        System.out.print("Id: ");
        int id = scanner.nextInt();
        System.out.print("\nName: ");
        String name = scanner.next();
        System.out.print("\nAge: ");
        int age = scanner.nextInt();
        try {
//            statement = connection.createStatement();
            statement.execute("INSERT INTO Person values ('" + id + "', '" + name + "', '" + age + "')");
            printTable();
            closeStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private static void printTable() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Person");
        while (resultSet.next()) {
            System.out.println("id: " + resultSet.getString("id") +
                    ", name: " + resultSet.getString("name") +
                    ", age: " + resultSet.getString("age"));
        }
    }
}
