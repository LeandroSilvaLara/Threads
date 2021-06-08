package co.leandrolara.entendothreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonIniciar;
    private int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIniciar = findViewById(R.id.buttonIniciar);
    }
    public void iniciarThread(View view) {
        /*
        MyThread thread = new MyThread();
        thread.start();
        */
        //Executaveio
        MyRunnable runnable = new MyRunnable();
        //instaciar
        new Thread ( runnable ).start();
    }

    class MyRunnable implements Runnable {

       @Override
        public void run() {
            for (int i=0; i<= 15; i++ ){

                numero = i;
                Log.d("Thread", "contador: " + i);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("contador: " + numero);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
