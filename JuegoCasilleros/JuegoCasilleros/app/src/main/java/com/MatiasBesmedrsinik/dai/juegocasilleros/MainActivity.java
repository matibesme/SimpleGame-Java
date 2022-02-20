package com.MatiasBesmedrsinik.dai.juegocasilleros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MatiasBesmedrsinik.dai.juegocasilleros.claases.Session;
import com.MatiasBesmedrsinik.dai.juegocasilleros.claases.toastes;

public class MainActivity extends AppCompatActivity {
    public static final String PARAMETRO_NOMBRE="com.MatiasBesmedrisnik.dai.JuegoCasilleros.MainActivity.PARAM_NOMBRE";
    public static final String PARAMETRO_APELLIDO="com.MatiasBesmedrisnik.dai.JuegoCasilleros.MainActivity.PARAM_APELLIDO";

    EditText edtNom;
    EditText edtApellido;
    Button btnTest;
    Button btnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Ingreso");
        ObtenerReferencias();
        setearListeners();

    }


    private void setearListeners(){
        btnTest.setOnClickListener(btnTest_Click);
        btnRes.setOnClickListener(btnRes_Click);

    }

    View.OnClickListener btnTest_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String strNombre;
            String strApellido;
            strNombre = edtNom.getText().toString();
            strApellido = edtApellido.getText().toString();
            toastes.msj(getApplicationContext(),"Ah jugarrr!!!");
            Intent intent;
            if (DatosValidos()){
                Session.Nombre= strNombre;
                Session.Ape=strApellido;
            intent = new Intent ( MainActivity.this, JuegoActivity.class);
            startActivity(intent);


        }}
    };
    View.OnClickListener btnRes_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String strNombre;
            String strApellido;
            strNombre = edtNom.getText().toString();
            strApellido = edtApellido.getText().toString();
            Intent intent1;
            toastes.msj(getApplicationContext(),"Resultado sin camino...");
            if (DatosValidos()){


                intent1 = new Intent ( MainActivity.this, ResultadoActivity.class);
                Session.Nombre= strNombre;
                Session.Ape=strApellido;
                startActivity(intent1);

            }




        }
    };

    private boolean DatosValidos(){
        boolean blnRes = true;
        String strErrores="";



        if( edtNom.getText().toString().trim().length()==0){
            strErrores+="Por favor ingrese el nombre\n";
            blnRes=false;
        }
        if( edtApellido.getText().toString().trim().length()==0){
            strErrores+="Por favor ingrese su apellido\n";
            blnRes=false;
        }

        if(!blnRes){
            Toast.makeText(getApplicationContext(), strErrores, Toast.LENGTH_LONG).show();
        }
        return blnRes;


    }

    private void ObtenerReferencias() {
        edtNom=(EditText) findViewById(R.id.edtNom);
        edtApellido=(EditText) findViewById(R.id.edtApellido);
        btnTest= (Button) findViewById(R.id.btnTest);
        btnRes= (Button) findViewById(R.id.btnRes);
        Session.cont=1;
        Session.logica="";
    }



}