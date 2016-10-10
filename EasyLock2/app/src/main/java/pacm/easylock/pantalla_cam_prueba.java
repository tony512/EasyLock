package pacm.easylock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pantalla_cam_prueba extends AppCompatActivity {
    ImageButton vistaCamExt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cam_prueba);

        vistaCamExt=(ImageButton)findViewById(R.id.imagen_pruebaCam_ext);
        vistaCamExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pantalla_cam_prueba.this,Main.class));
            }
        });
    }
}
