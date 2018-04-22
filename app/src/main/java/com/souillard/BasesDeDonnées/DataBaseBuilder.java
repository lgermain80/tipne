package com.souillard.BasesDeDonnées;

import android.content.Context;
import android.util.Log;

import com.souillard.BasesDeDonnées.listes.Listes;
import com.souillard.BasesDeDonnées.listes.ListesDAO;
import com.souillard.BasesDeDonnées.mots.Mots;
import com.souillard.BasesDeDonnées.mots.MotsDAO;
import com.souillard.R;

public class DataBaseBuilder {

    //////Vars////////////////

    private Context context;
    private MotsDAO motsDAO;
    private ListesDAO listesDAO;
    private AppDataBase appDataBase;


    //////////////Builder///////////////

    public DataBaseBuilder(Context context){
        this.context = context;
        this.appDataBase = AppDataBase.getAppDatabase(context);
        this.listesDAO = appDataBase.ListesDAO();
        this.motsDAO = appDataBase.MotsDao();
    }

////////////Fonction de build de la db////////////////////

    public void buildDataBase(){
        if (listesDAO.getAll().isEmpty()) {
            buildDbListes(listesDAO);
            buildDbMots(motsDAO, listesDAO);

        }
    }

////////////Fonctions de build de la dbMots////////////////////////

    private void buildDbMots(MotsDAO dbMots, ListesDAO dbListes){  //créé la DB
        String[] namesList = getNamesOfTheLists(dbListes);
        int idList = 1;
        for (String nameOfList : namesList){
            int idInList = 1;
            String[] aList = getList(nameOfList);
            for (String linkedWords : aList){
                String[] separatedWords = separateWords(linkedWords);
                Mots aWord = setWord(separatedWords, idList, idInList);
                insertWordInDB(aWord, dbMots);
                idInList ++;
            }
            idList ++;
        }
        Log.i("Base", "Import des mots dans la base de données");
    }

///////////Fonction de build de la dbListes//////////////////////

    private void buildDbListes (ListesDAO dbListes){
        String[] listesParameters = getListsParameters();
        int idList = 1;
        for (String linkedListe : listesParameters){
            String[] separatedListe = separateList(linkedListe);
            Listes aListe = setListe(separatedListe, idList);
            insertListeInDB(aListe, dbListes);
            idList ++;
        }
        Log.i("Base", "Import des listes dans la bsae de donnée");
    }

/////////////////////Fonctions utiles au build de dbListes////////////////


    private String[] getListsParameters(){
        String[] listsParameters = context.getResources().getStringArray(R.array.Listes);
        return listsParameters;
    }

    private String[] separateList(String linkedList){
        String[] separatedList = linkedList.split("/");
        return separatedList;
    }

    private Listes setListe (String[] separatedList, int idList){
        Listes aList = new Listes(idList, separatedList[0], Integer.parseInt(separatedList[1]), Integer.parseInt(separatedList[2]));
        return  aList;
    }

    private void insertListeInDB (Listes aListe, ListesDAO dbListes){dbListes.insertListe(aListe);

    }


///////////////////Fonctions utiles au build de dbMots//////////////////////

    private String[] getNamesOfTheLists(ListesDAO dbListes){      //Récupère les noms de toutes nos listes
        String[] namesList = dbListes.getNames();
        return namesList;
    }

    private String[] getList(String nameList){ //Récupère la liste associé au nom fourni
        int listID = context.getResources().getIdentifier(nameList, "array", context.getPackageName());
        String[] WordsList = context.getResources().getStringArray(listID);
        return WordsList;
    }

    private String[] separateWords (String linkedWords) { //sépare les mots français collé aux mots anglais
        String[] separatedWords = linkedWords.split("/");
        return separatedWords;
    }

    private Mots setWord (String[] separatedWords, int idList, int idInList) { //Permet de définir le mot dans la DB avec ses ID
        Mots aWord = new Mots(idList, idInList, separatedWords[0], separatedWords[1], 0, 0);
        return aWord;
    }

    private void insertWordInDB (Mots aWord, MotsDAO dbMots) {
        dbMots.insertMot(aWord);
    }




}
