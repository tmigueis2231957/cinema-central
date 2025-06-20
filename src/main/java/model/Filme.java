package model;

public class Filme {
    private static int filmeIdCounter = 0;
    private int idFilme;
    private String nome;
    private String genero;
    private int duracao;
    private String classificacaoEtaria;
    private String estado;
    private String sinopse;

    public Filme(String nome, String genero, int duracao, String classificacaoEtaria, String estado, String sinopse) {
        filmeIdCounter++;
        this.idFilme = filmeIdCounter;
        this.nome = nome;
        this.genero = genero;
        this.duracao = duracao;
        this.classificacaoEtaria = classificacaoEtaria;
        this.estado = estado;
        this.sinopse = sinopse;
    }

    public int getIdFilme() { return idFilme; }
    public String getNome() { return nome; }
    public String getGenero() { return genero; }
    public int getDuracao() { return duracao; }
    public String getClassificacaoEtaria() { return classificacaoEtaria; }
    public String getEstado() { return estado; }
    public String getSinopse() { return sinopse; }

    public void setNome(String nome) { this.nome = nome; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
    public void setClassificacaoEtaria(String classificacaoEtaria) { this.classificacaoEtaria = classificacaoEtaria; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
} 