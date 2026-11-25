package de.hawlandshut.pluto27_gkw;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG= "xxx SignInActivity";
    // 3a Declare a variable for each UI View
    EditText mEmail;
    EditText mPassword;
    Button mButtonSignIn;
    Button mButtonResetPassword;
    Button mButtonRegister;

    FirebaseAuth mAuth;

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

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
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
        // AccountCreation erfolgt in der CreateAccountActivity.
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity( intent );
    }

    private void doResetPassword() {
        Toast.makeText(this, "Reset password clicked", Toast.LENGTH_SHORT).show();
    }

    private void doSignIn() {
        String email = mEmail.getText().toString(); // Hole E-Mail aus dem Eingabefeld
        String password = mPassword.getText().toString(); // Hole Passwort aus dem Eingabefeld
        // Validierung
        if (email.isEmpty()) {
            Toast.makeText(this, "E-Mail missing", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Password missing", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(getApplicationContext(), "User signed in",
                                Toast.LENGTH_LONG).show();
                        Log.d(TAG, "signInWithEmail:success");
                        //
                        finish();
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Sign in failed",
                                Toast.LENGTH_LONG).show();
                    }

                });
    }

}
