package net.garden.gardenplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnBegin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBegin = (Button) findViewById(R.id.startBtn);

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start the preferences selector
                Intent intent = new Intent(MainActivity.this, PreferenceSelectorActivity.class);
                startActivity(intent);
            }
        });
    }


}
