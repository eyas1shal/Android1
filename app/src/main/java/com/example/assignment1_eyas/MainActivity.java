package com.example.assignment1_eyas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button add;
    ListView listView;
    ArrayAdapter<Task> listAdapter;
    public List<Task> tasks;
    public static final String DATA = "DATA";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPrefs();
        listView = (ListView) findViewById(R.id.tasks_menu);
        add = findViewById(R.id.add_task);
        gson = new Gson();
        tasks = new ArrayList<Task>();
        load();

        try {
            Intent state = getIntent();
            if (state != null && state.getExtras().get("reply").equals("y")) {
                addAndSave(state.getExtras().get("ts_name").toString()
                        , state.getExtras().get("ts_date").toString(),
                        state.getExtras().get("ts_note").toString());
            }
            load();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Intent due = getIntent();
            if(due!=null && !due.getExtras().get("status").equals("")) {
                int s = (int) due.getExtras().get("status");
                int p = (int) due.getExtras().get("pos");
                tasks.get(p).setStatus((byte) s);

                save();}
        }catch (Exception x){
            //x.printStackTrace();
        }
        try {
            Intent delete=getIntent();
            if(delete!=null && !delete.getExtras().get("del").equals("")){
                int p = (int) delete.getExtras().get("pos");
                delAndSave(p);
                load();
            }


        }catch (Exception ex){

        }


        AdapterView.OnItemClickListener itemClickListener = (parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, TasksActivity.class);
            intent.putExtra("task_name", listAdapter.getItem(position).getName());
            intent.putExtra("task_date", listAdapter.getItem(position).getDate());
            intent.putExtra("task_note", listAdapter.getItem(position).getNote());
            intent.putExtra("task_status", listAdapter.getItem(position).getStatus());
            intent.putExtra("pos",position);
            startActivity(intent);
        };
        listView.setOnItemClickListener(itemClickListener);


        add.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent1);
        });


    }

    private void addAndSave(String tsName, String tsDate, String tsNote) {
        tasks.add(new Task(tsName, tsDate, tsNote));
        save();
    }

    private void delAndSave(int pos){
        tasks.remove(pos);
        save();
    }


    public void load() {

        String str = prefs.getString(DATA, "");
        if (!str.equals("")) {
            Task[] tasks1 = gson.fromJson(str, Task[].class);

            listAdapter = new ArrayAdapter<Task>(this,
                    android.R.layout.simple_list_item_1,
                    tasks1);
            listView.setAdapter(listAdapter);
            for (int i = 0; i < tasks1.length; i++) {
                tasks.add(tasks1[i]);
            }
        }
    }


    public void save() {
        String tasksString = gson.toJson(tasks);
        editor.putString(DATA, tasksString);
        editor.apply();
    }


    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

}