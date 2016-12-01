package com.project.empyrean.parkinghelp;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, feedback {
    WebView webView;
    NavigationView navigationView;
    AdView mAdView;
    DialogFragment dialogAnimation;
    DialogFragment dialogRating;
    SideBar sideBar;
    selectionContentToWebView content;
    private int ItemMenu = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_webview);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Initialization();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_rating, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        dialogRating.show(fm, "dialogAnimation");

        return true;
    }
    @Override
    public void OpenFeedback() {
        loadUrlInWebView(3);
    }
    private void Initialization(){
        dialogAnimation = new AnimationFragment();
        dialogRating = new AssessmentFragment();
        sideBar = new SideBar();
        webView = (WebView) findViewById(R.id.webViewWithInstructions);
        WebViewSettings();
        navigationView = (NavigationView)findViewById(R.id.Navigation2);
        navigationView.setNavigationItemSelectedListener(this);
        adViewSettings();

    }
    private void adViewSettings() {
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void WebViewSettings(){
            loadUrlInWebView(ItemMenu);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        openUrl();
    }
    public void loadUrlInWebView(int item){
        content = new selectionContentToWebView();
        content.setItem(item);
        webView.loadUrl(content.getNameHtmlFile());
    }

    private void openUrl(){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/error.html");
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){

                if (content.searchAnimationInString(url)==true) {
                    openDialogWithAnimation(url);
                }
                else{
                    view.loadUrl(url);
                }
                return true;
            }
        });

    }
    private void openDialogWithAnimation(String url){
        Bundle args = new Bundle();
        args.putString("url", url);
        dialogAnimation.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        dialogAnimation.show(fm, "dialogAnimation");
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        sideBar = new SideBar();
        sideBar.setSelectedItemMenu(item);
        reloadWebViewActivity(sideBar.getSelectedItem());
        hideSideBar();
        return true;
    }

    private void hideSideBar(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_webview);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void reloadWebViewActivity(int menuItem){
        loadUrlInWebView(menuItem);
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else {
             super.onBackPressed();
        }
    }

}
