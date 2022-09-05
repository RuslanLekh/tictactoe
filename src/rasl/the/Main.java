package rasl.the;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("відкриття ... ");
        JFrame window = new JFrame("Хрестики-нулики"); //головне вікно
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //кнопка закрити
        window.setSize(400, 400);//розмір вікна
        window.setLayout(new BorderLayout());//менеджер компановки
        window.setLocationRelativeTo(null);//вікно по середині
        window.setVisible(true);//видимість вікна
        TicTacToe game = new TicTacToe();//додаємо обєкт нового класу
        window.add(game);//додаємо обєкт у вікно
    }
}
