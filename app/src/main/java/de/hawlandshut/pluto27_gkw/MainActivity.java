package de.hawlandshut.pluto27_gkw;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG= "xxx MainActivity";

    // TODO: Hier rausnehmen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d(TAG, "onCreate");
        Toast.makeText(getApplicationContext(), "App started", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mainMenuHelp) {
            Toast.makeText(getApplicationContext(), "Help clicked", Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId() == R.id.mainMenuManageAccount){
            Toast.makeText(getApplicationContext(), "Manage Account clicked", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Abflug zu SignActity zum Testen
        // TODO: Remove later
        Intent intent = new Intent(this, ManageAccountActivity.class);
        startActivity( intent );
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}