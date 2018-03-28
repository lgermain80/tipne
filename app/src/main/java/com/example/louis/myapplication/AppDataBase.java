package com.example.louis.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.mots.*;

/**
 * Created by louis on 17/03/18.
 */

@Database(entities = {Mots.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MotsDAO MotsDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"list2").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


}
