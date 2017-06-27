package com.wickerlabs.exp_tr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.wickerlabs.exp_tr.Databases.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{

    ViewPager viewPager;
    static TabHost tabHost;
    Spinner spinner;
    EditText expAdder;

    DatabaseHelper helper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper= new DatabaseHelper(this);

            // they Load pieChart view on a ViewPager with its custom Adapter
        ViewPager viewPager=(ViewPager)findViewById(R.id.displayPart);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

            // Pink Add Circle Floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding Expenses", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                // get adderSheet.xml view as dialog view
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.activity_adder_sheet, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                // set alertdialog builder
                alertDialogBuilder.setView(promptsView);

                TextView cashfrom=(TextView)promptsView.findViewById(R.id.cash_from);

                Cursor res = helper.getAllData();
                while (res.moveToNext()) {
                    cashfrom.setText(res.getString(4));
                }

                spinner=(Spinner) promptsView.findViewById(R.id.spinner);
                expAdder= (EditText) promptsView.findViewById(R.id.expAdder);


                String[] a={"Transport", "Bills","Shopping","Food","Credits"};
                final SpinnerAdapter spinnerAdapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, a );

                spinner.setAdapter(spinnerAdapter);

                // set Actions when SET button on alertDialog is clicked
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("Set",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String selectedExp = (String) spinner.getSelectedItem();
                                        int expCash= Integer.parseInt(expAdder.getText().toString());

                                        helper.addExpense(selectedExp, expCash);


                                        Toast.makeText(MainActivity.this, " selected", Toast.LENGTH_SHORT).show();

                                        // weka functions humu

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
                alertDialog.show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // use the methods for TabHost and ViewPage
        initTabHost();
        initViewPager();
    }


    // Creating Tabs on the screen
    private void initTabHost() {
        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        String[] tabNames={"Today","Yesterday", "Summaries"};

        for(int i=0; i<tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec= tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    public class FakeContent implements TabHost.TabContentFactory {
        Context context;
        public FakeContent(Context mcontext) {
            context = mcontext;
        }

        @Override
        public View createTabContent(String tag) {

            View fakeView= new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    // method for Submitting and adding Fragments to Page view
    private void initViewPager() {

        viewPager= (ViewPager)findViewById(R.id.view_page);

        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(new Today());
        listFragments.add(new Yesterday());
        listFragments.add(new Summaries());

        FragmentAdapter myFragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    // implemented methods from the mainActivity class
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tabHost.setCurrentTab(selectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem= tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollView=(HorizontalScrollView)findViewById(R.id.h_scrollView);
        View tabView= tabHost.getCurrentTabView();
        int scrollPos = tabHost.getLeft()-(hScrollView.getWidth()- tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollPos,0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent i= new Intent(this, Profile.class);
            startActivity(i);
            // Handle the camera action

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_cust_visuals) {

        } else if (id == R.id.nav_cust_inputs) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logOut) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
