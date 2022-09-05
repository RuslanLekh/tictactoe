package rasl.the;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //пусте поле
    public static final int FIELD_X = 10; //поле з хрестиком
    public static final int FIELD_O = 200; //поле з нуликом
    int[][] field;//масив ігрового поля
    boolean isXturn;

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //очищуємо
        graphics.clearRect(0, 0, getWidth(), getHeight());
        //сітка з ліній
        drawGrid(graphics);
        //мадюєм хрестики нолики
        drawXO(graphics);
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        // переводим координати в індекси ячейка в масиві field
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) { // провіряєм чи нажата ліва клавіша мишки
            int x = mouseEvent.getX(); // координата х кліка
            int y = mouseEvent.getY();// координата у кліка
            //перевірка чи вибрана ячейка пуста і туди можна ходити
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            if (field[i][j] == FIELD_EMPTY) {
                // провіряємочи чий хід, якщо Х - ставим хрестик, якщо 0 - нолик
                field[i][j] = isXturn ? FIELD_X : FIELD_O;
                isXturn = !isXturn; // міняємо хід
                repaint(); //
                int res = checkState();
                if (res != 0) {
                    if (res == FIELD_O * 3) {
                        //переміг нулик
                        JOptionPane.showMessageDialog(this, " нулик виграв !", "Перемога!", JOptionPane.INFORMATION_MESSAGE);
                    } else if (res == FIELD_X * 3) {
                        //переміг Х
                        JOptionPane.showMessageDialog(this, " Х виграв !", "Перемога!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        //нічия
                        JOptionPane.showMessageDialog(this, " нічия !", "Нічия!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //перезапускаємо гру
                    initGame();
                    //перемальовуємо поле
                    repaint();
                }
            }
        }

    }

    void drawGrid(Graphics graphics) {
        int w = getWidth();//ширина ігрового поля
        int h = getHeight();//висота ігрового поля
        int dw = w / 3; //ділим ширину на 3 - отримуєм ширину одної комі рки
        int dh = h / 3; //ділим висоту на 3 та отримуєм довжину одної комірки
        graphics.setColor(Color.BLUE);
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        //лінiя із правого верхнього кута в лівий нижній
        graphics.drawLine(x, y, x + dw, y + dh);
        //лінія від лівого нижнього до правого верхнього
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void drawO(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x + 5 * dw / 100, y, dw * 9 / 10, dh);
    }

    void drawXO(Graphics graphics) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                //якщо в ячейці хрестик то малюємо його
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                    //для нулика
                } else if (field[i][j] == FIELD_O) {
                    drawO(i, j, graphics);
                }
            }
        }
    }

    public TicTacToe() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3];
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD_EMPTY;
            }
        }
        isXturn = true;
    }

    int checkState() {
        //провіряємо діагоналі
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag += field[i][i];
            diag2 += field[i][2 - i];
        }
        if (diag == FIELD_O * 3 || diag == FIELD_X * 3) {return diag;}
        if (diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3) {return diag2;}
        int check_i, check_j;
        boolean hasEmpty = false;
        //
        for (int i = 0; i < 3; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            if (check_i == FIELD_O * 3 || check_i == FIELD_X * 3) {
                return check_i;
            }
            if (check_j == FIELD_O * 3 || check_j == FIELD_X * 3) {
                return check_j;
            }
        }
        if (hasEmpty) return 0; else return -1;
    }
}





