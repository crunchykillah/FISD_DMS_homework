import config.Config;
import model.Art;
import model.Author;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        Author authorOne = (Author) context.getBean("author");
        Author authorTwo = (Author) context.getBean("author");

        if (authorOne.equals(authorTwo)) {
            System.out.println("Это был синглтон");
        } else {
            System.out.println("Это был prototype");
        }

        Art artOne = (Art) context.getBean("art");
        Art artTwo = (Art) context.getBean("art");

        if (artOne.equals(artTwo)) {
            System.out.println("Это был синглтон");
        } else {
            System.out.println("Это был prototype");
        }
    }
}
