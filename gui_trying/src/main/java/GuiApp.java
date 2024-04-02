import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GuiApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример кнопки с изображением");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        try {
            File file = new File("C:\\Users\\Артемий\\IdeaProjects\\homework_second_course\\gui_trying\\src\\main\\resources\\mobb.png");
            // Загрузка изображения для кнопки
            ImageIcon icon = new ImageIcon(ImageIO.read(file));

            // Создание кнопки с изображением
            JButton imageButton = new JButton(icon);

            // Добавление слушателя событий к кнопке
            imageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Кнопка с изображением была нажата");
                }
            });

            // Добавление кнопки на панель
            panel.add(imageButton);
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}
