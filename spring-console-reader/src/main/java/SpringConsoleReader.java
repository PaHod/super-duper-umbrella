import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class SpringConsoleReader extends ConsoleReader {
    private static DataBaseController dataBaseController;

    public static void main(String[] args) {
        initConnection1();
        initScanner();
        listen();
    }

    protected static void initConnection1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("console-reader.xml");

        dataBaseController = (DataBaseController) context.getBean("dataBaseController");
    }
}
