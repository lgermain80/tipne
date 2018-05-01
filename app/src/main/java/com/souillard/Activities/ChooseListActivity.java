package com.souillard.Activities;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.souillard.BasesDeDonnées.AppDataBase;
import com.souillard.R;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.souillard.BasesDeDonnées.listes.ListesDAO;
import com.souillard.BasesDeDonnées.AppDataBase;
import java.lang.String;



public class ChooseListActivity extends Activity {

    private ListView mListView;
    public final static String nameList = "";
    private TextView text;

    AppDataBase db = AppDataBase.getAppDatabase(ChooseListActivity.this);
    ListesDAO dbListes = db.ListesDAO();
    String[] namesList = dbListes.getNames();
    String[] namesListDisplay = dbListes.getProperNames();
    String choixUser;
    String modeChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_liste);

        //Défini notre listView
        mListView = (ListView) findViewById(R.id.listView);

        Bundle extras = getIntent().getExtras();
        choixUser = extras.getString("choixUtilisateur");
        modeChoisi = extras.getString("mode");

        if (choixUser.equals("mots")) {
            motsChoosed();
        }

    }

    private void motsChoosed () {
        //Défini les données à afficher et comment on les affiche
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChooseListActivity.this,
                R.layout.button_choix_liste,R.id.liste, namesListDisplay);

        //On associe ces données à la ListView
        mListView.setAdapter(adapter);

        //que faire lorsqu'on clique sur un item ?
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomliste = namesList[position];
                if (modeChoisi.equals("apprentissage")) {
                    Intent i = new Intent(ChooseListActivity.this, LearningMotsActivity.class);
                    i.putExtra(nameList, nomliste);
                    startActivity(i);
                }
            }
        });
    }
}
