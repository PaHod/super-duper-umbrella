import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import people.Person;

@Controller
public class SpringConsoleReader extends AbstractConsoleReader {
    private static DataBaseController dataBaseController;

    public static void main(String[] args) {
        instance = new SpringConsoleReader();
    }

    protected void initConnection() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("console-reader.xml");
        dataBaseController = (DataBaseController) context.getBean("dataBaseController");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        dataBaseController.setJdbcTemplate(jdbcTemplate);

        dataBaseController.execute("CREATE TABLE test.person(id int, name varchar, age int)");
        dataBaseController.execute("INSERT INTO test.person (id, name, age) values (0, 'Jora', 29)");
        dataBaseController.execute("INSERT INTO test.person (id, name, age) values (1, 'Liusia', 23)");
        dataBaseController.execute("INSERT INTO test.person (id, name, age) values (2, 'Petia', 321)");
        dataBaseController.execute("INSERT INTO test.person (id, name, age) values (2, 'Tania', 123)");

        dataBaseController.readAll();
    }

    protected void addPerson(int id, String name, int age) {
        Person person = new Person(id, name, age);
        dataBaseController.addPerson(person);
    }

    protected void readPeople(String readBy, String value) {

    }

    protected void updatePeople(String updateBy, String value) {

    }

    protected void printTable() {

    }
}
