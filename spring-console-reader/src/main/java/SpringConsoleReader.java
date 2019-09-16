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
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
//        SingleConnectionDataSource ds = new SingleConnectionDataSource();
//        ds.setDriverClassName("org.hsqldb.jdbcDriver");
//        ds.setUrl("jdbc:hsqldb:data/tutorial");
//        ds.setUsername("sa");
//        ds.setPassword("");
//
//        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.execute("create table employee (id int, name varchar)");
        jt.execute("insert into employee (id, name) values (1, 'A')");
        jt.execute("insert into employee (id, name) values (2, 'B')");
        jt.execute("insert into employee (id, name) values (3, 'C')");
        jt.execute("insert into employee (id, name) values (4, 'D')");
        jt.execute("insert into employee (id, name) values (5, 'E')");
        jt.execute("insert into employee (id, name) values (6, 'F')");


        SqlRowSet count = jt.queryForRowSet("select count(*) from employee");

        System.out.println(count);
//        ds.destroy();


//        dataBaseController.setJdbcTemplate(jdbcTemplate);
//
//        dataBaseController.execute("CREATE TABLE Person(id int, name varchar, age int)");
//
////        dataBaseController.execute("CREATE TABLE 'Person'(" +
////                "'id' bigint(20) generated by default as identity NOT NULL, " +
////                "'name' varchar(100) NOT NULL, " +
////                "'age' bigint(100) NOT NULL)");
//        dataBaseController.execute("INSERT INTO Person (id, name, age) values (0, 'Jora', 29)");
//        dataBaseController.execute("INSERT INTO Person (id, name, age) values (1, 'Liusia', 23)");
//        dataBaseController.execute("INSERT INTO Person (id, name, age) values (2, 'Petia', 321)");
//        dataBaseController.execute("INSERT INTO Person (id, name, age) values (2, 'Tania', 123)");
//
//        dataBaseController.readAll();
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
