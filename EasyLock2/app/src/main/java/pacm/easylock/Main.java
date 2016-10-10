package pacm.easylock;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Stack;

public class Main extends AppCompatActivity  implements View.OnClickListener {
    ImageButton vistaCam;
    MenuItem contactanos;

    ConnectivityManager conMan;

    WifiManager controlWifi;
    Switch aSwitch,aSwitch_wifi,aSwitch_datos;
    ImageView img_estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageView img_estado=(ImageView)findViewById(R.id.img_estado);
        //Desde aqui//
        Resources res = getResources(); // llamar a recursos ya definidos de android//

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec =tabs.newTabSpec("tab1");

        //pestaña#1//
        spec=tabs.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", res.getDrawable(R.drawable.ic_lock_estado2));
        tabs.addTab(spec);

        //pestaña#2//
        spec=tabs.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", res.getDrawable(R.drawable.ic_videocam));
        tabs.addTab(spec);

        //pestaña#3 (con imagen)//
        spec=tabs.newTabSpec("tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("", res.getDrawable(R.drawable.ic_ajustes));
        tabs.addTab(spec);


        tabs.setCurrentTab(0);


        //Empieza switch boton Dispositivo//

        aSwitch=(Switch) findViewById(R.id.tgg_dispositivo2);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Dispositivo conectado", Toast.LENGTH_SHORT).show();
                    img_estado.setBackgroundResource(R.drawable.img_estado_abierto);

                } else {
                    Toast.makeText(getApplicationContext(), "Dispositivo desconectado", Toast.LENGTH_SHORT).show();
                    img_estado.setBackgroundResource(R.drawable.img_estado_cerrado);
                }
            }
        });


        //Empieza switch de wifi//
        controlWifi= (WifiManager)this.getSystemService(Context.WIFI_SERVICE);

        aSwitch_wifi=(Switch) findViewById(R.id.tgg_wifi);
        aSwitch_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    controlWifi.setWifiEnabled(controlWifi.setWifiEnabled(true));
                    Toast.makeText(getApplicationContext(), "Wifi encendido", Toast.LENGTH_SHORT).show();
                } else {
                    controlWifi.setWifiEnabled(controlWifi.setWifiEnabled(false));
                    Toast.makeText(getApplicationContext(), "Wifi apagado", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //Empieza switch de datos//


        aSwitch_datos=(Switch) findViewById(R.id.tgg_datos);
        aSwitch_datos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Toast.makeText(getApplicationContext(), "Datos moviles activados", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Datos moviles desactivados", Toast.LENGTH_SHORT).show();

                }
            }
        });


        //Vista de imagen para la pantalla de la camara (borrar)

         vistaCam=(ImageButton)findViewById(R.id.imagen_pruebaCam);
        vistaCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, pantalla_cam_prueba.class));
                Toast.makeText(getApplicationContext(), "Ingresar a la ventana de contacanos", Toast.LENGTH_SHORT).show();
            }
        });

        //control wifi y datos moviles
        conMan=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        //NetworkInfo netInfo =conMan.getActiveNetworkInfo();
        NetworkInfo.State mobil=conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi=conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        //Cambiar estado de los switch
        if (wifi==NetworkInfo.State.CONNECTED){
            aSwitch_wifi.setChecked(true);
        }else {
            aSwitch_wifi.setChecked(false);
        }

        if (mobil==NetworkInfo.State.CONNECTED){
            aSwitch_datos.setChecked(true);
        }else{
            aSwitch_datos.setChecked(false);
        }

        if (wifi==NetworkInfo.State.DISCONNECTED && mobil==NetworkInfo.State.DISCONNECTED){
            aSwitch.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Porfavor de activar alguna conexion a internet",Toast.LENGTH_SHORT).show();
        }

        if (wifi==NetworkInfo.State.CONNECTED && mobil==NetworkInfo.State.CONNECTED){
            aSwitch.setEnabled(true);
            Toast.makeText(getApplicationContext(),"Su movil esta conectado a una red",Toast.LENGTH_SHORT).show();
        }






        //Action Bar//
         //contactanos =(MenuItem)findViewById(R.id.accion_contactanos);

       // contactanos.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
        //    @Override
        //    public boolean onMenuItemClick(MenuItem item) {

         //       return false;
          //  }
        //});


        //fin de la clase oncreate

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
         //   return true;
        //}

    //    return super.onOptionsItemSelected(item);
    //}



    @Override
    public void onClick(View v) {
        switch (v.getId()){



        }
    }
}
