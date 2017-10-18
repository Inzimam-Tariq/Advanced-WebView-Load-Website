package com.opencartarabic.nativewebapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import im.delight.android.webview.AdvancedWebView;
import opencartarabic.com.opencart.R;

/**
 * Created by Inzimam on 18-Oct-17.
 */

public class MainAct extends Activity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;
    private Utils utils;
    private String url = "http://www.opensourcecart.com/index.php?route=mobile_app/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.utils = new Utils(this);

        if (utils.isNetworkConnected()) {
            mWebView = (AdvancedWebView) findViewById(R.id.webview);
            mWebView.setListener(this, this);
            mWebView.loadUrl(url);
        } else {
            utils.showAlertDialoge();
        }

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        if (utils.isNetworkConnected()) {
            mWebView.onResume();
        } else {
            utils.showAlertDialoge();
        }
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        if (utils.isNetworkConnected()) {
            mWebView.onPause();
        } else {
            utils.showAlertDialoge();
        }
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (utils.isNetworkConnected()) {
            mWebView.onActivityResult(requestCode, resultCode, intent);
        } else {
            utils.showAlertDialoge();
        }
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
    }

    @Override
    public void onPageFinished(String url) {
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        utils.showAlertDialoge("Error Loading Data!");
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {
    }

}
