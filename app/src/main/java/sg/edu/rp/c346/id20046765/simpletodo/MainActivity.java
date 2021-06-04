package sg.edu.rp.c346.id20046765.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
        lvTaskToDo.setAdapter(aaTodo); // I tried very hard to research on how to change the colour of it, but too complex for me lmao


        ArrayAdapter adapterForSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerItems, R.layout.spinner_text); // To change the adapater of what the spinner uses
        spnAddRemove.setAdapter(adapterForSpinner); // To find out how to change this, spent me 2h 42mins. This is to assign / conenct the spinner I think

        adapterForSpinner.setDropDownViewResource(R.layout.spinner_menu_color);
        spnAddRemove.setAdapter(adapterForSpinner);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etTask.setHint("Type in a new task here");
                        buttonDelete.setEnabled(false);
                        buttonAdd.setEnabled(true);
                        buttonAdd.setBackgroundColor(Color.parseColor("#8A2BE2"));
                        buttonClear.setBackgroundColor(Color.parseColor("#8A2BE2"));
                        buttonDelete.setBackgroundColor(Color.parseColor("#4B0082"));
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        buttonDelete.setEnabled(true);
                        buttonAdd.setEnabled(false);
                        buttonClear.setBackgroundColor(Color.parseColor("#8A2BE2"));
                        buttonDelete.setBackgroundColor(Color.parseColor("#8A2BE2"));
                        buttonAdd.setBackgroundColor(Color.parseColor("#4B0082"));
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
