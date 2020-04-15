package com.gozdedemirtop.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        if(!editText.getText().toString().matches("")){
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText("Your Age: " + userAge);

            sharedPreferences.edit().putInt("storeAge",userAge).apply();
        }
    }

    public void delete(View view){
        int deleteData = sharedPreferences.getInt("storeAge",0);

        if (deleteData!=0){
            sharedPreferences.edit().remove("storeAge").apply();
            textView.setText("Your Age: ");
        }
    }
}
