package com.MatiasBesmedrsinik.dai.juegocasilleros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.MatiasBesmedrsinik.dai.juegocasilleros.claases.Session;
import com.MatiasBesmedrsinik.dai.juegocasilleros.claases.toastes;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvName2;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        this.setTitle("Resultado");
        ObtenerReferencias();
        RecibiendoParms();
        setearListeners();


    }
    private void setearListeners(){
        btnBack.setOnClickListener(btnBack_Click);

    }



    View.OnClickListener btnBack_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            toastes.msj(getApplicationContext(),Session.estado);
            Intent intent;

                intent = new Intent ( ResultadoActivity.this, JuegoActivity.class);
                if (Session.estado=="Superate champion"){
                    Session.estado="";
                    Session.cont=1;
                    Session.logica=" ";
                }
                startActivity(intent);
                finish();


            }
    };



    private void RecibiendoParms() {

        String strMens= String.format("Bienvenido %s %s \n  ", Session.Nombre, Session.Ape);
        tvName.setText(strMens);

        tvName2.setText(Session.logica);

    }

    private void ObtenerReferencias() {
        tvName=(TextView) findViewById(R.id.tvNom);
        tvName2=(TextView) findViewById(R.id.tvNom2);
        btnBack=(Button) findViewById(R.id.btnBack);

    }

}