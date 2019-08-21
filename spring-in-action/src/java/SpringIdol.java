import additionals.Entertainer;
import exceptions.PerformanceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import prformers.Performer;

import java.util.Map;

public class SpringIdol {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springIdol.xml");

        Entertainer entertainer = (Entertainer) context.getBean("entertainer");

        for (Map.Entry<String, Performer> entry : context.getBeansOfType(Performer.class).entrySet()) {

            Performer performer = entertainer.introduce(entry);

            try {
                performer.perform();
            } catch (PerformanceException e) {
                e.printStackTrace();
            }

        }
    }
}
