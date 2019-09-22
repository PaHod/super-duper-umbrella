import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringReadConsole {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("console-reader.xml");
        SpringDatabaseController databaseController = (SpringDatabaseController) context.getBean("dataBaseController");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        databaseController.setJdbcTemplate(jdbcTemplate);

        new ConsoleReader(databaseController).init();
    }
}
