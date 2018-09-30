package com.example.bruno.loginteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

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
            btnLogin.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
            //String t=mAuth.getCurrentUser()+"";
            textView.setText("Seja bem vindo(a)"+mAuth.getCurrentUser().getDisplayName());
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

    }
}
