package com.example.filelist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DirectoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectoryFragment extends Fragment {

    ArrayList<String> list_data = new ArrayList<>();
    ArrayList<String> list_display = new ArrayList<>();

    private static final String ARG_PARAM = "param";

    private String mParam;

    public DirectoryFragment() {
        // Required empty public constructor
    }

    public static DirectoryFragment newInstance(String param) {
        DirectoryFragment fragment = new DirectoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        ListView listView = view.findViewById(R.id.list_view);


        list_data.clear();
        File folderFile = new File(mParam);
        File[] files = folderFile.listFiles();
        for (File f : files) {
            Log.v("TAG", f.getAbsolutePath());
            list_data.add(f.getAbsolutePath());

            String folder = f.getAbsolutePath();
            int pos = folder.length() - 1;
            while (folder.charAt(pos) != '/') pos--;
            folder = folder.substring(pos + 1);
            list_display.add(folder);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list_display);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((ItemClickListener)getActivity()).updateData(list_data.get(i));
            }
        });
        return view;
    }
}