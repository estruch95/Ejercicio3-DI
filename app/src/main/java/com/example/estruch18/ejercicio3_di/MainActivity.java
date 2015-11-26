package com.example.estruch18.ejercicio3_di;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    //ATRIBUTOS DE LA CLASE
    private ListView listaNumeros;
    private String numeroSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EJECUCIÓN DE MÉTODOS
        declaracionViews();
        cargarDatosListView();

        //Añadido del view correspondiente al Context Menú
        registerForContextMenu(listaNumeros);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Inflado del xml del context menú
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        numeroSeleccionado = ((TextView)info.targetView).getText().toString();
        menu.setHeaderTitle(numeroSeleccionado);
    }

    //Gestión del item seleccionado en el context menú
    public boolean onContextItemSelected(MenuItem item){
        String accion = item.getTitle().toString();

        //Condicionamos la selección del item
        switch (accion){
            case "Añadir elemento a continuacion":
                //Información por pantalla
                Toast.makeText(getApplicationContext(), "Has añadido detrás de "+numeroSeleccionado, Toast.LENGTH_SHORT).show();
                break;
            case "Eliminar elemento seleccionado":
                //""
                Toast.makeText(getApplicationContext(), "Has eliminado a "+numeroSeleccionado, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //Método encargado de declarar todos y cada uno de los views implementados en el xml
    public void declaracionViews(){
        listaNumeros = (ListView)findViewById(R.id.listViewNumeros);
    }

    //Método encargado de cargar datos en la lista a partir de un recurso
    public void cargarDatosListView(){
        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.numeros, android.R.layout.simple_list_item_1);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        listaNumeros.setAdapter(adaptador);
    }
}
