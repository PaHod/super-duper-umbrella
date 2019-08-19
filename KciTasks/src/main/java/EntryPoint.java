import java.sql.*;

public class EntryPoint {

    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");

            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE Person(id INTEGER, name VARCHAR)");

            statement.execute("INSERT INTO Person values (0, 'Jora')");
            statement.execute("INSERT INTO Person values (1, 'Liusia')");

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Person");
            while(resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
