package br.com.fabricadeprogramador.apptuc;

import java.util.Locale;

/**
 * Created by rafae on 25/10/2016.
 */

public class ItemCesta {

    private Produto produto;
    private int quantidade;

    public ItemCesta(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemCesta() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return getQuantidade() + "   " + getProduto().getDescricao() +"   R$: " + String.format(Locale.US, "%.2f", getProduto().getValor());
    }
}