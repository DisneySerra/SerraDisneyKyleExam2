package exam.disney.serra.serradisneykyleexam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eGrade1, eGrade2, eAve;
    ArrayList<String> keyList;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("grade");
        eFname = findViewById(R.id.eFN);
        eLname = findViewById(R.id.eLN);
        eGrade1 = findViewById(R.id.eGrade1);
        eGrade2 = findViewById(R.id.eGrade2);
        eAve = findViewById(R.id.eAve);
        keyList = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        super.onStart();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss: dataSnapshot.getChildren()) {
                    keyList.add(ss.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Toast.makeText(this, keyList.get(0), Toast.LENGTH_SHORT).show();
    }


//    public void addRecord(View v) {
//        String fname = eFname.getText().toString();
//        String lname = eLname.getText().toString();
//        Long grade = Long.parseLong(eGrade1.getText().toString());
//        Long grade2 = Long.parseLong(eGrade2.getText().toString());
//        int ave = (Integer.parseInt(String.valueOf(eGrade1.getText()))+ Integer.parseInt(String.valueOf(eGrade2.getText())))/2;
//        Student sgrade = new Student(fname,lname,ave);
//        String key = root.push().getKey();
//        root.child(key).setValue(sgrade);
//        keyList.add(key);
//        Toast.makeText(this,"record added to db",Toast.LENGTH_LONG).show();
//    }

    public void addRecord(View v){
        if(v.getId() == R.id.button){
            int a, b;
            try{
                a = Integer.parseInt(eGrade1.getText().toString());
                b = Integer.parseInt(eGrade2.getText().toString());
                int c = (a+b)/2;

                String fname = eFname.getText().toString();
                String lname = eLname.getText().toString();
                Student sgrade = new Student(fname,lname,c);
                String key = root.push().getKey();
                root.child(key).setValue(sgrade);
                keyList.add(key);


                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        index = (int) dataSnapshot.getChildrenCount() - 1;
                        Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                        eAve.setText(stud.getAve().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
            catch (NumberFormatException e){
                Toast.makeText(this, "Cannot put null values", Toast.LENGTH_LONG).show();
            }


        }

        Toast.makeText(this,"record added to db",Toast.LENGTH_LONG).show();
    }
}
