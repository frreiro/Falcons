package com.example.falcons.classesExtras;

import android.content.Context;
import android.content.Intent;

import com.example.falcons.PdfActivity;

public class PdfClass {

    public void ChamarTelaPdf(int x, Context context, String pdfName) {
        Intent pdf = new Intent(context.getApplicationContext(), PdfActivity.class);
        pdf.putExtra("parametro", x);
        pdf.putExtra("parametro2", pdfName);
        context.startActivity(pdf);
    }
}
