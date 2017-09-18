package com.example.gaston.laboratorio02;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gaston.laboratorio02.modelo.Pedido;
import com.example.gaston.laboratorio02.modelo.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Utils utils;
    ToggleButton delivery;
    Spinner horario;
    String seleccion;
    String radioButton_activo;
    Switch reserva;
    TextView texto_informacion;
    RadioGroup radio_grupo;
    RadioButton rb_plato, rb_bebida, rb_postre;
    Boolean pedido_confirmado = false;
    Button agregar, confirmar, reiniciar, pagar;
    ListView lista;
    Utils.ElementoMenu[] listaPlato;
    Utils.ElementoMenu[] listaBebida;
    Utils.ElementoMenu[] listaPostre;
    List contenidoSpinner = new ArrayList();
    ArrayAdapter adaptadorSpinner, adaptadorLista;
    EditText etNombreCliente;
    EditText etEmail;

    boolean platoElegido;
    boolean postreElegido;
    boolean bebidaElegida;

    Utils.ElementoMenu postre;
    Utils.ElementoMenu bebida;
    Utils.ElementoMenu plato;

    ArrayList<Pedido> listaPedidos;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        utils = new Utils();
        utils.iniciarListas();
        listaPlato = utils.getListaPlatos();
        listaBebida = utils.getListaBebidas();
        listaPostre = utils.getListaPostre();
        delivery = (ToggleButton) findViewById(R.id.delivery);
        horario = (Spinner) findViewById(R.id.horario);
        reserva = (Switch) findViewById(R.id.reserva);
        radio_grupo = (RadioGroup) findViewById(R.id.radio_group);
        rb_plato = (RadioButton) findViewById(R.id.radio_plato);
        rb_bebida = (RadioButton) findViewById(R.id.radio_bebida);
        rb_postre = (RadioButton) findViewById(R.id.radio_postre);
        lista = (ListView) findViewById(R.id.lista);
        agregar = (Button) findViewById(R.id.boton_agregar);
        confirmar = (Button) findViewById(R.id.boton_confirmar);
        reiniciar = (Button) findViewById(R.id.boton_reiniciar);
        pagar = (Button) findViewById(R.id.boton_pagar);
        texto_informacion = (TextView) findViewById(R.id.informacion);
        texto_informacion.setMovementMethod(new ScrollingMovementMethod());
        etNombreCliente = (EditText)findViewById(R.id.etNombreCliente);
        etEmail = (EditText)findViewById(R.id.etEmail);
        seleccion = null;

        listaPedidos = new ArrayList<Pedido>();

        platoElegido = false;
        postreElegido = false;
        bebidaElegida = false;

        postre = null;
        bebida = null;
        plato = null;

        contenidoSpinner.add("20:00 Hs");
        contenidoSpinner.add("20:30 Hs");
        contenidoSpinner.add("21:00 Hs");
        contenidoSpinner.add("21:30 Hs");
        contenidoSpinner.add("22:00 Hs");

        rb_plato.setChecked(true);

        adaptadorSpinner = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,contenidoSpinner);
        adaptadorLista = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,listaPlato);
        radioButton_activo = "plato";

        horario.setAdapter(adaptadorSpinner);
        lista.setAdapter(adaptadorLista);

        radio_grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_bebida:
                        adaptadorLista = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listaBebida);
                        radioButton_activo = "bebida";
                        break;
                    case R.id.radio_postre:
                        adaptadorLista = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listaPostre);
                        radioButton_activo = "postre";
                        break;
                    default:
                        adaptadorLista = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listaPlato);
                        radioButton_activo = "plato";
                        break;
                }
                lista.setAdapter(adaptadorLista);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seleccion = adaptadorLista.getItem(i).toString() + "\n";
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pedido_confirmado){
                    Toast.makeText(getApplicationContext(),"No se puede agregar el " + radioButton_activo + ". El pedido ya fue confirmado, por favor, reinicie.",Toast.LENGTH_LONG).show();
                }
                else{
                    if(seleccion!=null){
                        if((radioButton_activo.equals("plato") && !platoElegido) || (radioButton_activo.equals("postre") && !postreElegido) || (radioButton_activo.equals("bebida") && !bebidaElegida)){
                            texto_informacion.setText(texto_informacion.getText() + seleccion);
                            confirmar.setEnabled(true);
                            Utils.ElementoMenu elem = utils.obtenerElementoMenu(seleccion.split("\\(")[0]);
                            if(radioButton_activo.equals("plato")){
                                plato = elem;
                                platoElegido = true;
                            }
                            else{
                                if(radioButton_activo.equals("postre")){
                                    postre = elem;
                                    postreElegido = true;
                                }
                                else{
                                    if(radioButton_activo.equals("bebida")){
                                        bebida = elem;
                                        bebidaElegida = true;
                                    }
                                }
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Ya ha agregado un/a " + radioButton_activo, Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Debe seleccionar un/a " + radioButton_activo,Toast.LENGTH_LONG).show();
                }
            }
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(texto_informacion.getText()!=null) {
                    pedido_confirmado = true;
                    Double importe = 0.0;
                    String[] listaProdSel = texto_informacion.getText().toString().split("\\n");
                    String aux = "\\(";
                    for(String item : listaProdSel){
                        String[] texto=item.split(aux);
                        importe+= utils.obtenerElementoMenu(texto[0]).getPrecio();
                    }
                    texto_informacion.setText(texto_informacion.getText() + importe.toString());
                    Pedido ped = new Pedido(etNombreCliente.getText().toString(), etEmail.getText().toString(), importe, delivery.isChecked(), horario.getSelectedItem().toString(), bebida, plato, postre);
                    listaPedidos.add(ped);
                    DecimalFormat f = new DecimalFormat("##.00");
                    AlertDialog.Builder alerta = new AlertDialog.Builder(Main2Activity.this);
                    alerta.setMessage("El importe a pagar por su pedido es de $" + f.format(importe)).create().show();
                    reiniciarDatos();
                }
            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarDatos();
            }
        });
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listaPedidos.size() > 0){
                    try{
                        Intent intent = new Intent(Main2Activity.this, PagoPedido.class);
                        startActivityForResult(intent, 1);
                    }
                    catch(Exception ex){
                        AlertDialog.Builder alerta = new AlertDialog.Builder(Main2Activity.this);
                        alerta.setMessage(ex.getMessage()).create();
                        alerta.show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "El pago fue cancelado", Toast.LENGTH_LONG).show();
            }
            else{
                if(resultCode == RESULT_OK){
                    Toast.makeText(this, "El pago se confirmó con el monto total", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void reiniciarDatos(){
        radio_grupo.check(R.id.radio_plato);
        adaptadorLista = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listaPlato);
        radioButton_activo = "plato";
        lista.setAdapter(adaptadorLista);
        confirmar.setEnabled(false);
        pedido_confirmado = false;
        horario.setSelection(0);
        texto_informacion.setText(null);
        reserva.setChecked(false);
        seleccion = null;
        delivery.setChecked(false);
        platoElegido = false;
        postreElegido = false;
        bebidaElegida = false;
        postre = null;
        bebida = null;
        plato = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Double importe = 0.0;
        if(plato != null){
            importe += plato.getPrecio();
        }
        if(bebida != null){
            importe += bebida.getPrecio();
        }
        if(postre != null){
            importe += postre.getPrecio();
        }
        Pedido ped = new Pedido(etNombreCliente.getText().toString(), etEmail.getText().toString(), importe, delivery.isChecked(), horario.getSelectedItem().toString(), bebida, plato, postre);
        outState.putSerializable("Pedido", ped);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Pedido ped = (Pedido) savedInstanceState.getSerializable("Pedido");
        etNombreCliente.setText(ped.getNombreCliente());
        etEmail.setText(ped.getEmail());
        delivery.setChecked(ped.getEsDelivery());
        horario.setPrompt(ped.getHoraEntrega());
        String texto = "";
        if(ped.getPostre() != null){
            texto += ped.getPostre().getNombre() + "\\(" + ped.getPostre().getPrecio() + "\\)\n";
        }
        if(ped.getPlato() != null){
            texto += ped.getPlato().getNombre() + "\\(" + ped.getPlato().getPrecio() + "\\)\n";
        }
        if(ped.getBebida() != null){
            texto += ped.getBebida().getNombre() + "\\(" + ped.getBebida().getPrecio() + "\\)\n";
        }
        if(!texto.equals("")){
            texto_informacion.setText(texto);
        }
    }
}
