package br.com.fabricadeprogramador.apptuc;

/**
 * Created by rafae on 25/10/2016.
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;

public class CestaActivity extends Activity {

    List<ItemCesta> cestaList  =  ProdutoService.getCestaList();
    public ArrayAdapter<ItemCesta> myAdapter;


    @Bind(R.id.lvCesta)
    ListView lvCesta;

    @Bind(R.id.tv_ValorTotal)
    TextView valorTotal;


    @OnItemLongClick(R.id.lvCesta)
    boolean onItemLongClick(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(CestaActivity.this);
        builder.setMessage("Deseja remover este Produto ?")
                .setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String nomeProduto = cestaList.get(position).getProduto().getDescricao();
                        cestaList.remove(position);
                        Toast.makeText(CestaActivity.this, "" + nomeProduto + " removido !", Toast.LENGTH_SHORT).show();
                        myAdapter.notifyDataSetChanged();
                        calculaTotal();
                    }
                })
                .setNegativeButton(android.R.string.no, null);

        AlertDialog dialog = builder.create();
        dialog.show();

        return true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);
        ButterKnife.bind(this);

        ArrayAdapter<ItemCesta> adapter = new ArrayAdapter<ItemCesta>(this, android.R.layout.simple_list_item_1, cestaList);
        myAdapter = adapter;
        lvCesta.setAdapter(adapter);
        calculaTotal();
        //Não está mais usando o Total pelo Bundle
        //Bundle bundle = getIntent().getExtras();
        //Double tot = bundle.getDouble("total");

    }

     public void calculaTotal() {
         Double calculoTotal = 0.0;
         for (int i=0; i<cestaList.size(); i++){
            calculoTotal = calculoTotal + (cestaList.get(i).getQuantidade() * cestaList.get(i).getProduto().getValor());
        }
         valorTotal.setText(String.format(Locale.US, "%.2f", calculoTotal));

    }
}