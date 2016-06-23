package com.adi2.michaeljeffress.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by michaeljeffress on 6/21/16.
 */
public class TasksActivity extends AppCompatActivity {
    private Button tButtonAdd;
    private Button tButton_Back;
    private EditText tEditText;
    private ListView tListView;
    private ArrayList<String> tArrayList;
    private ArrayAdapter<String> tArrayAdapter;

    public static int BUTTON_REQUEST_CODE = 28;
    public static int LISTVIEW_REQUEST_CODE  = 24;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_layout);

        tEditText = (EditText)findViewById(R.id.editText_Tasks);
        tButtonAdd = (Button)findViewById(R.id.button_AddTask);
        tButton_Back = (Button)findViewById(R.id.tButton_Back);
        tListView = (ListView)findViewById(R.id.listView_Tasks);

        Intent recievedIntent = getIntent();
        tArrayList = recievedIntent.getStringArrayListExtra("tasks");
        if (tArrayList == null) {
            tArrayList = new ArrayList<String>();
        }

        tArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tArrayList);

        tListView.setAdapter(tArrayAdapter);


        tButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = tEditText.getText().toString();
                tArrayList.add(task);
                tArrayAdapter.notifyDataSetChanged();
                tEditText.setText("");
                //Need to stop it from adding blank tasks
            }
        });



        tListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tArrayList.remove(position);
                return false;
            }
        });

        tButton_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("tasks", tArrayList);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });


    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == LISTVIEW_REQUEST_CODE){
//            if(resultCode == RESULT_OK) {
//                ArrayList<String> receivedResults = data.getStringArrayListExtra("todos");
//                int Position = data.getIntExtra("pos", 0);
//                categories.get(Position).setTodos(receivedResults);
//                tArrayAdapter.notifyDataSetChanged();
//
//            }
//
//        }
//    }

}
