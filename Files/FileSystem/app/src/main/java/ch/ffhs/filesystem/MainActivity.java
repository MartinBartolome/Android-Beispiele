package ch.ffhs.filesystem;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private static final String FLASH_FILE_NAME ="myFile.txt";
    private static final String SDCARD_FILE_NAME ="/sdcard/myFile.txt";
    static final Integer WRITE_EXST = 0x3;
    static final Integer READ_EXST = 0x4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_file);
    }



    private void writeToFlashFile(){
        FileOutputStream out = null;
        OutputStreamWriter writer = null;
        TextView errTextView = (TextView) findViewById(R.id.errTextView);
        errTextView.setText("writeToFile ok!");

        try {
            final EditText textToWrite = (EditText) findViewById(R.id.in_text);

            out = openFileOutput(FLASH_FILE_NAME, MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(textToWrite.getText().toString());
            writer.flush();
        } catch (Exception e) {
            errTextView.setText("Exception: " + e.toString());
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                errTextView.setText("Exception: " + e.toString());
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                errTextView.setText("Exception: " + e.toString());
            }
        }

    }

    private void readFromFlashFile(){
        FileInputStream in = null;
        InputStreamReader reader = null;
        TextView myTextView = (TextView) findViewById(R.id.myTextView);
        TextView errTextView = (TextView) findViewById(R.id.errTextView);
        errTextView.setText("readFromFile ok!");

        try {
            char[] inputBuffer = new char[256];
            in = openFileInput(FLASH_FILE_NAME);
            reader = new InputStreamReader(in);
            reader.read(inputBuffer);
            String myText = new String(inputBuffer);
            myTextView.setText(myText);
        } catch (Exception e) {
            errTextView.setText("Exception: " + e.toString());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                errTextView.setText("Exception: " + e.toString());
            }
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                errTextView.setText("Exception: " + e.toString());
            }
        }

    }

    private void writeToSDCardFile(){
        TextView errTextView = (TextView) findViewById(R.id.errTextView);
        errTextView.setText("writeToFile ok!");

        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXST);

        FileWriter f1;
        try {
            f1 = new FileWriter(SDCARD_FILE_NAME);
            final EditText textToWrite = (EditText) findViewById(R.id.in_text);
            f1.write(textToWrite.getText().toString());
            f1.close();
        } catch (IOException e) {
            errTextView.setText("Exception: " + e.toString());
        }
    }


    private void readFromSDCardFile(){
        TextView myTextView = (TextView) findViewById(R.id.myTextView);
        TextView errTextView = (TextView) findViewById(R.id.errTextView);
        errTextView.setText("readFromFile ok!");

        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
        try{
            FileReader fr = new FileReader(SDCARD_FILE_NAME);
            int c;
            String text = "";
            while ((c=fr.read())!=-1){
                text += ((char) c);
            }
            myTextView.setText(text);
            fr.close();

        }
        catch(FileNotFoundException e) {
            errTextView.setText("Exception: " + e.toString());
        }
        catch(IOException e) {
            errTextView.setText("Exception: " + e.toString());
        }
    }


    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    public void onButtonClicked(View v){
        Button btn = (Button) v;

        switch(btn.getId()){

            case R.id.write_flash:
                writeToFlashFile();
                break;

            case R.id.read_flash:
                readFromFlashFile();
                break;

            case R.id.write_sdcard:
                writeToSDCardFile();
                break;

            case R.id.read_sdcard:
                readFromSDCardFile();
                break;

            default:
                TextView errTextView = (TextView) findViewById(R.id.errTextView);
                errTextView.setText("unkown Item!");
                break;
        }

    }
}
