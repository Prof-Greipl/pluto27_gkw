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

public class CreateAccountActivity extends AppCompatActivity
implements View.OnClickListener {
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    EditText mEditTextPasswordRepeat;
    Button mButtonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mEditTextEmail = findViewById(R.id.createAccountEmail);
        mEditTextPassword = findViewById(R.id.createAccountPassword);
        mEditTextPasswordRepeat = findViewById(R.id.createAccountPasswordRepeat);
        mButtonCreateAccount = findViewById(R.id.createAccountButtonCreateAccount);

        mButtonCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i==R.id.createAccountButtonCreateAccount){
            doCreateAccount();
        }
    }

    private void doCreateAccount() {
        Toast.makeText(this, "Create Account clicked", Toast.LENGTH_LONG).show();
    }
}