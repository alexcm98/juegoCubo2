package com.mygdx.game;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alex on 15/03/2018.
 */

public class JumpBoxAndroid implements DataBaseCore {

      String pun = "";
    private Context cc;

    public JumpBoxAndroid(Context c){
      cc = c;
    }

    @Override
    public void obtenerPuntuacion(String score) {
        pun = score;

        JumpBoxOpenHelper jb = new JumpBoxOpenHelper(cc,"miDB",null,2);

        SQLiteDatabase db = jb.getWritableDatabase();

        db.execSQL("delete from Puntuacion");
        db.execSQL("insert into Puntuacion values('"+pun+"')");



    }
}
