package br.com.fabricadeprogramador.apptuc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.barcode_result)
    EditText barcodeResult;

    @Bind(R.id.etQuantidade)
    EditText quantidade;


    @Bind(R.id.tvDescricao)
    TextView descricao;

    @Bind(R.id.tvValor)
    TextView valor;


    @Bind(R.id.btnAdicCesta)
    ImageButton btnAddCesta;

    private Produto produtoSelecionado;
    private Double total = 0.0;


    @OnClick(R.id.scan_barcode)

    public void scanBarcode (View v){
        Intent intent = new Intent (this, ScanBarcodeActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode== CommonStatusCodes.SUCCESS){
                if(data !=null){
                    MediaPlayer mp;
                    mp = MediaPlayer.create(MainActivity.this, R.raw.beep1);
                    mp.start();


                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult.setText(""+barcode.displayValue);
                    buscar();
                }
                else{
                    Toast.makeText(this, "Produto não encontrado !", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{

        super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @OnClick(R.id.btn_digitar)
    public void digitar(){
        buscar();
    }


    @OnClick(R.id.btnMinhaCesta)
    public void minhaCesta(){
        Intent irParaCesta =  new Intent(this, CestaActivity.class);
        irParaCesta.putExtra("total", total);
        startActivity(irParaCesta);
    }


    private void limparCodigo(){
        barcodeResult.setText("");
    }

    private void limparDescValor(){
        descricao.setText("");
        valor.setText("");
    }

    private void deixarInvisivel() {
        quantidade.setText("");
        quantidade.setEnabled(false);
        quantidade.setVisibility(View.INVISIBLE);
        btnAddCesta.setEnabled(false);
        btnAddCesta.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btnAdicCesta)
    public void adicionarCesta() {

        if (produtoSelecionado != null) {

            if (quantidade.getText().length() != 0) {

                int qtd = Integer.parseInt(quantidade.getText().toString());

                ItemCesta itemCesta = new ItemCesta(produtoSelecionado, qtd);

                ProdutoService.adicionarCesta(itemCesta);

                total = total + (qtd * produtoSelecionado.getValor().doubleValue());

                //Toast.makeText(this, "Total Parcial: " + total, Toast.LENGTH_SHORT).show();

                produtoSelecionado = null;
                deixarInvisivel();
                limparDescValor();

                Toast.makeText(this, "Produto Adicionado com Sucesso !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Digite a Quantidade !", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ProdutoService.carregarProdutos();
    }

    public void buscar() {

        String cod = barcodeResult.getText().toString();
        ProdutoService produtoService = new ProdutoService();
        produtoService.buscarProduto(cod);
        //produtoSelecionado = produtoService.produtoBanco;
        try {
            sleep (2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        produtoSelecionado = produtoService.produtoBanco;

        if (produtoSelecionado != null) {
            //Display
            descricao.setText(produtoSelecionado.getDescricao());
            //valor.setText(produtoSelecionado.getValor().toString());
            valor.setText((String.format(Locale.US, "%.2f", produtoSelecionado.getValor())));

            quantidade.setEnabled(true);
            quantidade.setVisibility(View.VISIBLE);
            btnAddCesta.setEnabled(true);
            btnAddCesta.setVisibility(View.VISIBLE);

            //produtoSelecionado = produtoService.produtoBanco;


        } else {
            Toast.makeText(this, "Produto não encontrado !", Toast.LENGTH_SHORT).show();
            descricao.setText("");
            valor.setText("");


            deixarInvisivel();
        }

        limparCodigo();
    }
}