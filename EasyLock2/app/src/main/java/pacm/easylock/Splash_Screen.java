package pacm.easylock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {
    private long splashRetraso=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent NuevoL;
                NuevoL = new Intent(Splash_Screen.this,Main.class);
                startActivity(NuevoL);
                Splash_Screen.this.finish();

            }
        };
        Timer timer= new Timer();
        timer.schedule(task, splashRetraso);
    }
}
