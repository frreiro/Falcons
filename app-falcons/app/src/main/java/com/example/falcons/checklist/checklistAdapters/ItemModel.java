package com.example.falcons.checklist.checklistAdapters;

//Objeto do Checklist contendo as inforamções de um checklist
public class ItemModel {

    String text;
    boolean on;
    String telaAntiga;
    String piloto;
    String data;
    String fragmento;

    public ItemModel(String text, boolean on) {
        this.text = text;
        this.on = on;
    }
    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    public String getFragmento() {
        return fragmento;
    }

    public void setFragmento(String fragmento) {
        this.fragmento = fragmento;
    }


    public String getTelaAntiga() {
        return telaAntiga;
    }

    public void setTelaAntiga(String telaAntiga) {
        this.telaAntiga = telaAntiga;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



}
