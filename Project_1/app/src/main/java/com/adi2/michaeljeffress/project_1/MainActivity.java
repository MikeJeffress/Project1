package com.adi2.michaeljeffress.project_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private ListView mListView;
    private EditText mEditText;
    private Button mButtonRemove;
    private ArrayList<String> mArrayList = new ArrayList<String>();
    private ArrayAdapter<Category> mArrayAdapter;
    private ArrayList<ArrayList<String>> m2ArrayList;
    private ArrayList<Category> categories;

    public static int BUTTON_REQUEST_CODE = 27;
    public static int LISTVIEW_REQUEST_CODE  = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText)findViewById(R.id.editText_Main);
        mButton = (Button)findViewById(R.id.button_Main);
        mButtonRemove = (Button)findViewById(R.id.button_RemoveToDo);
        categories = new ArrayList<>();

        mArrayAdapter = new ArrayAdapter<Category>(this,android.R.layout.simple_list_item_1,categories);
        mListView = (ListView)findViewById(R.id.listView_Main);
        mListView.setAdapter(mArrayAdapter);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDo = mEditText.getText().toString();
                mArrayList.add(toDo);
                mArrayAdapter.notifyDataSetChanged();
                mEditText.setText("");
                //Need to stop it from adding black todos
            }
        });

        mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.remove(mArrayList.size() - 1);
                mArrayAdapter.notifyDataSetChanged();
            }
        });

        mListView = (ListView) findViewById(R.id.listView_Main);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category cat = categories.get(position);
                Intent intentMain = new Intent(MainActivity.this, TasksActivity.class);
                intentMain.putExtra("name", cat.getCategory());
                intentMain.putExtra("todos", cat.getTodos());
                intentMain.putExtra("pos", position);
                startActivityForResult(intentMain, LISTVIEW_REQUEST_CODE);


            }
        });

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LISTVIEW_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                ArrayList<String> receivedResults = data.getStringArrayListExtra("todos");
                int Position = data.getIntExtra("pos", 0);
                categories.get(Position).setTodos(receivedResults);
                mArrayAdapter.notifyDataSetChanged();

            }

        }
    }


}
