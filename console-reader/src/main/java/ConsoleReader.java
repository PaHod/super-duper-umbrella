import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class ConsoleReader {

    protected static final String CREATE_CMD = "create";
    protected static final String READ_CMD = "read";
    protected static final String UPDATE_CMD = "update";
    protected static Scanner scanner;

    public ConsoleReader() {
        initConnection();
        initScanner();
        listen();
    }


    protected abstract void initConnection();

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
            listen();
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
        addPeople(id, name, age);
        listen();
    }

    protected void addPeople(int id, String name, int age) {
        try {
            statement.execute("INSERT INTO Person values ('" + id + "', '" + name + "', '" + age + "')");
            printTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void proceedReadCmd() {
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
        readPeople(readBy, value);
        listen();
    }

    protected abstract void readPeople(String readBy, String value);

    protected void proceedUpdateCmd() {

    }


    protected void printTable() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Person");
        while (resultSet.next()) {
            System.out.println("id: " + resultSet.getString("id") +
                    ", name: " + resultSet.getString("name") +
                    ", age: " + resultSet.getString("age"));
        }
    }
}
