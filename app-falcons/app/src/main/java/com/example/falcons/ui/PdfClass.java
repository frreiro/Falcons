package com.example.falcons.ui;

import android.content.Context;
import android.content.Intent;

import com.example.falcons.MenuActivity;
import com.example.falcons.PdfActivity;

public class PdfClass {

    public void Callpdf(int x, Context context, String pdfName) {
        Intent pdf = new Intent(context.getApplicationContext(), PdfActivity.class);
        pdf.putExtra("parametro", x);
        pdf.putExtra("parametro2", pdfName);
        context.startActivity(pdf);
    }
}
