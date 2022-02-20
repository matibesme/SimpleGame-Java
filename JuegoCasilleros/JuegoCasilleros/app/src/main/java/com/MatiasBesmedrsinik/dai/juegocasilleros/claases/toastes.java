package com.MatiasBesmedrsinik.dai.juegocasilleros.claases;

import android.content.Context;
import android.widget.Toast;

public class toastes {

    public static void msj(Context contexto, String Mensaje){
        Toast toast=Toast.makeText(contexto,Mensaje,Toast.LENGTH_SHORT);
        toast.show();


    }
}
