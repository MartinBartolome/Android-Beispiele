package ch.ffhs.esa.preferences;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // addPreferencesFromResource(R.xml.einstellungen);
        FragmentManager manger = getFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();
        transaction.replace(android.R.id.content , new SettingsFragment());
        transaction.commit();
    }
}
