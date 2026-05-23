import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int LarguraBorda = 360;
        int AlturaBorda = 640;

        JFrame janela = new JFrame("Flappy Bird");
        janela.setSize(LarguraBorda, AlturaBorda);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true); // Mover para o final

        FlappyBird flappyBird = new FlappyBird();
        janela.add(flappyBird);
        janela.pack(); // Ajusta o tamanho da janela para se adequar ao conteúdo
        janela.setVisible(true); // Certifique-se de que a janela seja visível após adicionar o painel
    }
}
