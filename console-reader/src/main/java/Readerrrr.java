import java.sql.*;

public class Readerrrr extends AbstractConsoleReader {

    protected Connection connection;
    protected Statement statement;

    public static void main(String[] args) {
        instance = new Readerrrr();
    }

    @Override
    protected void initConnection() {
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

    @Override
    protected void addPerson(int id, String name, int age) {
        try {
            statement.execute("INSERT INTO Person values ('" + id + "', '" + name + "', '" + age + "')");
            printTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void readPeople(String readBy, String value) {
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

    protected void updatePeople(String updateBy, String value) {

    }

    @Override
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
