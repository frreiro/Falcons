package com.example.falcons.manutencao;

public class Manutencao {
    private String setor;
    private String titulo;
    private String description;
    private String periodicidade;
    private String data;
    public Manutencao(String setor) {
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
