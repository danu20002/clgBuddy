package com.daneshnaik.chatbot.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.daneshnaik.chatbot.R;

public class privacy_policy extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        webView=findViewById(R.id.webview_privacy);
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
       
        webView.loadUrl("https://www.freeprivacypolicy.com/live/4e594139-c23d-4a81-8193-9311d3681330");

    }
}