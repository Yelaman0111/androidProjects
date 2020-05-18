package com.bignerdranch.android.curseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Document doc;
    private Thread secThread;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();
    }

    private void init()
    {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();

            }
        };
        secThread = new Thread( runnable );
        secThread.start();
    }

    private void getWeb()
    {
        try {
            doc = Jsoup.connect( "https://minfin.com.ua/currency/" ).get();
            Elements tables = doc.getElementsByTag( "tbody" );
            Element ourTable = tables.get(0);
            Elements elements_from_table = ourTable.children();
            Element dollar = elements_from_table.first();
            Elements dollarElements = dollar.children();
            Log.d("MyLog","Title : " + dollarElements.get( 1 ).text() );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
