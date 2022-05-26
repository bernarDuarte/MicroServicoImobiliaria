package br.com.imobilizaria.tipoImovel.tipoImovelservice.model;

import java.util.Date;


public class Imovel {    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_craicao() {
        return data_criacao;
    }

    public void setData_craicao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public int getIdTipoImovel() {
        return idTipoImovel;
    }

    public void setIdTipoImovel(int idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    private Long id;
    private Date data_criacao;
    private int idTipoImovel;
    private String titulo;
    private String descricao;
    private int status;
    private double valor;
}
