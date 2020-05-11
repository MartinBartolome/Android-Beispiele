package ch.ffhs.esa.primzahlen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class PrimZahlenActivity extends AppCompatActivity {
    private final String TAG = PrimZahlenActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prim_zahlen);


        spinner = (Spinner) findViewById(R.id.spinner);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        Log.d(TAG, Thread.currentThread().getName() + " onCreate ");
    }



    public void onPrimzahlenBerechnen(View v) {

        EditText zahl = (EditText) findViewById(R.id.editStart);
        final int from = Integer.valueOf(zahl.getText().toString());

        zahl = (EditText) findViewById(R.id.editAnzahl);
        final int nbr = Integer.valueOf(zahl.getText().toString());

        Log.d(TAG, "Berechnen von " + nbr + " Primzahlen ab " + from);

        new PrimZahlenTask().execute(nbr, from);
        //PrimZahlenTask asyncTask=new PrimZahlenTask();
        //asyncTask.execute(nbr, from);

        Log.d(TAG, "Berechnen beendet");

    }


    public static boolean isPrime(int zahl) {
        if (zahl < 2) {
            return false;
        } else {
            for (int i = 2; i < zahl; i++) {
                if ((zahl % i) == 0) {
                    return false;
                }
            }
            return true;
        }
    }


    private class PrimZahlenTask extends
            AsyncTask<Integer, Integer, ArrayList<Integer>> {

        public ArrayList<Integer> doInBackground(Integer... zahl) {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            int from = zahl[1];
            publishProgress((int) 0);
            for (int i = 0; i < zahl[0]; i++) {
                while (!isPrime(from)) {
                    from++;
                }
                Log.d(TAG, from + " ist die " + i + ". Primzahl ");
                publishProgress((int) ((i / (float) zahl[0]) * 100));
                arrayList.add(from);
                from++;
                if (isCancelled())
                    break;
            }
            publishProgress((int) 100);
            return arrayList;
        }


        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);
        }

        protected void onPostExecute(ArrayList<Integer> result) {
            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                    PrimZahlenActivity.this,
                    android.R.layout.simple_spinner_item, result);
            spinner.setAdapter(adapter);
        }
    }


    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
