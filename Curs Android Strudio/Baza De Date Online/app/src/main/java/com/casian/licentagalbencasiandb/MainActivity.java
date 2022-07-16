package com.casian.licentagalbencasiandb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;


public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    List<User> list;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//    mRecyclerView = findViewById(R.id.userList);
//    mRecyclerView.setHasFixedSize(true);
//    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    list = new ArrayList<>();
    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
    getUsers(mDatabaseReference);



    }

   public void setUsers(List<User> lista){
       mRecyclerView = findViewById(R.id.userList);
       mRecyclerView.setHasFixedSize(true);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       myAdapter = new MyAdapter(this,lista);
       mRecyclerView.setAdapter(myAdapter);

       myAdapter.setOnItemClickListener(new MyAdapter.ClickListener() {
           @Override
           public void onItemClick(int position, View v) {
               Intent intent = new Intent(MainActivity.this,Detalii.class);
               intent.putExtra("firstname",lista.get(position).getFirstname()); // ai grija sa selectezi mereu ce out extra vrei deoarece Ai mai multe functii cu mai multe tipuri de varibile ce se trimit
               intent.putExtra("lastname",lista.get(position).getLastname());
               intent.putExtra("age",lista.get(position).getAge());

               startActivity(intent);
           }

           @Override
           public void onItemLongClick(int position, View v) {
               Toast.makeText(getApplicationContext(),"Nume de familie:"+lista.get(position).getLastname(),Toast.LENGTH_LONG).show();

           }
       });
   }

   public void getUsers(DatabaseReference databaseReference){
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               list.removeAll(list);
               for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                   User user = dataSnapshot1.getValue(User.class);
                   list.add(user);
               }

               setUsers(list);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
   }



}