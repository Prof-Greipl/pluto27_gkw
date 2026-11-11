package de.hawlandshut.pluto27_gkw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class ManageAccountActivity extends AppCompatActivity
implements View.OnClickListener {

    TextView mTextViewEmail;
    TextView mTextViewEmailVerified;
    TextView mTextViewTechnicalId;
    Button mButtonSignOut;
    Button mButtonSendActivationMail;

    EditText mEditTextPassword;
    Button mButtonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mTextViewEmail = findViewById(R.id.manageAccountEmail);
        mTextViewEmailVerified = findViewById(R.id.manageAccountEmailVerified);
        mTextViewTechnicalId = findViewById(R.id.manageAccountTechnicalId);
        mButtonSignOut = findViewById(R.id.manageAccountSignOut);
        mButtonSendActivationMail = findViewById(R.id.manageAccountSendActivationMail);
        mEditTextPassword = findViewById(R.id.manageAccountPassword);
        mButtonDelete = findViewById(R.id.manageAccountButtonDelete);

        mButtonSignOut.setOnClickListener(this);
        mButtonSendActivationMail.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.manageAccountSignOut){
            doSignOut();
        }
        if (i == R.id.manageAccountSendActivationMail){
            doSendActivationMail();
        }
        if (i == R.id.manageAccountButtonDelete){
            doDelete();
        }
    }

    private void doDelete() {
        Toast.makeText(this, "Delete clicked", Toast.LENGTH_LONG).show();
    }

    private void doSignOut() {
        Toast.makeText(this, "Sign out clicked", Toast.LENGTH_LONG).show();
    }

    private void doSendActivationMail() {
        Toast.makeText(this, "Send activation mail clicked", Toast.LENGTH_LONG).show();
    }
}