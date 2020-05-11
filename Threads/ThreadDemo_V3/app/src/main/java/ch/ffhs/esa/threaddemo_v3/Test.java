package ch.ffhs.esa.threaddemo_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Test extends AppCompatActivity {

    private TextView output = null;
    private int counter = 0;
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        output = (TextView) findViewById(R.id.info);
        output.setText("Warten auf Start");
    }


    /*
Params, the type of the parameters sent to the task upon execution.
Progress, the type of the progress units published during the background computation.
Result, the type of the result of the background computation

private class MyTask extends AsyncTask<X, Y, Z>
    protected void onPreExecute(){....}

    protected Z doInBackground(X...x){....}

    protected void onProgressUpdate(Y y){....}

    protected void onPostExecute(Z z){....}

see also:
http://stackoverflow.com/questions/6053602/what-arguments-are-passed-into-asynctaskarg1-arg2-arg3
*/
    class ZufallsZahlTesten extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.d("AsyncTask", "AsyncTask started");
            Random randomizer = new Random();
            while (started){
                if (randomizer.nextInt() % 333333 == 0) {
                    counter++;
                    publishProgress(counter);
                }
            }
            return "Stopped";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            int localCounter = values[0];
            output.setText("Zufall % 333333 = 0: " + localCounter);
            Log.d("AsyncTask", "Zufall % 333333 = 0: " + localCounter);
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String result) {
            Log.d("AsyncTask", "AsyncTask stopped");
            super.onPostExecute(result);
        }
    }


    public void onClickStartBtn(final View btn){
        if(started == false){
            started = true;
            new ZufallsZahlTesten().execute("");
            //ZufallsZahlTesten asyncTask = new ZufallsZahlTesten();
            //asyncTask.execute("");

        }
        else{
            Log.d("Test", "Already started");
        }
        output.setText("Gestartet !");
    }


    public void onClickStopBtn(final View btn){
        started = false;
    }
}
