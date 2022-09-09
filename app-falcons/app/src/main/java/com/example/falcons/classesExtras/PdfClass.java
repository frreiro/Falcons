package com.example.falcons.classesExtras;

import android.content.Context;
import android.content.Intent;

import com.example.falcons.PdfActivity;

public class PdfClass {

    public void ChamarTelaPdf(int pagina, Context context, String pdfName) {

        //Recebe os parametros com pagina, contexto, e o nome do pdf e chama a tela de pdf passando esses parâmetros recebidos da função
        Intent pdf = new Intent(context.getApplicationContext(), PdfActivity.class);
        pdf.putExtra("parametro", pagina);
        pdf.putExtra("parametro2", pdfName);
        context.startActivity(pdf);
    }
}
