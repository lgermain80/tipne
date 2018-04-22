package com.souillard.BasesDeDonnées.verbes;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Verbes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String baseVerbale;

    @ColumnInfo
    private String preterit;

    @ColumnInfo
    private String participe_passe;

    @ColumnInfo
    private int nb_eval;

    @ColumnInfo
    private int nb_fautes;

    @ColumnInfo
    private String rawTrads;

    @Ignore
    private ArrayList<String> trads;


    ///////////////////Builders////////////////////////

    public Verbes(){
    }

    public Verbes(String bv, String pret, String pp, int nb_fautes, int nb_eval){
        this.baseVerbale = bv;
        this.preterit = pret;
        this.participe_passe = pp;
        this.nb_eval = nb_eval;
        this.nb_fautes = nb_fautes;
    }



    //////////////////////Getters///////////////////////

    public int getId() {
        return id;
    }

    public String getBaseVerbale() {
        return baseVerbale;
    }


    public String getPreterit() {
        return preterit;
    }


    public String getParticipe_passe() {
        return participe_passe;
    }


    public String getRawTrads() {
        return rawTrads;
    }


    public ArrayList<String> getTrads() {
        return trads;
    }

    public int getNb_fautes() {
        return nb_fautes;
    }

    public int getNb_eval() {
        return nb_eval;
    }

    //////////////////////////////Setters///////////////////////////////

    public void setNb_eval(int nb_eval) {
        this.nb_eval = nb_eval;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBaseVerbale(String baseVerbale) {
        this.baseVerbale = baseVerbale;
    }

    public void setPreterit(String preterit) {
        this.preterit = preterit;
    }

    public void setParticipe_passe(String participe_passe) {
        this.participe_passe = participe_passe;
    }

    public void setNb_fautes(int nb_fautes) {
        this.nb_fautes = nb_fautes;
    }

    public void setRawTrads(String rawTrads) {
        this.rawTrads = rawTrads;
        this.trads = new ArrayList<String>(Arrays.asList(rawTrads.split(";")));
    }

    public void setTrads(ArrayList<String> trads) {
        this.trads = trads;
    }
}
