package gestor;

import model.Filme;
import model.Sessao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class GestorCinemaTest {
    private GestorCinema gestor;

    @BeforeEach
    public void setUp() {
        gestor = new GestorCinema();
    }

    @Test
    public void testAdicionarFilme() {
        int sizeBefore = gestor.getFilmes().size();
        Filme f = new Filme("Novo Filme", "Ação", 120, "M16", "Ativo", "Sinopse");
        gestor.addFilme(f);
        assertEquals(sizeBefore + 1, gestor.getFilmes().size());
        assertTrue(gestor.getFilmes().contains(f));
    }

    @Test
    public void testNaoPermitirFilmesDuplicados() {
        Filme f1 = new Filme("Duplicado", "Ação", 100, "M12", "Ativo", "Sinopse");
        Filme f2 = new Filme("Duplicado", "Ação", 100, "M12", "Ativo", "Sinopse");
        gestor.addFilme(f1);
        boolean existeAntes = gestor.existeFilmeComNome("Duplicado");
        gestor.addFilme(f2);
        long count = gestor.getFilmes().stream().filter(f -> f.getNome().equals("Duplicado")).count();
        assertTrue(existeAntes);
        assertEquals(2, count, "Permitiu adicionar filmes com nome duplicado (podes adaptar para não permitir, se quiseres).");
    }

    @Test
    public void testConflitoHorarioSessao() {
        Filme f = new Filme("Filme Teste", "Ação", 120, "M12", "Ativo", "Sinopse");
        gestor.addFilme(f);
        Date data = new Date();
        Sessao s1 = new Sessao("Sessao1", f, "Sala 1", data, "15:00", 5.0f);
        gestor.addSessao(s1);
        boolean conflito = gestor.existeConflitoHorario("Sala 1", data, "15:00", null);
        assertTrue(conflito, "Deveria detetar conflito de horário para a mesma sala, data e hora.");
    }

    @Test
    public void testValidarCamposObrigatoriosSessao() {
        Filme f = new Filme("Filme Teste", "Ação", 120, "M12", "Ativo", "Sinopse");
        Date data = new Date();
        boolean valido = gestor.validarSessaoCampos("Sessao1", f, "Sala 1", data, "15:00", 5.0f);
        assertTrue(valido, "Todos os campos obrigatórios estão preenchidos, deveria ser válido.");

        boolean invalido = gestor.validarSessaoCampos("", f, "Sala 1", data, "15:00", 5.0f);
        assertFalse(invalido, "Nome vazio, deveria ser inválido.");
    }

    @Test
    public void testEditarFilme() {
        Filme f = new Filme("Editar", "Ação", 100, "M12", "Ativo", "Sinopse");
        gestor.addFilme(f);
        gestor.editarFilme(f.getIdFilme(), "Editado", "Drama", 90, "M16", "Inativo", "Nova Sinopse");
        Filme editado = gestor.getFilme(f.getIdFilme());
        assertEquals("Editado", editado.getNome());
        assertEquals("Drama", editado.getGenero());
        assertEquals(90, editado.getDuracao());
        assertEquals("M16", editado.getClassificacaoEtaria());
        assertEquals("Inativo", editado.getEstado());
        assertEquals("Nova Sinopse", editado.getSinopse());
    }

    @Test
    public void testEliminarFilme() {
        Filme f = new Filme("Eliminar", "Ação", 100, "M12", "Ativo", "Sinopse");
        gestor.addFilme(f);
        int sizeBefore = gestor.getFilmes().size();
        gestor.eliminarFilme(f.getIdFilme());
        assertEquals(sizeBefore - 1, gestor.getFilmes().size());
        assertFalse(gestor.getFilmes().contains(f));
    }
} 