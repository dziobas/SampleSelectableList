package pl.dziobas;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Random                r    = new Random();
        ArrayList<SimpleBean> list = new ArrayList<SimpleBean>();

        for(int i = 0; i < 100; i++) {
            list.add(new SimpleBean(String.valueOf(r.nextInt(10000)), r.nextBoolean()));
        }

        MyListAdapter adapter = new MyListAdapter(this, pl.dziobas.R.layout.row, list);

        getListView().setAdapter(adapter);
    }
}
