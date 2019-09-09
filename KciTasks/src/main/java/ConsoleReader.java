import java.sql.*;
import java.util.Scanner;

public class ConsoleReader {

    private static final String CREATE_CMD = "create";
    private static final String READ_CMD = "read";
    private static final String UPDATE_CMD = "update";
    private static Connection connection;
    private static Scanner scanner;
    private static Statement statement;

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

            printTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void parseCommand(String next) {
        next = next.toLowerCase();
        if (CREATE_CMD.equals(next)) {
            createCmd();
        } else if (READ_CMD.equals(next)) {
            readCmd();
        } else if (UPDATE_CMD.equals(next)) {
            updateCmd();
        } else {
            System.out.println("No such command.");
            listen();
        }
    }

    private static void updateCmd() {

    }

    private static void readCmd() {
        System.out.println("Read user by:");
        System.out.println("1 - Id;\n" +
                "2 - Name;\n" +
                "3 - Age;");
        int num = scanner.nextInt();
        String readBy = null;

        switch (num) {
            case 1:
                readBy = "id";
                break;
            case 2:
                readBy = "name";
                break;
            case 3:
                readBy = "age";
                break;
        }


        System.out.println("enter " + readBy + ": ");
        String value = scanner.next();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person WHERE " + readBy + " = '" + value + "'");

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
            statement.execute("INSERT INTO Person values ('" + id + "', '" + name + "', '" + age + "')");
            printTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listen();
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
