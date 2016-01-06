package com.dever.thridlevellist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        List<ThrList<?>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ThrList<String> thrList = new ThrList<>(String.format("第%03d条数据",i));
            for (int j = 0; j < 10; j++) {
                ThrList<String> listThr = new ThrList<>(String.format("第%03d条数据 第%03d条数据", i, j));
                thrList.addThridList(listThr);
                for (int k = 0; k < 10; k++) {
                    ThrList<String> thrList1 = new ThrList<>(String.format("第%03d条数据 第%03d条数据 第%03d条数据",i,j,k));
                    listThr.addThridList(thrList1);
                }
            }
            list.add(thrList);
        }
        //Log.d("list", "onCreate: "+list.get(0).getThridList().get(0).getThridList().get(0).getLevel());
        ThrItemAdapter adapter = new ThrItemAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
