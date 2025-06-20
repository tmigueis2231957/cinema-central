package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {
    @Test
    public void testCriarFilme() {
        Filme f = new Filme("Diamantes", "Comédia", 135, "M12", "Ativo", "Sinopse 1");
        assertEquals("Diamantes", f.getNome());
        assertEquals("Comédia", f.getGenero());
        assertEquals(135, f.getDuracao());
        assertEquals("M12", f.getClassificacaoEtaria());
        assertEquals("Ativo", f.getEstado());
        assertEquals("Sinopse 1", f.getSinopse());
    }
} 