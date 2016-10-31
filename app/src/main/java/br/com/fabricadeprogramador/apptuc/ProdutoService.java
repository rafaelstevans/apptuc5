package br.com.fabricadeprogramador.apptuc;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Virmerson on 10/22/16.
 */
public class ProdutoService {

    private static List<Produto> produtoList = new ArrayList<>();


    private  static List<ItemCesta> cestaList = new ArrayList<>();

    public static Produto buscarProduto(String cod) {

        for (int i = 0; i < produtoList.size(); i++) {
            //Comparando cada produto
            if (produtoList.get(i).getCodigoBarra().equals(cod)) {
                //Produto encontrado
                return produtoList.get(i);
            }
        }
        return null;
    }

    public static void carregarProdutos() {

        produtoList.add(new Produto(1, "JS029791141BR", "Arroz", 6.24));
        produtoList.add(new Produto(2, "JS029791124BR", "Feijão", 7.50));
        produtoList.add(new Produto(3, "JS029791138BR", "Macarrão", 3.25));
        produtoList.add(new Produto(4, "1001", "Sabão em Pó", 8.10));
        produtoList.add(new Produto(5, "1002", "Óleo de Soja", 5.25));
        produtoList.add(new Produto(6, "1003", "Desinfetante", 4.30));
        produtoList.add(new Produto(7, "7896438202948", "MaltoDextrina", 21.40));
        produtoList.add(new Produto(8, "7896098900208", "Bolacha", 3.10));
        produtoList.add(new Produto(9, "7896523206448", "Amaciante", 8.75));
        produtoList.add(new Produto(10, "7897836696544", "Café", 4.85));


    }

    public static void adicionarCesta(ItemCesta itemCesta){
        cestaList.add(itemCesta);
    }
    public static void removerCesta(ItemCesta itemCesta){
        cestaList.remove(itemCesta);
    }

    public static List<ItemCesta> getCestaList() {
        return cestaList;
    }


}