package com.a11group.microfinanceapp.Model;

import java.io.Serializable;

/**
 * Created by Jonathan on 17/06/2017.
 */

public class PrevidenciaModel implements Serializable {

    /*POJO*/
    private static final long serialVersionUID = 1L;
    private int id;
    private String descricao;
    private String tipoReceita;
    private String data;
    private double valor;

    ///GET'S
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoReceita() {
        return tipoReceita;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    //SET'S
    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipoReceita(String tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
