package com.parama.android.siapkontraktor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.actionbarsherlock.app.SherlockActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aldi on 10/15/13.
 */
public class MainActivity extends SherlockActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        prepareListData();
        listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Menu");
        listDataHeader.add("Karyawan");

        List<String> menu = new ArrayList<String>();
        menu.add("Daftar Proyek");
        menu.add("Settings");

        List<String> karyawan = new ArrayList<String>();
        karyawan.add("Daftar Karyawan");

        listDataChild.put(listDataHeader.get(0), menu);
        listDataChild.put(listDataHeader.get(1), karyawan);
    }
}