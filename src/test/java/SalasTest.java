import org.junit.jupiter.api.Test;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.TableModel;

import static org.junit.jupiter.api.Assertions.*;

public class SalasTest {

    @Test
    public void testJanelaSalasCriaSemErro() {
        JanelaSalas janelaSalas = new JanelaSalas();
        assertNotNull(janelaSalas);
    }

    @Test
    public void testTituloJanelaSalas() {
        JanelaSalas janelaSalas = new JanelaSalas();
        assertEquals("Gestão de Salas", janelaSalas.getTitle());
    }

    @Test
    public void testTabelaSalasExiste() {
        JanelaSalas janelaSalas = new JanelaSalas();
        JTable tabela = janelaSalas.getTableSalas();
        assertNotNull(tabela);
    }

    @Test
    public void testTabelaTemColunasCertas() {
        JanelaSalas janelaSalas = new JanelaSalas();
        JTable tabela = janelaSalas.getTableSalas();
        TableModel modelo = tabela.getModel();
        assertEquals(8, modelo.getColumnCount());
        assertEquals("ID", modelo.getColumnName(0));
        assertEquals("Nome", modelo.getColumnName(1));
        assertEquals("Lugares", modelo.getColumnName(2));
    }

    @Test
    public void testBotaoAdicionarExiste() {
        JanelaSalas janelaSalas = new JanelaSalas();
        JButton botao = janelaSalas.getBtnAdicionar();
        assertNotNull(botao);
    }
}
