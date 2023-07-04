package com.daneshnaik.chatbot.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.daneshnaik.chatbot.R;

public class sems_selector extends AppCompatActivity {
WebView webView;
TextView textView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sems_selector);

        webView=findViewById(R.id.webview);
        textView=findViewById(R.id.pdfname);
        Intent intent=getIntent();
        String pdfnam=intent.getStringExtra("name");
        String Url=intent.getStringExtra("url");
        textView.setText(pdfnam);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.getBuiltInZoomControls();
        webView.loadUrl(Url);


    }
}