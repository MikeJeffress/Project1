package com.adi2.michaeljeffress.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.support.v7.app.AppCompatActivity;
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
    private Button tButtonRemove;
    private Button tButton_Back;
    private EditText tEditText;
    private ListView tListView;
    private ArrayList<String> tArrayList = new ArrayList<String>();
    private ArrayAdapter<String> tArrayAdapter;

    public static int BUTTON_REQUEST_CODE = 28;
    public static int LISTVIEW_REQUEST_CODE  = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_layout);

        tEditText = (EditText)findViewById(R.id.editText_Tasks);
        tButtonAdd = (Button)findViewById(R.id.button_AddTask);
        tButtonRemove = (Button)findViewById(R.id.button_RemoveTask);
        tButton_Back = (Button)findViewById(R.id.tButton_Back);
        tListView = (ListView)findViewById(R.id.listView_Tasks);
        tArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tArrayList);

        tListView.setAdapter(tArrayAdapter);


        tButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = tEditText.getText().toString();
                tArrayList.add(task);
                tArrayAdapter.notifyDataSetChanged();
                tEditText.setText("");
                //Need to stop it from adding black todos
            }
        });

        tButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tArrayList.remove(tArrayList.size() - 1);
     //           tArrayList.remove(position);
                tArrayAdapter.notifyDataSetChanged();
            }
        });

        Intent recievedIntent = getIntent();
        String  intentTask = recievedIntent.getStringExtra("todo");

        tButton_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tasks = tEditText.getText().toString();
                Intent resultIntent = getIntent();
                resultIntent.putExtra("todos", tasks);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });

//            @Override
//            public void onClick(View v) {
//                String editText_Task = tEditText.getText().toString();
//                Intent intent = new Intent(TasksActivity.this, MainActivity.class);
//                intent.putExtra("Task", editText_Task);
//                //Bundle bundle = new Bundle();
//                setResult(RESULT_OK, intent);
//                startActivity(intent);
//                finish();
//
//            }




    }

}
