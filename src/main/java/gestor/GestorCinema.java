package gestor;

import model.Filme;
import java.util.LinkedList;

public class GestorCinema {
    private static GestorCinema instance = null;
    private LinkedList<Filme> filmes;

    private GestorCinema() {
        filmes = new LinkedList<>();
    }

    public static GestorCinema getInstance() {
        if (instance == null) {
            instance = new GestorCinema();
        }
        return instance;
    }

    public LinkedList<Filme> getFilmes() { return filmes; }
    public Filme getFilme(int id) {
        for (Filme f : filmes) {
            if (f.getIdFilme() == id) return f;
        }
        return null;
    }
    public void addFilme(Filme f) { filmes.add(f); }
    public void editarFilme(int id, String nome, String genero, int duracao, String classificacaoEtaria, String estado, String sinopse) {
        Filme f = getFilme(id);
        if (f != null) {
            f.setNome(nome);
            f.setGenero(genero);
            f.setDuracao(duracao);
            f.setClassificacaoEtaria(classificacaoEtaria);
            f.setEstado(estado);
            f.setSinopse(sinopse);
        }
    }
    public void eliminarFilme(int id) {
        Filme f = getFilme(id);
        if (f != null) filmes.remove(f);
    }
    public boolean existeFilmeComNome(String nome) {
        for (Filme f : filmes) {
            if (f.getNome().equalsIgnoreCase(nome)) return true;
        }
        return false;
    }
} 