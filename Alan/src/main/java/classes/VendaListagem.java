/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import util.Formatacoes;


/**
 *
 * @author Arthur
 */
public class VendaListagem {

    private int idVenda;
    private int idFilial;
    private String dataVenda;
    private int codigoProduto;
    private String nomeProduto;
    private int idUsuario;
    private String nomeUsuario;
    private double quantidade;
    private double vrUnitario;
    private double valor;

    public VendaListagem(int idVenda, int idFilial, String dataVenda, int codigoProduto, String nomeProduto, int idUsuario, String nomeUsuario, double quantidade, double vrUnitario, double valor) {
        this.idVenda = idVenda;
        this.idFilial = idFilial;
        this.dataVenda = dataVenda;
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.quantidade = quantidade;
        this.vrUnitario = vrUnitario;
        this.valor = valor;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getVrUnitario() {
        return vrUnitario;
    }
    

    public double getValor() {
        return valor;
    }

    public int getIdFilial() {
        return idFilial;
    }
    
    public String getValorUnitario() {
        return Formatacoes.formatarMoeda(this.vrUnitario);
    }
    
    public String getValorStr() {
        return Formatacoes.formatarMoeda(this.valor);
    }
}
