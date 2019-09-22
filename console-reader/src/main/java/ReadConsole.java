public class ReadConsole {
    public static void main(String[] args) {
        new ConsoleReader(new JdbcDatabaseController()).start();
    }
}
