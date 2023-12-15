package com.example.assignment1_eyas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private EditText name,date,note;
    private Button add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        Intent intent= getIntent();

        name=findViewById(R.id.t_name);
        date=findViewById(R.id.t_date);
        note=findViewById(R.id.t_note);
        add=findViewById(R.id.t_add);

        cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener( v -> {
            Intent intent1 = new Intent(AddTaskActivity.this, MainActivity.class);
            intent1.putExtra("reply","n");
            startActivity(intent1);
        });


        add.setOnClickListener(v->{
            if(!name.getText().toString().equals("")) {
                Task temp = new Task( name.getText().toString(),
                        date.getText().toString(),
                        note.getText().toString());
                Intent intent2 = new Intent(AddTaskActivity.this, MainActivity.class);
                intent2.putExtra("reply","y");
                intent2.putExtra("ts_name",name.getText());
                intent2.putExtra("ts_date",date.getText());
                intent2.putExtra("ts_note",note.getText());
                startActivity(intent2);
            }
            else{
                name.setBackgroundColor(Color.RED);
            }
        });



    }


}