package com.bignerdranch.android.curseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();
    }

    private void init()
    {
        listView = findViewById( R.id.listView );
        arrayList = new ArrayList<>(  );
        adapter = new CustomArrayAdapter( this, R.layout.list_item_1,arrayList ,getLayoutInflater() );
        listView.setAdapter( adapter );

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


            for(int i = 0; i < ourTable.childrenSize(); i++){
                ListItemClass items = new ListItemClass();
                items.setData_1( ourTable.children().get( i).child(0).text());
                items.setData_2(  ourTable.children().get( i).child(1).text().substring( 0,7 ) );
                items.setData_3(  ourTable.children().get( i).child(2).text().substring( 0,7 ) );
                items.setData_4(  ourTable.children().get( i).child(3).text() );
                arrayList.add( items );
            }

            runOnUiThread( new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            } );



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
