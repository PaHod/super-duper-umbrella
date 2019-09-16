import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean addPeple() {

        jdbcTemplate.query();
        return false;
    }


    public boolean readPeople() {

        return false;
    }


    public boolean updatePeople() {
        return false;
    }


}
