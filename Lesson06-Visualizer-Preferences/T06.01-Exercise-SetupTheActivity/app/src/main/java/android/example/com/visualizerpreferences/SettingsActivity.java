package android.example.com.visualizerpreferences;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Back navigation to the home activity, VisualizerActivity
        ActionBar actionBar = getSupportActionBar();

        if( actionBar != null ){
            actionBar.setDisplayHomeAsUpEnabled( true );
        }// end if
    }

    // (Home button) Back navigation to the home activity, VisualizerActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();

        if( itemID == android.R.id.home ){
            NavUtils.navigateUpFromSameTask( this );
            return true;
        }// end if

        return super.onOptionsItemSelected(item);
    }
}
