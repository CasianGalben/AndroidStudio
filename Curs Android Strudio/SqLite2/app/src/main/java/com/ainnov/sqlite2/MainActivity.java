package com.ainnov.sqlite2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd,btnView;
    private EditText etNume,etVarsta;
    private Switch mySwitch;
    private ListView myList;

    DataBaseHelper dataBaseHelper;
    ArrayAdapter<UserModel> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnAfisare);
        etNume = findViewById(R.id.et_username);
        etVarsta = findViewById(R.id.et_varsta);
        mySwitch = findViewById(R.id.switch_activ);
        myList = findViewById(R.id.lv_users);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        afisareUseri(dataBaseHelper);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel = null;
                try{
                    userModel = new UserModel(1,etNume.getText().toString(),Integer.parseInt(etVarsta.getText().toString()),mySwitch.isChecked());
                }catch (Exception e){
                    userModel = new UserModel(1,"error",0,false);
                    Toast.makeText(MainActivity.this,"No Data Error",Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.adaugaUser(userModel);

                afisareUseri(dataBaseHelper);

                etNume.setText("");
                etVarsta.setText("");
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                afisareUseri(dataBaseHelper);

            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel userModel = (UserModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteUser(userModel);
                afisareUseri(dataBaseHelper);
            }
        });

    }


    public void afisareUseri(DataBaseHelper dataBaseHelper2) {
        adapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getUsers());
        myList.setAdapter(adapter);
    }
}