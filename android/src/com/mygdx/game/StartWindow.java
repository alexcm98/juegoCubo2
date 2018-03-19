package com.mygdx.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alex on 11/03/2018.
 */

public class StartWindow extends Activity {


    static int op = 0;
    private  RadioButton r1;/*para almacenar el partido de waterpolo*/
    private  RadioButton r2;/*para almacenar el partido de baloncesto*/
    private TextView a;

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.layout_princial);
        r1 =(RadioButton) findViewById(R.id.rb1);
        r2 =(RadioButton) findViewById(R.id.rb2);
        a =(TextView) findViewById(R.id.textView3);

        JumpBoxOpenHelper p = new JumpBoxOpenHelper(this,"miDB",null, 2);
        SQLiteDatabase db = p.getReadableDatabase();

        String[] Campos = new String[]{"Score"};

        Cursor c = db.query("Puntuacion",Campos,null,null,null,null,null);

        if(c.moveToFirst()){

            do{
                String c1 = c.getString(0);


                a.setText("Puntuacion Max: "+c1);


            }while (c.moveToNext());



        }

    }

    public void startPuntuacion(View v){
        JumpBoxOpenHelper p = new JumpBoxOpenHelper(this,"miDB",null, 2);
        SQLiteDatabase db = p.getReadableDatabase();

        String[] Campos = new String[]{"Score"};

        Cursor c = db.query("Puntuacion",Campos,null,null,null,null,null);

        if(c.moveToFirst()){

            do{
                String c1 = c.getString(0);


                a.setText("Puntuacion Actual: "+c1);


            }while (c.moveToNext());



        }
    }


    public void startGame(View v){

        if(!r1.isChecked() && !r2.isChecked()){
           Toast.makeText(this,"Primero selecciona personaje",Toast.LENGTH_SHORT).show();
        }else{

            if(r1.isChecked()){
                op = 1;
                Intent i=new Intent(this,AndroidLauncher.class);

                startActivity(i);
            }else{
                op = 2;
                Intent i=new Intent(this,AndroidLauncher.class);

                startActivity(i);
            }


        }


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Quieres salir de la aplicacion");
        builder.setTitle("Aviso");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
