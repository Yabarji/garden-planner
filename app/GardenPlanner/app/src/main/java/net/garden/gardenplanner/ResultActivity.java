package net.garden.gardenplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    PieChart mPieChart;
    Garden mGarden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String gardenJson = getIntent().getExtras().getString(Garden.KEY);

        mGarden = new Gson().fromJson(gardenJson, Garden.class);

        mPieChart = (PieChart) findViewById(R.id.chart);
        Plant[] plants = mGarden.getPlants();

        int y = 0;
        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < 4; i ++) {
            y += plants[i].getYield();
            //int area = plants[i].getArea() / mGarden.getArea();
            int area = plants[i].getArea();
            entries.add(new PieEntry(area, plants[i].getName()));
        }

        TextView price = (TextView) findViewById(R.id.price);
        double pr = 7.70 * 4 + 8;
        price.setText("Price: $" + pr);

        TextView yield = (TextView) findViewById(R.id.yield);
        yield.setText("Yield: " + y + " servings");

        TextView labour = (TextView) findViewById(R.id.labour);
        labour.setText("Labour: " + (1.7*4) + " (Low Maintenance)");

        PieDataSet pds = new PieDataSet(entries, "Garden Distribution");
        pds.setColors(ColorTemplate.MATERIAL_COLORS);

        mPieChart.setData(new PieData(pds));
        mPieChart.getLegend().setEnabled(false);
        mPieChart.setContentDescription("Title");
        mPieChart.invalidate();
    }
}
