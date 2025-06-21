import javax.swing.*;

public class AdminDashboard extends JFrame {

    private JPanel contentPane; // este é o painel raiz do .form
    private JButton vendasButton;
    private JButton sessoesButton;
    private JButton salasButton;
    private JButton filmesButton;
    private JButton barButton;
    private JButton consultasButton;

    public AdminDashboard() {
        setContentPane(contentPane); // LIGA o layout gerado ao JFrame
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // centra

        vendasButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Clicaste no botão!");
        });

        sessoesButton.addActionListener(e -> {
            JanelaFilmes janelaFilmes = new JanelaFilmes();
            janelaFilmes.setVisible(true);
            dispose();
        });

        salasButton.addActionListener(e -> {
            JanelaSalas janelaSalas = new JanelaSalas();
            janelaSalas.setVisible(true);
            dispose();
        });

        filmesButton.addActionListener(e -> {
            JanelaFilmes janelaFilmes = new JanelaFilmes();
            janelaFilmes.setVisible(true);
            dispose();
        });

        barButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Clicaste no botão!");
        });

        consultasButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Clicaste no botão!");
        });
    }

    // ✅ Getters para usar nos testes
    public JButton getVendasButton() {
        return vendasButton;
    }

    public JButton getSessoesButton() {
        return sessoesButton;
    }

    public JButton getSalasButton() {
        return salasButton;
    }

    public JButton getFilmesButton() {
        return filmesButton;
    }

    public JButton getBarButton() {
        return barButton;
    }

    public JButton getConsultasButton() {
        return consultasButton;
    }
}
