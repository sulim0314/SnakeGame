package SnakeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakeGame extends JPanel implements Runnable, KeyListener {

    private static final int SIZE = 20;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    private Thread thread;
    private boolean running = false;

    private Point head, food;
    private java.util.List<Point> snakeParts = new java.util.ArrayList<Point>();
    private int direction = 0; // 0: up, 1: right, 2: down, 3: left
    private Random random;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH * SIZE, HEIGHT * SIZE));
        setFocusable(true);
        addKeyListener(this);
        random = new Random(); // random 객체를 초기화
        startGame();
    }

    // 1. 게임 시작과 뱀, 먹이 초기화
    public void startGame() {
        running = true;
        direction = 0;
        snakeParts.clear();
        head = new Point(WIDTH / 2, HEIGHT / 2);
        snakeParts.add(head);
        food = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (running) {
            tick();
            repaint();

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 2. 뱀의 이동과 먹이 먹기, 게임 오버 체크
    public void tick() {
        Point newHead = new Point(head.x, head.y);
        switch (direction) {
            case 0: newHead.y--; break;
            case 1: newHead.x++; break;
            case 2: newHead.y++; break;
            case 3: newHead.x--; break;
        }
        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= WIDTH || newHead.y >= HEIGHT || snakeParts.contains(newHead)) {
            running = false;
            JOptionPane.showMessageDialog(this, "게임 오버입니다.");
            System.exit(0);  // 게임 오버 후 프로그램을 종료
            return;
        }
        snakeParts.add(0, newHead);
        head = newHead;
        if (head.equals(food)) {
            do {
                food = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
            } while (snakeParts.contains(food));
        } else {
            snakeParts.remove(snakeParts.size() - 1);
        }
    }

    // 3. 게임 요소 그리기
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        
        // 격자
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= WIDTH; i++) {
            g.drawLine(i * SIZE, 0, i * SIZE, HEIGHT * SIZE);
        }
        for (int i = 0; i <= HEIGHT; i++) {
            g.drawLine(0, i * SIZE, WIDTH * SIZE, i * SIZE);
        }
        
        // 먹이
        g.setColor(Color.RED);
        g.fillRect(food.x * SIZE, food.y * SIZE, SIZE, SIZE);
        
        // 뱀
        g.setColor(Color.GREEN);
        for (Point part : snakeParts) {
            g.fillRect(part.x * SIZE, part.y * SIZE, SIZE, SIZE);
        }
    }

    // 4. 방향키 입력 처리
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != 2) direction = 0;
        if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != 3) direction = 1;
        if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != 0) direction = 2;
        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != 1) direction = 3;
    }


    // 이 메소드들이 없으면 KeyListener 인터페이스를 제대로 구현하지 않은 것으로 간주되어 
    // 컴파일 에러가 발생
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new SnakeGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
