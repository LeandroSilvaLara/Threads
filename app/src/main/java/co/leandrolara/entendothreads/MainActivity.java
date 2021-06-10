package co.leandrolara.entendothreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonIniciar;
    private int numero;
    //Sistema de Fila
    private android.os.Handler handler = new Handler();
    private boolean pararExecucao = false;

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
        pararExecucao = false;
        //Executaveio
        MyRunnable runnable = new MyRunnable();
        //instaciar
        new Thread ( runnable ).start();
    }

    class MyRunnable implements Runnable {

       @Override
        public void run() {
            for (int i=0; i<= 15; i++ ){

                if (pararExecucao)
                    return;

                numero = i;
                Log.d("Thread", "contador: " + i);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("contador: " + numero);
                    }
                });

                /*
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("contador: " + numero);
                    }
                });*/

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void pararThread(View view){
        pararExecucao = true;
    }

   class MyThread extends Thread{
       @Override
       public void run() {
           for (int i=0; i <= 15; i++){
               Log.d("Thread", "contador: " + i);
           }
       }
   }
}
