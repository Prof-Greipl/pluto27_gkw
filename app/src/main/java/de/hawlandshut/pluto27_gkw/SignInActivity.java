package de.hawlandshut.pluto27_gkw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG= "xxx SignInActivity";
    // 3a Declare a variable for each UI View
    EditText mEmail;
    EditText mPassword;
    Button mButtonSignIn;
    Button mButtonResetPassword;
    Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 3b Initialize the variables
        mEmail = findViewById(R.id.signin_email);
        mPassword = findViewById(R.id.signin_password);
        mButtonSignIn = findViewById(R.id.signin_button_signin);
        mButtonResetPassword = findViewById(R.id.signin_button_reset_password);
        mButtonRegister = findViewById(R.id.signin_button_register);

        // Listener (this) für die Buttons setzen
        mButtonSignIn.setOnClickListener(this);
        mButtonResetPassword.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);

        // Preset Input Fields
        // TODO: Remove before production
        mEmail.setText("fhgreipl@gmail.com");
        mPassword.setText("123456");
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.signin_button_signin){
            doSignIn();
        }

        if (i == R.id.signin_button_reset_password){
            doResetPassword();
        }

        if (i == R.id.signin_button_register){
            doRegister();
        }
    }

    private void doRegister() {
        Toast.makeText(getApplicationContext(), "Register clicked", Toast.LENGTH_LONG).show();
    }

    private void doResetPassword() {
        Toast.makeText(this, "Reset password clicked", Toast.LENGTH_SHORT).show();
    }

    private void doSignIn() {
        Toast.makeText(this, "Sign in clicked", Toast.LENGTH_SHORT).show();
    }
}