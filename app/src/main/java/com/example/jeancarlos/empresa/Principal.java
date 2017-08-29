package com.example.jeancarlos.empresa;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    private TextView total;
    private EditText cantidad;
    private Resources resources;
    private Spinner materiales;
    private Spinner dijes;
    private Spinner tipos;
    private Spinner monedas;
    private String material[];
    private String dije[];
    private String tipo[];
    private String moneda[];
    private ArrayAdapter<String> materialesOpc;
    private ArrayAdapter<String> dijesOpc;
    private ArrayAdapter<String> tiposOpc;
    private ArrayAdapter<String> monedasOpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        total = (TextView)findViewById(R.id.lblTotal);
        cantidad = (EditText)findViewById(R.id.txtCantidad);
        materiales = (Spinner)findViewById(R.id.cmbMaterial);
        dijes = (Spinner)findViewById(R.id.cmbDije);
        tipos = (Spinner)findViewById(R.id.cmbTipo);
        monedas = (Spinner)findViewById(R.id.cmbMoneda);
        resources = this.getResources();

        material = getResources().getStringArray(R.array.materiales);
        dije = getResources().getStringArray(R.array.dijes);
        tipo = getResources().getStringArray(R.array.tipos);
        moneda = getResources().getStringArray(R.array.monedas);

        materialesOpc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, material);
        dijesOpc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dije);
        tiposOpc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo);
        monedasOpc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, moneda);

        materiales.setAdapter(materialesOpc);
        dijes.setAdapter(dijesOpc);
        tipos.setAdapter(tiposOpc);
        monedas.setAdapter(monedasOpc);
    }

    public boolean validar(){
        if(cantidad.getText().toString().isEmpty()){
            cantidad.setError(resources.getString(R.string.mensaje_error_cantidad));
            return false;
        }
        if((Integer.parseInt(cantidad.getText().toString())==0)){
            Toast.makeText(this, resources.getString(R.string.mensaje_error_cero), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void calcular(View v) {
        String mat, di, tip, mon;
        int cant, result=0;
        int valorUni=0;

        if (validar()){
            cant = Integer.parseInt(cantidad.getText().toString());
            mat = materiales.getSelectedItem().toString();
            di = dijes.getSelectedItem().toString();
            tip = tipos.getSelectedItem().toString();
            mon = monedas.getSelectedItem().toString();
            resources = this.getResources();

            if (mat.equals(resources.getString(R.string.cuero)) && di.equals(resources.getString(R.string.martillo)) && (tip.equals(resources.getString(R.string.oro)) || tip.equals(resources.getString(R.string.oro_rosado)))) {
                valorUni = 100;
            }
            if (mat.equals(resources.getString(R.string.cuero)) && di.equals(resources.getString(R.string.martillo)) && tip.equals(resources.getString(R.string.plata))) {
                valorUni = 80;
            }
            if (mat.equals(resources.getString(R.string.cuero)) && di.equals(resources.getString(R.string.martillo)) && tip.equals(resources.getString(R.string.niquel))) {
                valorUni = 70;
            }
            if (mat.equals(resources.getString(R.string.cuero)) && di.equals(resources.getString(R.string.ancla)) && (tip.equals(resources.getString(R.string.oro)) || tip.equals(resources.getString(R.string.oro_rosado)))) {
                valorUni = 120;
            }
            if (mat.equals(resources.getString(R.string.cuero)) && di.equals(resources.getString(R.string.ancla)) && tip.equals(resources.getString(R.string.plata))) {
                valorUni = 100;
            }
            if(mat.equals(resources.getString(R.string.cuero)) && di.equals(resources.getString(R.string.ancla)) && tip.equals(resources.getString(R.string.niquel))) {
                valorUni = 90;
            }
            if (mat.equals(resources.getString(R.string.cuerda)) && di.equals(resources.getString(R.string.martillo)) && (tip.equals(resources.getString(R.string.oro))||tip.equals(resources.getString(R.string.oro_rosado)))) {
                valorUni = 90;
            }
            if (mat.equals(resources.getString(R.string.cuerda)) && di.equals(resources.getString(R.string.martillo)) && tip.equals(resources.getString(R.string.plata))) {
                valorUni = 70;
            }
            if (mat.equals(resources.getString(R.string.cuerda)) && di.equals(resources.getString(R.string.martillo)) && tip.equals(resources.getString(R.string.niquel))) {
                valorUni = 50;
            }
            if (mat.equals(resources.getString(R.string.cuerda)) && di.equals(resources.getString(R.string.ancla)) && (tip.equals(resources.getString(R.string.oro))||tip.equals(resources.getString(R.string.oro_rosado)))) {
                valorUni = 110;
            }
            if (mat.equals(resources.getString(R.string.cuerda)) && di.equals(resources.getString(R.string.ancla)) && tip.equals(resources.getString(R.string.plata))) {
                valorUni = 90;
            }
            if (mat.equals(resources.getString(R.string.cuerda)) && di.equals(resources.getString(R.string.ancla)) && tip.equals(resources.getString(R.string.niquel))) {
                valorUni = 80;
            }
            if (mon.equals(resources.getString(R.string.pesos)))
                result = valorUni*cant*3200;
                total.setText(resources.getString(R.string.signo_peso)+result+"");
            if (mon.equals(resources.getString(R.string.dolares)))
                result = valorUni*cant;
                total.setText(resources.getString(R.string.signo_dolar)+result+"");
        }
    }

    public void borrar(View v){
        total.setText("");
        cantidad.setText("");
        materiales.setSelection(0);
        dijes.setSelection(0);
        tipos.setSelection(0);
        monedas.setSelection(0);
        cantidad.requestFocus();
    }
}
