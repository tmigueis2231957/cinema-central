import org.junit.jupiter.api.Test;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDashboardTest {

    @Test
    public void testAdminDashboardCriaSemErro() {
        AdminDashboard dashboard = new AdminDashboard();
        assertNotNull(dashboard);
    }

    @Test
    public void testTituloDashboard() {
        AdminDashboard dashboard = new AdminDashboard();
        assertEquals("Admin Dashboard", dashboard.getTitle());
    }

    @Test
    public void testTodosBotoesExistem() {
        AdminDashboard dashboard = new AdminDashboard();
        assertNotNull(dashboard.getVendasButton());
        assertNotNull(dashboard.getSessoesButton());
        assertNotNull(dashboard.getSalasButton());
        assertNotNull(dashboard.getFilmesButton());
        assertNotNull(dashboard.getBarButton());
        assertNotNull(dashboard.getConsultasButton());
    }

    @Test
    public void testFilmesButtonTemActionListener() {
        AdminDashboard dashboard = new AdminDashboard();
        JButton filmesButton = dashboard.getFilmesButton();
        ActionListener[] listeners = filmesButton.getActionListeners();
        assertTrue(listeners.length > 0, "Filmes button deve ter ActionListener");
    }

    @Test
    public void testVendasButtonTemActionListener() {
        AdminDashboard dashboard = new AdminDashboard();
        JButton vendasButton = dashboard.getVendasButton();
        ActionListener[] listeners = vendasButton.getActionListeners();
        assertTrue(listeners.length > 0, "Vendas button deve ter ActionListener");
    }
}
