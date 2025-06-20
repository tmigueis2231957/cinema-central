import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import gestor.GestorCinema;
import model.Sessao;
import model.Filme;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JanelaSessoes extends JFrame {
    private JPanel panelSessoes;
    private JTable tableSessoes;
    private JButton btnAdicionarSessao;
    private DefaultTableModel modeloTabela;
    private GestorCinema gestor = GestorCinema.instance;

    public JanelaSessoes() {
        setContentPane(panelSessoes);
        setTitle("Gestão de Sessões");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Sessão", "Filme", "Sala", "Data", "Hora", "Preço", "Editar", "Apagar"}, 0
        );
        tableSessoes.setModel(modeloTabela);
        atualizarTabela();

        btnAdicionarSessao.addActionListener(this::adicionarSessao);
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Sessao s : gestor.getSessoes()) {
            modeloTabela.addRow(new Object[]{
                s.getIdSessao(), s.getNome(), s.getFilme().getNome(), s.getSala(),
                sdf.format(s.getData()), s.getHora(), s.getPreco() + "€", "EDITAR", "APAGAR"
            });
        }
    }

    private void adicionarSessao(ActionEvent e) {
        SessaoDialog dialog = new SessaoDialog(this, null);
        dialog.setVisible(true);
        if (dialog.isSucesso()) {
            gestor.addSessao(dialog.getSessao());
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Sessão adicionada com sucesso.");
        }
    }
}

class SessaoDialog extends JDialog {
    private JTextField txtNome, txtSala, txtData, txtHora, txtPreco;
    private JComboBox<String> comboFilme;
    private boolean sucesso = false;
    private Sessao sessao;

    public SessaoDialog(JFrame parent, Sessao sessaoExistente) {
        super(parent, "Dados da Sessão", true);
        setLayout(new java.awt.GridLayout(7, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);
        add(new JLabel("Filme:"));
        comboFilme = new JComboBox<>();
        for (Filme f : GestorCinema.instance.getFilmes()) {
            comboFilme.addItem(f.getNome());
        }
        add(comboFilme);
        add(new JLabel("Sala:"));
        txtSala = new JTextField();
        add(txtSala);
        add(new JLabel("Data (dd/MM/yyyy):"));
        txtData = new JTextField();
        add(txtData);
        add(new JLabel("Hora (ex: 15:00):"));
        txtHora = new JTextField();
        add(txtHora);
        add(new JLabel("Preço (€):"));
        txtPreco = new JTextField();
        add(txtPreco);

        JButton btnOk = new JButton("Submeter");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnOk);
        add(btnCancelar);

        if (sessaoExistente != null) {
            txtNome.setText(sessaoExistente.getNome());
            comboFilme.setSelectedItem(sessaoExistente.getFilme().getNome());
            txtSala.setText(sessaoExistente.getSala());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtData.setText(sdf.format(sessaoExistente.getData()));
            txtHora.setText(sessaoExistente.getHora());
            txtPreco.setText(String.valueOf(sessaoExistente.getPreco()));
        }

        btnOk.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String filmeNome = (String) comboFilme.getSelectedItem();
                Filme filme = null;
                for (Filme f : GestorCinema.instance.getFilmes()) {
                    if (f.getNome().equals(filmeNome)) {
                        filme = f;
                        break;
                    }
                }
                String sala = txtSala.getText();
                String dataStr = txtData.getText();
                String hora = txtHora.getText();
                float preco = Float.parseFloat(txtPreco.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date data = sdf.parse(dataStr);

                if (!GestorCinema.instance.validarSessaoCampos(nome, filme, sala, data, hora, preco)) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
                    return;
                }
                if (GestorCinema.instance.existeConflitoHorario(sala, data, hora, null)) {
                    JOptionPane.showMessageDialog(this, "Já existe uma sessão para esta sala, data e hora.");
                    return;
                }
                sessao = new Sessao(nome, filme, sala, data, hora, preco);
                sucesso = true;
                setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados inseridos: " + ex.getMessage());
            }
        });
        btnCancelar.addActionListener(e -> setVisible(false));
    }

    public boolean isSucesso() { return sucesso; }
    public Sessao getSessao() { return sessao; }
}
