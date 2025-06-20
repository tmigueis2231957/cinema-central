package gestor;

import model.Filme;
import model.Sessao;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class GestorCinema {
    public static final GestorCinema instance = new GestorCinema();

    private LinkedList<Filme> filmes;
    private LinkedList<Sessao> sessoes;

    private GestorCinema() {
        filmes = new LinkedList<>();
        sessoes = new LinkedList<>();
        seedApp();
    }

    // Seed de dados de exemplo
    private void seedApp() {
        Filme f1 = new Filme("Diamantes", "Comédia", 135, "M12", "Ativo", "Sinopse 1");
        Filme f2 = new Filme("Paddington", "Animação", 94, "M6", "Ativo", "Sinopse 2");
        Filme f3 = new Filme("O Amador", "Thriller", 124, "M14", "Ativo", "Sinopse 3");
        Filme f4 = new Filme("The Accountant", "Thriller", 120, "M8", "Ativo", "Sinopse 4");
        Filme f5 = new Filme("The Avengers", "Sci-Fi", 190, "M12", "Ativo", "Sinopse 5");
        filmes.add(f1); filmes.add(f2); filmes.add(f3); filmes.add(f4); filmes.add(f5);

        sessoes.add(new Sessao("Sessão 1", f1, "Sala 1", new Date(), "15:00", 5.0f));
        sessoes.add(new Sessao("Sessão 2", f2, "Sala 2", new Date(), "17:00", 6.0f));
    }

    // Filmes
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

    // Sessões
    public LinkedList<Sessao> getSessoes() { return sessoes; }
    public Sessao getSessao(int id) {
        for (Sessao s : sessoes) {
            if (s.getIdSessao() == id) return s;
        }
        return null;
    }
    public void addSessao(Sessao s) { sessoes.add(s); }
    public void editarSessao(int id, String nome, Filme filme, String sala, Date data, String hora, float preco) {
        Sessao s = getSessao(id);
        if (s != null) {
            s.setNome(nome);
            s.setFilme(filme);
            s.setSala(sala);
            s.setData(data);
            s.setHora(hora);
            s.setPreco(preco);
        }
    }
    public void eliminarSessao(int id) {
        Sessao s = getSessao(id);
        if (s != null) sessoes.remove(s);
    }
    // Validação: conflito de horário/sala
    public boolean existeConflitoHorario(String sala, Date data, String hora, Integer ignorarId) {
        for (Sessao s : sessoes) {
            if (s.getSala().equalsIgnoreCase(sala) && s.getData().equals(data) && s.getHora().equals(hora)) {
                if (ignorarId == null || s.getIdSessao() != ignorarId) {
                    return true;
                }
            }
        }
        return false;
    }
    // Validação: campos obrigatórios sessão
    public boolean validarSessaoCampos(String nome, Filme filme, String sala, Date data, String hora, Float preco) {
        return nome != null && !nome.isEmpty() && filme != null && sala != null && !sala.isEmpty() && data != null && hora != null && !hora.isEmpty() && preco != null && preco > 0;
    }
    // Validação: campos obrigatórios filme
    public boolean validarFilmeCampos(String nome, String genero, Integer duracao, String classificacaoEtaria, String estado, String sinopse) {
        return nome != null && !nome.isEmpty() && genero != null && !genero.isEmpty() && duracao != null && duracao > 0 && classificacaoEtaria != null && !classificacaoEtaria.isEmpty() && estado != null && !estado.isEmpty() && sinopse != null && !sinopse.isEmpty();
    }
} 