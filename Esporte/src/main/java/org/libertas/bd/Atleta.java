package org.libertas.bd;

public class Atleta {
    private int idAtleta;
    private String nome;
    private String modalidade;
    private int idade;
    private String clube;

    public int getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(int idAtleta) {
        this.idAtleta = idAtleta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }
}
