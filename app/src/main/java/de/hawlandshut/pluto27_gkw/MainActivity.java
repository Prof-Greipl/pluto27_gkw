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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hawlandshut.pluto27_gkw.model.Post;
import de.hawlandshut.pluto27_gkw.test.Testdata;

public class MainActivity extends AppCompatActivity {
    private static final String TAG= "xxx MainActivity";
    RecyclerView mRecyclerView;
    CustomAdapter mAdapter;

    private static final String TEST_MAIL = "fhgreipl@gmail.com";
    private static final String TEST_PASSWORD ="123456";

    FirebaseAuth mAuth;
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

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // TODO: Testdaten erzeugen; remove later
        ArrayList<Post> postList = Testdata.createPostList(3);

        // Manage Recycler View and Adapter
        // Manage the adapter with testdata
        mAdapter = new CustomAdapter();
        mAdapter.mPostList = postList; // Adapter Zugriff auf meine Posts geben.

        mRecyclerView = (RecyclerView) findViewById( R.id.recycler_view);
        mRecyclerView.setLayoutManager( new LinearLayoutManager( this ));
        mRecyclerView.setAdapter( mAdapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_test_auth) {
            doTestAuth();
        }

        if (item.getItemId() == R.id.menu_create_user) {
            mAuth.createUserWithEmailAndPassword(TEST_MAIL, TEST_PASSWORD)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "User created",
                                    Toast.LENGTH_LONG).show();
                            Log.d(TAG, "createUserWithEmail:success");
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "User Creation failed",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }

        if (item.getItemId() == R.id.menu_sign_out){
            FirebaseUser user = mAuth.getCurrentUser();
            if (user == null) {
                Toast.makeText(getApplicationContext(), "Sign out not needed - no user", Toast.LENGTH_LONG).show();
                return true;
            }
            mAuth.signOut();
            Toast.makeText(getApplicationContext(), "User signed out", Toast.LENGTH_LONG).show();
        }

        if (item.getItemId() == R.id.menu_sign_in){
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                Toast.makeText(getApplicationContext(), "User already logged in. Pls. sign out first" + user.getEmail(),
                        Toast.LENGTH_LONG).show();
                return true;
            }

            mAuth.signInWithEmailAndPassword(TEST_MAIL, TEST_PASSWORD).addOnCompleteListener(
                    this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "User signed in",
                                    Toast.LENGTH_LONG).show();
                            Log.d(TAG, "signInWithEmail:success");
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Sign in failed",
                                    Toast.LENGTH_LONG).show();
                        }

                    });
        }

        if (item.getItemId() == R.id.menu_delete){
            FirebaseUser user = mAuth.getCurrentUser();
            if (user == null) {
                Toast.makeText(getApplicationContext(), "Delete not possible - sign in first", Toast.LENGTH_LONG).show();
                return true;
            }

            user.delete().addOnCompleteListener(
                    this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "User deleted", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "User deletion failed", task.getException());
                            Toast.makeText(getApplicationContext(), "User deletion failed: " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    });
        }

        if (item.getItemId() == R.id.menu_password_reset_email){
            mAuth.sendPasswordResetEmail(TEST_MAIL).addOnCompleteListener(
                    this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "Password reset email sent", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Password reset email sent");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Password reset email failed", task.getException());
                            Toast.makeText(getApplicationContext(), "Password reset email failed", Toast.LENGTH_LONG).show();
                        }
                    });
        }

        if (item.getItemId() == R.id.menu_verification_mail){
            FirebaseUser user = mAuth.getCurrentUser();
            if (user == null) {
                Toast.makeText(getApplicationContext(), "Verification mail not possible - sign in first", Toast.LENGTH_LONG).show();
                return true;
            }

            user.sendEmailVerification().addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "Verification mail sent", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Verification mail sent");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Verification mail failed", task.getException());
                            Toast.makeText(getApplicationContext(), "Verification mail failed", Toast.LENGTH_LONG).show();
                        }
                    }
            );

        }








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
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity( intent );
        }
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    private void doTestAuth() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(getApplicationContext(), "User is logged in: "
                            + user.getEmail() + " - "
                            + user.getUid()
                            + " (" + user.isEmailVerified() +")"
                    , Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "User is not logged in", Toast.LENGTH_LONG).show();
        }
    }

}