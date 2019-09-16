import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpingCosoleReader extends ConsoleReader {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("console-reader.xml");



    }
}
