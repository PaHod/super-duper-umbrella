import exceptions.PerformanceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import prformers.Performer;

import java.util.Map;

public class SpringIdol {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springIdol.xml");


        for (Map.Entry<String, Performer> entry : context.getBeansOfType(Performer.class).entrySet()) {
            System.out.println("\n --------- id: " + entry.getKey() + " ---------");
            try {
                entry.getValue().perform();
            } catch (PerformanceException e) {
                e.printStackTrace();
            }

        }
    }
}
