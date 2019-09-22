import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIt {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("console-reader.xml");
        ConsoleReader consoleReader = (ConsoleReader) context.getBean("consoleReader");

        consoleReader.start();
    }
}
