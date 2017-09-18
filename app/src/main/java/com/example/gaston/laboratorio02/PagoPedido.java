package com.example.gaston.laboratorio02;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gaston.laboratorio02.modelo.Tarjeta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PagoPedido extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre;
    EditText etNumTarjeta;
    EditText etNumSeguridad;
    EditText etFechaVenc;

    Button btnConfirmar;
    Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etFechaVenc = (EditText)findViewById(R.id.etFechaVenc);
        etNumSeguridad = (EditText)findViewById(R.id.etNumSeguridad);
        etNumTarjeta = (EditText)findViewById(R.id.etNumeroTarjeta);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
        btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);
    }

    //requestCode = RESULT_OK o RESULT_CANCELED
    private void cerrarActivity(int requestCode){
        this.setResult(requestCode);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCancelar:
            {
                cerrarActivity(RESULT_CANCELED);
                break;
            }
            case R.id.btnConfirmar:
            {
                try{
                    if(!etNombre.getText().toString().equals("") && !etNumTarjeta.getText().toString().equals("") && !etNumSeguridad.getText().toString().equals("") && !etFechaVenc.getText().toString().equals("")){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                        Date fechaVenc = new Date();
                        fechaVenc = dateFormat.parse(etFechaVenc.getText().toString().split("/")[0] + "/01/" + etFechaVenc.getText().toString().split("/")[1] + " 00:00:00 AM");
                        Tarjeta tarj = new Tarjeta(etNombre.getText().toString(), Integer.parseInt(etNumTarjeta.getText().toString()), Integer.parseInt(etNumSeguridad.getText().toString()), fechaVenc);
                        cerrarActivity(RESULT_OK);
                    }
                    else{
                        Toast.makeText(PagoPedido.this, "Debe completar todos los datos para poder confirmar el pago", Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception ex){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PagoPedido.this);
                    alerta.setMessage(ex.getMessage()).create();
                    alerta.show();
                }
                break;
            }
        }
    }
}
