package br.com.fabricadeprogramador.apptuc;

/**
 * Created by rafae on 25/10/2016.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CestaActivity extends AppCompatActivity {

    @Bind(R.id.lvCesta)
    ListView lvCesta;

    @Bind(R.id.tv_ValorTotal)
    TextView valorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);
        ButterKnife.bind(this);

        List<ItemCesta> cestaList  =  ProdutoService.getCestaList();

        ArrayAdapter<ItemCesta> adapter = new ArrayAdapter<ItemCesta>(this, android.R.layout.simple_list_item_1, cestaList);

        lvCesta.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        Double tot = bundle.getDouble("total");
        //tot = Double.valueOf(String.format(Locale.US, "%.2f", tot));
        //valorTotal.setText(String.valueOf(tot));
        valorTotal.setText(String.format(Locale.US, "%.2f", tot));



    }
}