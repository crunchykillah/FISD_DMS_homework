package ru.itis;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.Config;
import ru.itis.model.Order;
import ru.itis.model.OrderItem;
import ru.itis.model.Product;
import ru.itis.rep.OrderItemRepository;
import ru.itis.rep.OrderRepository;
import ru.itis.rep.ProductRepository;
import ru.itis.rep.UserRepository;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        OrderItemRepository orderItemRepository = context.getBean(OrderItemRepository.class);
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);


        // Создаем продукт
        Product product = new Product();
        product.setName("Product 1");
        product.setWeight(100);
        productRepository.save(product);

        // Создаем заказ
        Order order = new Order();
        order.setUsername("user1");
        order.setPassword("password");
        order.setOrderDate(new Date());
        orderRepository.save(order);

        // Создаем элемент заказа
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setProduct(product); // Привязываем элемент заказа к продукту
        orderItem.setOrder(order); // Привязываем элемент заказа к заказу
        orderItemRepository.save(orderItem);

        // Обновляем заказ, чтобы добавить элемент заказа
        order.getOrderItems().add(orderItem);
        orderRepository.save(order);

        // Выводим ID заказа
        System.out.println("Order ID: " + order.getId());
    }
}
