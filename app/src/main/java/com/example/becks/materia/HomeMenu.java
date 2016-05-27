package com.example.becks.materia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.becks.materia.adapters.myFragmentApdater;
import com.example.becks.materia.fragments.Tab1Frag;
import com.example.becks.materia.fragments.Tab2Frag;

public class HomeMenu extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    public RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences  sharedpreferences=getSharedPreferences("USERS", Context.MODE_PRIVATE);
       String uname= sharedpreferences.getString("fName", "");
        String uemail= sharedpreferences.getString("Emails","");
      // to swipe the views
        ViewPager vpager= (ViewPager) findViewById(R.id.vpager);
         // to add the pager of the fragment
        this.addPagers(vpager);
        // to set the Tabs
        TabLayout tabs= (TabLayout) findViewById(R.id.tabs);
        // to occopy the full weitght of the screeen
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        // to set the pagers on the tabs
        tabs.setupWithViewPager(vpager);
        tabs.setOnTabSelectedListener(tabsListener(vpager));
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // to access navigation view
        View headerview = navigationView.inflateHeaderView(R.layout.navheader);
        TextView username = (TextView) headerview.findViewById(R.id.fname);
        TextView email = (TextView) headerview.findViewById(R.id.email);

        username.setText(uname);
        email.setText(uemail);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.writeus:
                        startActivity(new Intent(getApplicationContext(),writeus.class));

                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(),order.class));
                        return  true;


                    // For rest of the options we just show a toast on click
                    case R.id.share:
                        startActivity(new Intent(getApplicationContext(),share.class));
                        return  true;
                    case R.id.aboutus:
                        startActivity(new Intent(getApplicationContext(),aboutus.class));
                        return  true;
                    case R.id.myaccount:
                        startActivity(new Intent(getApplicationContext(),editProfil.class));
                        return  true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        return  true;


                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


    }






    public void addPagers(ViewPager viewPager) {
        myFragmentApdater myFragAdapter = new myFragmentApdater(getSupportFragmentManager());
        myFragAdapter.addFragment(new Tab1Frag());
        myFragAdapter.addFragment(new Tab2Frag());
        // set the adapter
        viewPager.setAdapter(myFragAdapter);


    }
    // when the tab is selected
    private  TabLayout.OnTabSelectedListener tabsListener(final  ViewPager pager) {

        return new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences sharedpreferences=getSharedPreferences("USERS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}








