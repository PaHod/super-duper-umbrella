import java.sql.*;
import java.util.Scanner;

public class JdbcDatabaseController implements IDatabaseController{

    protected Connection connection;
    protected Statement statement;

    public static void main(String[] args) {
        instance = new JdbcDatabaseController();
    }

    protected static final String CREATE_CMD = "create";
    protected static final String READ_CMD = "read";
    protected static final String UPDATE_CMD = "update";
    protected static Scanner scanner;
    protected static JdbcDatabaseController instance;

    public JdbcDatabaseController() {
        initConnection();
        initScanner();
        listen();
    }


    public void initConnection() {
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

    protected void initScanner() {
        scanner = new Scanner(System.in);
    }

    protected void listen() {
        System.out.println("Type command create, read, update");
        if (scanner.hasNext()) {
            String next = scanner.nextLine();
            parseCommand(next);
        }
    }

    protected void parseCommand(String next) {
        next = next.toLowerCase();
        if (CREATE_CMD.equals(next)) {
            proceedCreateCmd();
        } else if (READ_CMD.equals(next)) {
            proceedReadCmd();
        } else if (UPDATE_CMD.equals(next)) {
            proceedUpdateCmd();
        } else {
            System.out.println("No such command.");
//            listen();
        }
    }

    protected void proceedCreateCmd() {
        System.out.println("Create new user:");
        System.out.print("Id: ");
        int id = scanner.nextInt();
        System.out.print("\nName: ");
        String name = scanner.next();
        System.out.print("\nAge: ");
        int age = scanner.nextInt();
        addPerson(id, name, age);
//        listen();
    }

    protected void proceedReadCmd() {
        System.out.println("Read user by:");
        System.out.println("1 - Id;\n2 - Name;\n3 - Age;");
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
        readPeople(readBy, value);
//        listen();
    }

    protected void proceedUpdateCmd() {
        System.out.println("Update not implemented yet");
//        listen();
    }



    public void addPerson(int id, String name, int age) {
        try {
            statement.execute("INSERT INTO Person values ('" + id + "', '" + name + "', '" + age + "')");
            printTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readPeople(String readBy, String value) {
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
    }

    public void updatePeople(String updateBy, String value) {

    }

    protected void printTable() {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery("SELECT * FROM Person");
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getString("id") +
                        ", name: " + resultSet.getString("name") +
                        ", age: " + resultSet.getString("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
