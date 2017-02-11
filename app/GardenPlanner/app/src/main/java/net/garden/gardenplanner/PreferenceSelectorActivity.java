package net.garden.gardenplanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PreferenceSelectorActivity extends AppCompatActivity {

    private static final String TAG = "PreferenceActivity";

    public static Garden mGarden;
    public Plant[] list;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_selector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preference_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Log.v(TAG, "Position: " + position);

            switch (position) {
                case 0:
                    return LayoutSetupFragment.newInstance();
                case 1:
                    return PlantSelectorFragment.newInstance();
            }

            return LayoutSetupFragment.newInstance();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }

    public ViewPager getmViewPager(){
        return mViewPager;
    }

    public static class LayoutSetupFragment extends Fragment {

        private Switch switchIndoor;
        private EditText editLength;
        private EditText editWidth;
        private Switch switchPotted;
        private EditText editPots;
        private EditText editPH;
        private Button btnNext;

        public LayoutSetupFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment LayoutSetupFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static LayoutSetupFragment newInstance() {
            LayoutSetupFragment fragment = new LayoutSetupFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_layout_setup, container, false);

            switchIndoor = (Switch) view.findViewById(R.id.switchIndoor);
            editLength = (EditText) view.findViewById(R.id.length);
            editWidth = (EditText) view.findViewById(R.id.width);
            switchPotted = (Switch) view.findViewById(R.id.switchPotted);
            editPots = (EditText) view.findViewById(R.id.numPots);
            editPH = (EditText) view.findViewById(R.id.pH);
            btnNext = (Button) view.findViewById(R.id.next);

            switchPotted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(switchPotted.isChecked()){
                        editPots.setVisibility(View.VISIBLE);
                        editPots.requestFocus();
                    } else{
                        editPots.setVisibility(View.GONE);
                    }
                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isIndoor = switchIndoor.isChecked();
                    int len = Integer.valueOf(editLength.getText().toString());
                    int wid = Integer.valueOf(editWidth.getText().toString());
                    boolean isPot = switchPotted.isChecked();
                    int numPot;
                    if(isPot){
                        numPot = Integer.valueOf(editPots.getText().toString());
                    } else{
                        numPot = 0;
                    }
                    int ph = Integer.valueOf(editPH.getText().toString());

                    PreferenceSelectorActivity.mGarden = new Garden(isIndoor, len, wid, isPot, numPot, ph); //Create the garden

                    ((PreferenceSelectorActivity)getActivity()).getmViewPager().setCurrentItem(1, true);
                }
            });

            return view;
        }
    }

    public static class PlantSelectorFragment extends Fragment {
        private Plant[] selectedPlants;
        private Plant[] allPlants;

        private int btn1 = 1, btn2 = 2, btn3 = 3, btn4 = 4;

        Button bt1;
        Button bt2, bt3, bt4;

        public PlantSelectorFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PlantSelectorFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static PlantSelectorFragment newInstance() {
            PlantSelectorFragment fragment = new PlantSelectorFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            selectedPlants = new Plant[4];

            try {
                allPlants = getPlants();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_plant_selector, container, false);

            bt1 = (Button) view.findViewById(R.id.plant1);
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), PlantSelectorActivity.class);

                    startActivityForResult(intent, btn1);
                }
            });

            bt2 = (Button) view.findViewById(R.id.plant2);
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), PlantSelectorActivity.class);

                    startActivityForResult(intent, btn2);
                }
            });

            bt3 = (Button) view.findViewById(R.id.plant3);
            bt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), PlantSelectorActivity.class);

                    startActivityForResult(intent, btn3);
                }
            });

            bt4 = (Button) view.findViewById(R.id.plant4);
            bt4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), PlantSelectorActivity.class);

                    startActivityForResult(intent, btn4);
                }
            });

            return view;
        }

        private View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PlantSelectorActivity.class);

                int id = view.getId();

                startActivityForResult(intent, id);
            }
        };

        public Plant[] getPlants() throws IOException {
            InputStream is = getContext().getAssets().open("plants.json");

            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Log.v(TAG, json);

            Plant[] plants = new Gson().fromJson(json, Plant[].class);

            for (Plant p : plants) {
                Log.v(TAG, p.toString());
            }

            return plants;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == btn1) {
                if(resultCode == Activity.RESULT_OK){
                    int i = data.getIntExtra("result", 0);

                    selectedPlants[0] = allPlants[i];
                    bt1.setText(selectedPlants[0].getName());
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            } else if (requestCode == btn2) {
                if(resultCode == Activity.RESULT_OK){
                    int i = data.getIntExtra("result", 0);

                    selectedPlants[1] = allPlants[i];
                    bt2.setText(selectedPlants[1].getName());

                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            } else if (requestCode == btn3) {
                if(resultCode == Activity.RESULT_OK){
                    int i = data.getIntExtra("result", 0);

                    selectedPlants[2] = allPlants[i];
                    bt3.setText(selectedPlants[2].getName());
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            } else {
                if(resultCode == Activity.RESULT_OK){
                    int i = data.getIntExtra("result", 0);

                    selectedPlants[3] = allPlants[i];
                    bt4.setText(selectedPlants[3].getName());
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            }
        }
    }

}
