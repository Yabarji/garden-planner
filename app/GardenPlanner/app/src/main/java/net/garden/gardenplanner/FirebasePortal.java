package net.garden.gardenplanner;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Varun on 2017-02-11.
 */

public class FirebasePortal {

    private static final String TAG = "FirebasePortal";

    private DatabaseReference mDatabase;
    private ArrayList<Plant> plants;

    public FirebasePortal(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public ArrayList<Plant> getList(){
        mDatabase.addListenerForSingleValueEvent(plantListener);
        return plants;
    }

    private ValueEventListener plantListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                Plant p = postSnapshot.getValue(Plant.class);
                plants.add(p);
                Log.v(TAG, "Plant: " + p);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };
}
