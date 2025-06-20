package model;

import java.util.Date;

public class Sessao {
    private static int sessaoIdCounter = 0;
    private int idSessao;
    private String nome;
    private Filme filme;
    private String sala;
    private Date data;
    private String hora;
    private float preco;

    public Sessao(String nome, Filme filme, String sala, Date data, String hora, float preco) {
        sessaoIdCounter++;
        this.idSessao = sessaoIdCounter;
        this.nome = nome;
        this.filme = filme;
        this.sala = sala;
        this.data = data;
        this.hora = hora;
        this.preco = preco;
    }

    public int getIdSessao() { return idSessao; }
    public String getNome() { return nome; }
    public Filme getFilme() { return filme; }
    public String getSala() { return sala; }
    public Date getData() { return data; }
    public String getHora() { return hora; }
    public float getPreco() { return preco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public void setSala(String sala) { this.sala = sala; }
    public void setData(Date data) { this.data = data; }
    public void setHora(String hora) { this.hora = hora; }
    public void setPreco(float preco) { this.preco = preco; }
} 