package com.MatiasBesmedrsinik.dai.juegocasilleros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MatiasBesmedrsinik.dai.juegocasilleros.claases.Session;
import com.MatiasBesmedrsinik.dai.juegocasilleros.claases.toastes;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity {
private String []_LogicArrive={
        "1,2,4", "1,2,3,5","2,3,6","1,4,5,7", "2,4,8,6,5","3,5,6,9","4,7,8", "5,7,8,9","6,8,9"
};
private boolean []_estadosArrive={
        true, true, true, true, true, true, true, true, true


};
Button btnInicio;
Button btnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        this.setTitle("Juego");
        ObtenerReferencias();
      //  BotonRandom();
        ResetearJuego();
        setearListeners();




    }

    private void setearListeners(){
    String strButtonName;
    Button btnTemp;

    for (int i=0; i<_LogicArrive.length;i++){
        strButtonName="btn" + (i+1);
        btnTemp=getButtonByName(strButtonName);
        btnTemp.setOnClickListener(btnTablero_Click);

    }
        btnInicio.setOnClickListener(btnInicio_Click);
        btnResult.setOnClickListener(btnResult_Click);
    }

    private Button getButtonByName(String strViewName){
        Button btnRetornar=null;
        Resources res;
        res=getResources();
        int id=res.getIdentifier(strViewName, "id",getApplicationContext().getPackageName());

        if (id > 0){
            btnRetornar = (Button)findViewById(id);

        }
       return btnRetornar;
    }
//finish();
    View.OnClickListener btnTablero_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String strButtonId, strNumero;
            int intNumero;
            Button btnPresionado;
            btnPresionado=(Button)v;

            strButtonId = GetViewIdString(v.getId());
            strNumero=btnPresionado.getText().toString();
            intNumero=Integer.valueOf(strNumero);
            PresionoUnBoton(intNumero);
            if (ElUsuarioHaGanado()){
                LogicaDeGanador();

                intents();
            }
        }
    };

    private void LogicaDeGanador(){
        toastes.msj(getApplicationContext(),"Ha ganado");

    }
    private void intents(){
        Intent intent;
        intent = new Intent ( JuegoActivity.this, ResultadoActivity.class);
        finish();
        startActivity(intent);

    }

    private void PresionoUnBoton(int intNumeroBoton){
        int intLogica;
        int intNumeroBotonTemp;
        String strLogicaJugada;
        String[] logicaJugadaArray;
        intLogica=intNumeroBoton-1;
        strLogicaJugada=_LogicArrive[intLogica] ;

        logicaJugadaArray=strLogicaJugada.split(",");

        for (int i=0; i<logicaJugadaArray.length;i++){
          Log.i("Hola",""+ logicaJugadaArray[i]);
          intNumeroBotonTemp=Integer.parseInt(logicaJugadaArray[i]);

          InvertirUnBoton(intNumeroBotonTemp);


        }

        Session.logica+=String.format("Jugada %s: %s \n  ",Session.cont , strLogicaJugada);
        Session.cont++;
        if (gane()){
            LogicaDeGanador();
            Session.estado="Superate champion";
            intents();
        }
        else{
            Session.estado="Sigue intentando...";
        }

    }

    private void InvertirUnBoton(int intBoton){
        int intEstadIndex;
        String strButtonName;
        Button btnTemp;

        strButtonName="btn"+ String.valueOf(intBoton);
        btnTemp=   getButtonByName(strButtonName);
        intEstadIndex=intBoton-1;
        _estadosArrive[intEstadIndex]=!_estadosArrive[intEstadIndex];
        PintarBoton(btnTemp,_estadosArrive[intEstadIndex]);
    }
    private boolean ElUsuarioHaGanado(){
        Boolean blnReturnValue=true;
        Boolean blnTestigo;
        blnTestigo=_estadosArrive[0];

        for (int i=1; i<_estadosArrive.length;i++){
           if (blnTestigo!= _estadosArrive[i]){
               blnReturnValue=false;
               break;

        }
        }
        return blnReturnValue;

    }

    private void PintarBoton(Button btnBoton, boolean blnEstado){
        if (blnEstado==true){
            btnBoton.setBackgroundColor(Color.parseColor("#e8573c"));
        } else{
            btnBoton.setBackgroundColor(Color.parseColor("#1c1970"));
        }
    }

    private void PintarTdElTablero(){
        String strButtonName;
        Button btnTemp;
        for (int i=0; i<_estadosArrive.length;i++){
            strButtonName="btn" + (i+1);
            btnTemp=getButtonByName(strButtonName);
            PintarBoton(btnTemp, _estadosArrive[i]);

        }


    }
    private void ResetearJuego(){
        _estadosArrive = new boolean[_LogicArrive.length];
        for (int i=0; i<_LogicArrive.length;i++){
            _estadosArrive[i]=true;

        }
        Random generadorDeNumeros=new Random();
        int numeroRandom=generadorDeNumeros.nextInt(9);
        _estadosArrive[numeroRandom]=false;
        PintarTdElTablero();

        if (Session.cont!=1){

        }else{
            Session.cont=1;
            Session.logica="";
        }

    }


    private String GetViewIdString(int id){
       String strBottonId;
        Resources res;
        res=getResources();
       strBottonId= res.getResourceEntryName(id);
        return strBottonId;
    }

    private boolean gane(){
        boolean blnReturnValue=false;

        int intContadorTrues=0;
        int intContadorFalses=0;
        for (int i=0; i<_estadosArrive.length;i++){
            if (_estadosArrive[i]== true){
                intContadorTrues++;
            }else{
                intContadorFalses++;
            }

        }
        if ((intContadorTrues==9)|| (intContadorFalses==9)){
            blnReturnValue=true;
        }
        return blnReturnValue;


    }

    /*private boolean Gane_V3{
        boolean blnReturnValue=false;
        boolean blnPrimerValor;
        boolean blnSonIguales=true;
        int i=0;

        blnPrimerValor=_estadosArrive[0];
        while (i<_estadosArrive.length && blnSonIguales){
            if (blnPrimerValor!=_estadosArrive[i]){
                blnSonIguales=false;
            }
            i++;
        }
        if(!blnSonIguales){
            blnReturnValue=false;
        }
        return blnReturnValue;
    }*/

    private void BotonRandom(){
        Random generadorDeNumeros=new Random();
        int numeroRandom=generadorDeNumeros.nextInt(9);
        _estadosArrive[numeroRandom]=false;


    }

    View.OnClickListener btnInicio_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent ( JuegoActivity.this, MainActivity.class);
            toastes.msj(getApplicationContext(),"Ve por una nueva partida");
            finish();
            startActivity(intent);


        }
    };

    View.OnClickListener btnResult_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent;
            Bundle datos;

            intent = new Intent ( JuegoActivity.this, ResultadoActivity.class);
            datos= new Bundle();

            intent.putExtras(datos);
            startActivity(intent);


        }
    };






    private void ObtenerReferencias() {
        btnResult =(Button) findViewById(R.id.btnResult);
        btnInicio =(Button) findViewById(R.id.btnInicio);



    }

}