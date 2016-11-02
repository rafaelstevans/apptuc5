package br.com.fabricadeprogramador.apptuc;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Virmerson on 10/22/16.
 */
public class ProdutoService {

    private static List<Produto> produtoList = new ArrayList<>();

    private String codigo="";

    public Produto produtoBanco;

    private  static List<ItemCesta> cestaList = new ArrayList<>();

    public void buscarProduto(String cod) {

        codigo = cod;

        new HttpRequestTask().execute();

        //return produtoBanco;

        /* BUSCA ANTIGA PELA produtoList
        for (int i = 0; i < produtoList.size(); i++) {
            //Comparando cada produto
            if (produtoList.get(i).getCodigoBarra().equals(cod)) {
                //Produto encontrado
                return produtoList.get(i);
            }
        }*/


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

    private class HttpRequestTask extends AsyncTask<Void, Void, Produto> {
        @Override
        protected Produto doInBackground(Void... params) {
            try {
                final String url = "http://192.168.0.162:8080/produtos/" + codigo;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Produto product = restTemplate.getForObject(url, Produto.class);
                produtoBanco = product;
                return product;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Produto product) {
           //produtoBanco = product;
            //stop
        }
    }
    }


