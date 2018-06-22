package in.co.example.bank.bankreg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebasedb;
    private String userId;
    EditText textViewName,textViewUid,textViewDob, textViewStreet,textViewLocality,textViewState,textViewDistrict,textViewPincode,mob,dno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSubmit = (Button) findViewById(R.id.submit);

        textViewName = (EditText) findViewById(R.id.name);
         textViewUid = (EditText)findViewById(R.id.uid);
        dno=(EditText)findViewById(R.id.dno);
        mob=(EditText)findViewById(R.id.mob);
        textViewDob = (EditText)findViewById(R.id.dob);
        textViewStreet = (EditText)findViewById(R.id.street);
        textViewLocality = (EditText)findViewById(R.id.locality);
        textViewState = (EditText)findViewById(R.id.state);
        textViewDistrict = (EditText)findViewById(R.id.district);
        textViewPincode = (EditText)findViewById(R.id.pincode);
        mFirebasedb = FirebaseDatabase.getInstance();
textViewName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
        if(textViewName.getText().length()==0)
            textViewName.setError("Name field cannot be empty");
    }
});
        dno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(dno.getText().length()==0)
                    dno.setError("Door number cannot be left empty");
            }
        });

        textViewUid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewUid.getText().length()==0)
                    textViewUid.setError("Aadhaar Id cannot be empty");
            }
        });
        textViewState.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewState.getText().length()==0)
                    textViewState.setError("State cannot be empty");
            }
        });
        textViewLocality.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewLocality.getText().length()==0)
                    textViewLocality.setError("Enter your locality");
            }
        });
        textViewDob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewDob.getText().length()==0)
                    textViewDob.setError("Enter ur dd/mm/yyyy");
            }
        });
        textViewDistrict.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewDistrict.getText().length()==0)
                    textViewDistrict.setError("District  cannot be blank");
            }
        });
        textViewPincode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewPincode.getText().length()==0||textViewPincode.getText().length()>6)
                    textViewPincode.setError("Enter 6 digit pincode");
            }
        });
        mob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(mob.getText().length()<10)
                    mob.setError("Enter a 10 digit mobile no");
            }
        });
        textViewStreet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(textViewStreet.getText().length()==0)
                    textViewStreet.setError("Street cannot be empty");
            }
        });


        // get reference to 'users' node
        mFirebaseDatabase = mFirebasedb.getReference("users");
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textViewName.getText().toString();
                String dob= textViewDob.getText().toString();
                String doorno=dno.getText().toString();String mobi=mob.getText().toString();
                String street = textViewStreet.getText().toString(); String pincode = textViewPincode.getText().toString();
                String Locality = textViewLocality.getText().toString();
                String District = textViewDistrict.getText().toString();
                String uid=textViewUid.getText().toString();
                String state = textViewState.getText().toString();
                createUser(name,dob,doorno,street,Locality,District,state,pincode,mobi,uid);
            }
        });
    }
    private void createUser(String name,String dob,String doorno,String street,String Locality,String District,String state,String pincode,String mobi,String uid) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth

            userId = mFirebaseDatabase.push().getKey();


        User user = new User(name,doorno,dob,street,Locality,District,state,pincode,mobi,uid);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                   // Log.e(TAG, "User data is null!");
                    return;
                }

                //Log.e(TAG, "User data is changed!" + user.name + ", " + user.email);

                // Display newly updated name and email
                //txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
                textViewName.setText("");textViewDob.setText("");textViewPincode.setText("");textViewStreet.setText("");
                textViewState.setText("");textViewLocality.setText("");textViewDistrict.setText("");textViewUid.setText("");
                dno.setText("");mob.setText("");
                Toast.makeText(MainActivity.this,"Successfully Registered.Our official will contact you soon!",Toast.LENGTH_LONG).show();
                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }
}
