package com.example.gaston.laboratorio02;

import com.example.gaston.laboratorio02.modelo.Utils;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Utils utils;
    ToggleButton delivery;
    Spinner horario;
    String seleccion;
    String radioButton_activo;
    Switch reserva;
    TextView texto_informacion;
    RadioGroup radio_grupo;
    RadioButton plato, bebida, postre;
    Boolean pedido_confirmado = false;
    Button agregar, confirmar, reiniciar;
    ListView lista;
    Utils.ElementoMenu[] listaPlato;
    Utils.ElementoMenu[] listaBebida;
    Utils.ElementoMenu[] listaPostre;
    List contenidoSpinner = new ArrayList();
    ArrayAdapter adaptadorSpinner, adaptadorLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new Utils();
        utils.iniciarListas();
        listaPlato = utils.getListaPlatos();
        listaBebida = utils.getListaBebidas();
        listaPostre = utils.getListaPostre();
        delivery = (ToggleButton) findViewById(R.id.delivery);
        horario = (Spinner) findViewById(R.id.horario);
        reserva = (Switch) findViewById(R.id.reserva);
        radio_grupo = (RadioGroup) findViewById(R.id.radio_group);
        plato = (RadioButton) findViewById(R.id.radio_plato);
        bebida = (RadioButton) findViewById(R.id.radio_bebida);
        postre = (RadioButton) findViewById(R.id.radio_postre);
        lista = (ListView) findViewById(R.id.lista);
        agregar = (Button) findViewById(R.id.boton_agregar);
        confirmar = (Button) findViewById(R.id.boton_confirmar);
        reiniciar = (Button) findViewById(R.id.boton_reiniciar);
        texto_informacion = (TextView) findViewById(R.id.informacion);
        texto_informacion.setMovementMethod(new ScrollingMovementMethod());
        seleccion = null;

        contenidoSpinner.add("20:00 Hs");
        contenidoSpinner.add("20:30 Hs");
        contenidoSpinner.add("21:00 Hs");
        contenidoSpinner.add("21:30 Hs");
        contenidoSpinner.add("22:00 Hs");

        plato.setChecked(true);

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
                        texto_informacion.setText(texto_informacion.getText() + seleccion);
                        confirmar.setEnabled(true);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Debe seleccionar un " + radioButton_activo,Toast.LENGTH_LONG).show();
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

                }
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_grupo.check(R.id.radio_plato);
                adaptadorLista = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listaPlato);
                radioButton_activo = "Plato";
                lista.setAdapter(adaptadorLista);
                confirmar.setEnabled(false);
                pedido_confirmado = false;
                horario.setSelection(0);
                texto_informacion.setText(null);
                reserva.setChecked(false);
                seleccion = null;
                delivery.setChecked(false);

            }
        });



    }
}
