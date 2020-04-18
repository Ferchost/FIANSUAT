package com.app.fi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.app.fi.R;
import com.app.fi.fragment.FeedFragment;
import com.app.fi.fragment.PerfilFragment;
import com.app.fi.fragment.PesquisaFragment;
import com.app.fi.fragment.PostagemFragment;
import com.app.fi.helper.ConfiguracaoFirebase;
import com.app.fi.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    private String identificadorUsuario;
    private StorageReference storageRef;
    private Usuario usuarioLogado;


    private FirebaseAuth autenticacao;
private FirebaseUser User;

   // FirebaseUser user = autenticacao.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        usuarioLogado = UsuarioFirebase.getDadosUsuarioLogado();
        storageRef = ConfiguracaoFirebase.getFirebaseStorage();
        identificadorUsuario = UsuarioFirebase.getIdUsuario();


        final FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();



        /*String nombre = user.getDisplayName();
        String correo = user.getEmail();
        String correotoedit = user.getEmail(); */

        TextView textView = (TextView)findViewById(R.id.textView7);
        textView.setText("Nombre: " +usuarioPerfil.getDisplayName().toUpperCase());

        TextView textView2 = (TextView)findViewById(R.id.textView8);
        String matrico;
        String matri = usuarioPerfil.getEmail();
        matrico = matri.substring(1,11);
        textView2.setText("Matricula: "+  matrico);

        TextView textView3 = (TextView)findViewById(R.id.textView9);
        textView3.setText("Correo Institucional: "+  usuarioPerfil.getEmail());



        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Mundo UAT");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);

        //Configurar objetos

        //Configurar botton navigation


    }




    public void logout(View view){

        try {
            autenticacao.getInstance().signOut();
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
