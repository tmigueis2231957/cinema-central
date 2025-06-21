import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import gestor.GestorCinema;
import model.Sala;

public class JanelaSalas extends JFrame {

    private JPanel panelSalas;  // Painel principal ligado ao .form
    private JTable tableSalas;
    private JButton btnAdicionar;
    private DefaultTableModel modeloTabela;
    private GestorCinema gestor = GestorCinema.instance;

    public JanelaSalas() {
        setContentPane(panelSalas);
        setTitle("Gestão de Salas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Lugares", "Som", "Formato", "Acessibilidade", "Editar", "Apagar"}, 0
        );
        if (tableSalas != null) {
            tableSalas.setModel(modeloTabela);
        }

        atualizarTabela();

        if (btnAdicionar != null) {
            btnAdicionar.addActionListener(e -> adicionarSala());
        }

        if (tableSalas != null) {
            tableSalas.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int row = tableSalas.rowAtPoint(e.getPoint());
                    int col = tableSalas.columnAtPoint(e.getPoint());

                    if (col == 6) {
                        editarSala(row);
                    } else if (col == 7) {
                        apagarSala(row);
                    }
                }
            });
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Sala s : gestor.getSalas()) {
            modeloTabela.addRow(new Object[]{
                    s.getIdSala(),
                    s.getNome(),
                    s.getLugares(),
                    s.getSom(),
                    s.getFormato(),
                    s.getAcessibilidade(),
                    "EDITAR",
                    "APAGAR"
            });
        }
    }

    private void adicionarSala() {
        SalaDialog dialog = new SalaDialog(this, null);
        dialog.setVisible(true);

        if (dialog.isSucesso()) {
            gestor.addSala(dialog.getSala());
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Sala adicionada com sucesso!");
        }
    }

    private void editarSala(int row) {
        int id = (int) modeloTabela.getValueAt(row, 0);
        Sala salaOriginal = gestor.getSalaById(id);

        SalaDialog dialog = new SalaDialog(this, salaOriginal);
        dialog.setVisible(true);

        if (dialog.isSucesso()) {
            gestor.updateSala(id, dialog.getSala());
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Sala atualizada com sucesso!");
        }
    }

    private void apagarSala(int row) {
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar esta sala?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) modeloTabela.getValueAt(row, 0);
            gestor.deleteSala(id);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Sala removida com sucesso!");
        }
    }

    public JTable getTableSalas() {
        return tableSalas;
    }

    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

}

class SalaDialog extends JDialog {

    private JTextField txtNome, txtLugares, txtSom, txtFormato, txtAcessibilidade;
    private boolean sucesso = false;
    private Sala sala;

    public SalaDialog(JFrame parent, Sala salaExistente) {
        super(parent, "Dados da Sala", true);
        setLayout(new java.awt.GridLayout(6, 2));
        setSize(400, 250);
        setLocationRelativeTo(parent);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Lugares:"));
        txtLugares = new JTextField();
        add(txtLugares);

        add(new JLabel("Som:"));
        txtSom = new JTextField();
        add(txtSom);

        add(new JLabel("Formato:"));
        txtFormato = new JTextField();
        add(txtFormato);

        add(new JLabel("Acessibilidade (SIM/NÃO):"));
        txtAcessibilidade = new JTextField();
        add(txtAcessibilidade);

        JButton btnOk = new JButton("Submeter");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnOk);
        add(btnCancelar);

        if (salaExistente != null) {
            txtNome.setText(salaExistente.getNome());
            txtLugares.setText(String.valueOf(salaExistente.getLugares()));
            txtSom.setText(salaExistente.getSom());
            txtFormato.setText(salaExistente.getFormato());
            txtAcessibilidade.setText(salaExistente.getAcessibilidade());
        }

        btnOk.addActionListener(e -> {
            if (txtNome.getText().isEmpty() || txtLugares.getText().isEmpty() ||
                    txtSom.getText().isEmpty() || txtFormato.getText().isEmpty() ||
                    txtAcessibilidade.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            int lugares;
            try {
                lugares = Integer.parseInt(txtLugares.getText());
                if (lugares <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número de lugares inválido.");
                return;
            }

            sala = new Sala(
                    txtNome.getText(),
                    lugares,
                    txtSom.getText(),
                    txtFormato.getText(),
                    txtAcessibilidade.getText()
            );
            sucesso = true;
            setVisible(false);
        });

        btnCancelar.addActionListener(e -> setVisible(false));
    }

    public boolean isSucesso() { return sucesso; }
    public Sala getSala() { return sala; }


}
