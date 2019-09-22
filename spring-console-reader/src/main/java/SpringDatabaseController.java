import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import people.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SpringDatabaseController implements IDatabaseController {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void initConnection() {

        execute("CREATE TABLE test.person(id int, name varchar, age int)");
        execute("INSERT INTO test.person (id, name, age) values (0, 'Jora', 29)");
        execute("INSERT INTO test.person (id, name, age) values (1, 'Liusia', 23)");
        execute("INSERT INTO test.person (id, name, age) values (2, 'Petia', 321)");
        execute("INSERT INTO test.person (id, name, age) values (2, 'Tania', 123)");

        readAll();
    }

    public void addPerson(int id, String name, int age) {

    }

    public void readPeople(String readBy, String value) {

    }

    public void updatePeople(String updateBy, String value) {

    }


    public boolean addPerson(Person person) {
        int id = person.getId();
        String name = person.getName();
        int age = person.getAge();

        jdbcTemplate.update("INSERT INTO Person values ('" + id + "', '" + name + "', '" + age + "')");
        return false;
    }


    public boolean readPeople() {

        return false;
    }


    public boolean updatePeople() {

        return false;
    }


    public void execute(String query) {
        jdbcTemplate.execute(query);
    }


    public void readAll() {

        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM Person");

        for (Object value : stringObjectMap.values()) {
            System.out.println(value);
        }
    }

    private class PersonRowMapper implements RowMapper<Person> {
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            return person;
        }
    }
}
