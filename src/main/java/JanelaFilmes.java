import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import gestor.GestorCinema;
import model.Filme;
import java.awt.event.ActionEvent;



public class JanelaFilmes extends JFrame {
    private JPanel panelFilmes;
    private JButton TESTEButton;
    private JTable tableFilmes;
    private JButton btnAdicionar;
    private DefaultTableModel modeloTabela;
    private GestorCinema gestor = GestorCinema.instance;

    public JanelaFilmes() {
        setContentPane(panelFilmes);
        setTitle("Gestão de Filmes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Filme", "Género", "Duração", "Classificação", "Estado", "Editar", "Apagar"}, 0
        );
        tableFilmes.setModel(modeloTabela);
        atualizarTabela();

        btnAdicionar.addActionListener(this::adicionarFilme);
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Filme f : gestor.getFilmes()) {
            modeloTabela.addRow(new Object[]{
                f.getIdFilme(), f.getNome(), f.getGenero(), f.getDuracao() + "m", f.getClassificacaoEtaria(), f.getEstado(), "EDITAR", "APAGAR"
            });
        }
    }

    private void adicionarFilme(ActionEvent e) {
        FilmeDialog dialog = new FilmeDialog(this, null);
        dialog.setVisible(true);
        if (dialog.isSucesso()) {
            gestor.addFilme(dialog.getFilme());
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Filme adicionado com sucesso.");
        }
    }
}

class FilmeDialog extends JDialog {
    private JTextField txtNome, txtGenero, txtDuracao, txtClassificacao, txtEstado, txtSinopse;
    private boolean sucesso = false;
    private Filme filme;

    public FilmeDialog(JFrame parent, Filme filmeExistente) {
        super(parent, "Dados do Filme", true);
        setLayout(new java.awt.GridLayout(7, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);
        add(new JLabel("Género:"));
        txtGenero = new JTextField();
        add(txtGenero);
        add(new JLabel("Duração (min):"));
        txtDuracao = new JTextField();
        add(txtDuracao);
        add(new JLabel("Classificação Etária:"));
        txtClassificacao = new JTextField();
        add(txtClassificacao);
        add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        add(txtEstado);
        add(new JLabel("Sinopse:"));
        txtSinopse = new JTextField();
        add(txtSinopse);

        JButton btnOk = new JButton("Submeter");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnOk);
        add(btnCancelar);

        if (filmeExistente != null) {
            txtNome.setText(filmeExistente.getNome());
            txtGenero.setText(filmeExistente.getGenero());
            txtDuracao.setText(String.valueOf(filmeExistente.getDuracao()));
            txtClassificacao.setText(filmeExistente.getClassificacaoEtaria());
            txtEstado.setText(filmeExistente.getEstado());
            txtSinopse.setText(filmeExistente.getSinopse());
        }

        btnOk.addActionListener(e -> {
            if (txtNome.getText().isEmpty() || txtGenero.getText().isEmpty() || txtDuracao.getText().isEmpty() || txtClassificacao.getText().isEmpty() || txtEstado.getText().isEmpty() || txtSinopse.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }
            int duracao;
            try {
                duracao = Integer.parseInt(txtDuracao.getText());
                if (duracao <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Duração inválida.");
                return;
            }
            filme = new Filme(txtNome.getText(), txtGenero.getText(), duracao, txtClassificacao.getText(), txtEstado.getText(), txtSinopse.getText());
            sucesso = true;
            setVisible(false);
        });
        btnCancelar.addActionListener(e -> setVisible(false));
    }

    public boolean isSucesso() { return sucesso; }
    public Filme getFilme() { return filme; }

}
