package com.bbvacontinental.productosbbva.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.ui.fragment.ProductsFragment;

import butterknife.BindView;

/**
 * Created by johnlopezvega on 8/04/18.
 */

public abstract class BaseNavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected @BindView(R.id.nav_view) NavigationView navigationView;
    protected @BindView(R.id.toolbar) Toolbar toolbar;
    protected DrawerLayout drawer;
    protected ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setDrawable();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setDrawable() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                hiddenKeyboard();
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (!item.isChecked()) {
            switch (item.getItemId()) {
                case R.id.item_update_products:
                    replaceFragment(R.id.content, ProductsFragment.newInstance(), false, true);
                    break;
                case R.id.item_delete_products:
                    replaceFragment(R.id.content, ProductsFragment.newInstance(), false, true);
                    break;
                case R.id.item_transfers_own:

                    break;
                case R.id.item_transfers_others:

                    break;
                case R.id.item_transfers_interbank:

                    break;
            }
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void startLoading() {
        super.startLoading();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.setDrawerIndicatorEnabled(true);
    }
}
