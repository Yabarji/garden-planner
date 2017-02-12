package net.garden.gardenplanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class PlantSelectorActivity extends AppCompatActivity {

    public static final String TAG = "PlantSelectorDialog";

    private Plant[] allPlants;
    private ListView plants;
    private PlantAdapter plantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_selector);

        allPlants = PreferenceSelectorActivity.list;

        plants = (ListView) findViewById(R.id.list);

        plantAdapter = new PlantAdapter(this, allPlants);
        plants.setAdapter(plantAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    private class PlantAdapter extends ArrayAdapter<Plant> {

        private Context context;
        private Plant[] allPlants;

        public PlantAdapter(Context context, Plant[] values){
            super(context, -1, values);
            this.context = context;
            this.allPlants = values;

        }
        @Override
        public int getCount() {
            int counter = 0;

            for(Plant p: allPlants) {
                if (p == null) {
                    counter++;
                }
            }

            return allPlants.length - counter;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public Plant getItem(int i) {
            return allPlants[i];
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_item, viewGroup, false);

            TextView t = (TextView) rowView.findViewById(R.id.plantName);
            t.setText(allPlants[i].getName());

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", i);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            });

            return rowView;
        }
    }
}
