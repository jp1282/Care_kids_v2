package juanpina.mi_app2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;
import java.util.List;

import juanpina.care_kids.R;


public class Principal extends AppCompatActivity {
    Button button6;
    private MapView mapView;
    String nmbct1,nmbct2, nroct1, nroct2;
    String usuario;

    String[] contactos = {"Llamame","S.O.S","Saliendo del Metro","Bajando de la Micro","Ayuda"};
    List<listadoelementos> elements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_principal);
        Intent in = getIntent();
        String usu = in.getStringExtra("usuario");

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });

            }
        });


        //Spinner
        Spinner Contactos = (Spinner) findViewById(R.id.spinnercontacto);
        ArrayAdapter aa= new ArrayAdapter(this, android.R.layout.simple_spinner_item,contactos);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Contactos.setAdapter(aa);

        init(usu);


        }

        public void init(String usuario){
            try{
            SQLiteDatabase db = openOrCreateDatabase("BD_Usuarios", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario(nombre VARCHAR,clave VARCHAR, nmbContacto1 VARCHAR, nroContacto1 VARCHAR, nmbContacto2 VARCHAR, nroContacto2 VARCHAR)");
            final Cursor c = db.rawQuery("select * from usuario where nombre=?", new String[]{String.valueOf(usuario)});
            c.moveToLast();

            int nombrect1 = c.getColumnIndex("nmbContacto1");
            int nombrect2 = c.getColumnIndex("nmbContacto2");
            int numeroct1 = c.getColumnIndex("nroContacto1");
            int numeroct2 = c.getColumnIndex("nroContacto2");

            nmbct1=c.getString(nombrect1).toString();
            nmbct2=c.getString(nombrect2).toString();
            nroct1=c.getString(numeroct1).toString();
            nroct2=c.getString(numeroct2).toString();

            elements = new ArrayList<>();
            elements.add(new listadoelementos("black",nmbct1,nroct1));
            elements.add(new listadoelementos("black",nmbct2,nroct2));


            listadoadaptador listado = new listadoadaptador(elements, this);
            RecyclerView recycler = findViewById(R.id.recycler1);
            recycler.setHasFixedSize(true);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(listado);
        }catch (Exception ex){
                Toast.makeText(this,"No se encontraron los datos.",Toast.LENGTH_LONG).show();
            }
        }


        @Override
        protected void onStart() {
            super.onStart();
            mapView.onStart();

        }

        @Override
        protected void onResume() {
            super.onResume();
            mapView.onResume();
        }

        @Override
        protected void onPause() {
            super.onPause();
            mapView.onPause();
        }

        @Override
        protected void onStop() {
            super.onStop();
            mapView.onStop();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mapView.onSaveInstanceState(outState);
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }
}


