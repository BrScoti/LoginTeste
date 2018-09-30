package com.example.bruno.loginteste;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnLogin,btnLogout;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        btnLogin= findViewById(R.id.login);
        textView= findViewById(R.id.textView);
        btnLogout=findViewById(R.id.buttonLogout);
        if(mAuth.getCurrentUser()!=null){
            loginSucess();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Login bem sucedido!",
                        Toast.LENGTH_LONG)
                        .show();
                loginSucess();

            } else {
                Toast.makeText(this,
                        "Erro de login.",
                        Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        }
    }
    public void logar(View view){
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    123
            );
        } else {
            Log.d("TESTE",FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            Toast.makeText(this,
                    "Bem vindo(a) " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();


        }
    }
    public void logOut(View v){
        if (v.getId() == btnLogout.getId()) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(MainActivity.this, MainActivity.class));

                            finish();
                        }
                    });
        }
    }
    public void loginSucess(){
        btnLogin.setVisibility(View.INVISIBLE);
        btnLogout.setVisibility(View.VISIBLE);
        //String t=mAuth.getCurrentUser()+"";
        textView.setText("Seja bem vindo(a)"+mAuth.getCurrentUser().getDisplayName());

    }
}
