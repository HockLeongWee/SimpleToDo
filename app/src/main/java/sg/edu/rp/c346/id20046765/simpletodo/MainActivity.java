package sg.edu.rp.c346.id20046765.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button buttonAdd;
    Button buttonDelete;
    Button buttonClear;
    ListView lvTaskToDo;
    ArrayAdapter aaTodo ;
    String etTaskInput="";
    Spinner spnAddRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.tvTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        lvTaskToDo = findViewById(R.id.lvTaskToDo);
        spnAddRemove = findViewById(R.id.spinner);


        ArrayList<String> alToDo ; // Array List

        alToDo  = new ArrayList<String>(); // ArrayList


        aaTodo  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvTaskToDo.setAdapter(aaTodo);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etTask.setHint("Type in a new task here");
                        buttonDelete.setEnabled(false);
                        buttonAdd.setEnabled(true);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        buttonDelete.setEnabled(true);
                        buttonAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                etTaskInput = etTask.getText().toString();
                alToDo.add(etTaskInput);
                aaTodo.notifyDataSetChanged();
                etTask.setText(null);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(alToDo.size() == 0){
                    Toast.makeText(MainActivity.this, "You don't have task to delete", Toast.LENGTH_SHORT).show();
                }else{
                    int position = Integer.parseInt(etTask.getText().toString());
                    if(alToDo.size() <= position){
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }else{
                        alToDo.remove(position);
                        aaTodo.notifyDataSetChanged();
                        etTask.setText(null);
                    }
                }
            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alToDo.clear();
                aaTodo.notifyDataSetChanged();
                etTask.setText(null);
            }
        });




    }
}
