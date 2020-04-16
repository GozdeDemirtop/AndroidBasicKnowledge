package com.gozdedemirtop.storingdata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.gozdedemirtop.storingdata", Context.MODE_PRIVATE);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        int storeAge = sharedPreferences.getInt("storeAge", 0);

        if(storeAge == 0){
            textView.setText("Your Age: ");
        }
        else{
            textView.setText("Your Age: "+ storeAge);
        }
    }

    public void save(View view){

        AlertDialog.Builder alertSave = new AlertDialog.Builder(this);

        alertSave.setTitle("Save");
        alertSave.setMessage("Are you sure ?");

        alertSave.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!editText.getText().toString().matches("")){
                    int userAge = Integer.parseInt(editText.getText().toString());
                    textView.setText("Your Age: " + userAge);

                    sharedPreferences.edit().putInt("storeAge",userAge).apply();
                }
            }
        });

        alertSave.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
            }
        });

        alertSave.show();
    }

    public void delete(View view){

        AlertDialog.Builder alertDelete = new AlertDialog.Builder(this);

        alertDelete.setTitle("Delete");
        alertDelete.setMessage("Are you sure ?");

        alertDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int deleteData = sharedPreferences.getInt("storeAge",0);

                if (deleteData!=0){
                    sharedPreferences.edit().remove("storeAge").apply();
                    textView.setText("Your Age: ");
                }
            }
        });

        alertDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Not Delete", Toast.LENGTH_LONG).show();
            }
        });
        alertDelete.show();
    }
}
