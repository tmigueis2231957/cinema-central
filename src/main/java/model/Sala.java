package model;

public class Sala {
    private static int salaIdCounter = 0;

    private int idSala;
    private String nome;
    private int lugares;
    private String som;
    private String formato;
    private String acessibilidade;

    public Sala(String nome, int lugares, String som, String formato, String acessibilidade) {
        salaIdCounter++;
        this.idSala = salaIdCounter;
        this.nome = nome;
        this.lugares = lugares;
        this.som = som;
        this.formato = formato;
        this.acessibilidade = acessibilidade;
    }

    // Getters
    public int getIdSala() { return idSala; }
    public String getNome() { return nome; }
    public int getLugares() { return lugares; }
    public String getSom() { return som; }
    public String getFormato() { return formato; }
    public String getAcessibilidade() { return acessibilidade; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setLugares(int lugares) { this.lugares = lugares; }
    public void setSom(String som) { this.som = som; }
    public void setFormato(String formato) { this.formato = formato; }
    public void setAcessibilidade(String acessibilidade) { this.acessibilidade = acessibilidade; }
}
