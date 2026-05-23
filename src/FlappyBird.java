import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int LarguraBorda = 360;
    int AlturaBorda = 640;

    // IMAGEM

    Image birdImage;
    Image backgroundImage;
    Image bottomPipeImage;
    Image topPipeImage;

    // PASSARO
    int birdX = LarguraBorda / 8;
    int birdY = AlturaBorda / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image image) {
            this.img = image;
        }
    }

    // CANOS
    int Pipex = LarguraBorda;
    int pipeY = 0;
    int PipeWidth = 64;
    int PipeHeight = 512;

    class Pipe {
        int x = Pipex;
        int y = pipeY;
        int width = PipeWidth;
        int height = PipeHeight;
        Image img;
        boolean Passed = false;

        Pipe(Image img) {
            this.img = img;
        }
    }

    // LOGICA DO JOGO
    Bird bird;
    int velocityx = -4;
    int velocityy = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;

    boolean gameOver = false;

    double counter = 0;

    FlappyBird() {
        setPreferredSize(new Dimension(LarguraBorda, AlturaBorda));

        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();

        bird = new Bird(birdImage);
        pipes = new ArrayList<Pipe>();
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();
        gameLoop = new Timer(1000 / 60, (ActionListener) this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void placePipes() {
        int randomPypeY = (int) (pipeY - PipeHeight / 4 - Math.random() * (PipeHeight / 2));
        int openingSpace = AlturaBorda / 4;
        Pipe topPipe = new Pipe(topPipeImage);
        topPipe.y = randomPypeY;
        pipes.add(topPipe);
        Pipe bottomPipe = new Pipe(bottomPipeImage);
        bottomPipe.y = topPipe.y + PipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, LarguraBorda, AlturaBorda, null);

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("" + (int) counter, 10, 30);

    }

    public void move() {
        velocityy += gravity;
        bird.y += velocityy;
        bird.y = Math.max(0, bird.y);

        for (int i = 0; i < pipes.size(); i++) {

            Pipe pipe = pipes.get(i);
            pipe.x += velocityx;

            if (!pipe.Passed && bird.x > pipe.x + pipe.width) {
                pipe.Passed = true;
                counter += 0.5;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }

            if (pipe.x + pipe.width < 0) {
                pipes.remove(pipe);
                i--;
            }
        }

        if (bird.y > AlturaBorda) {
            gameOver = true;
        }

    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width && a.x + a.width > b.x && a.y < b.y + b.height && a.y + a.height > b.y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityy = -6;
        }

        if (gameOver) {
            bird.x = birdX;
            bird.y = birdY;
            pipes.clear();
            counter = 0;
            gameOver = false;
            placePipesTimer.start();
            gameLoop.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");

        FlappyBird gamePanel = new FlappyBird();

        frame.add(gamePanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }

}
