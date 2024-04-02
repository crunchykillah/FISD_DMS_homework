import config.BeanRegister;
import model.Basket;
import model.Client;
import model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanRegister.class);

        Client clientOne = (Client) context.getBean("client");
        System.out.println("username: " + clientOne.getUsername());

        Client clientTwo = (Client) context.getBean("client");
        System.out.println("username: " + clientTwo.getUsername());

        Basket basket = (Basket) context.getBean("basket");

        for (Product product: basket.getProducts()) {
            System.out.println("product: " + product.getName());
        }
    }
}
