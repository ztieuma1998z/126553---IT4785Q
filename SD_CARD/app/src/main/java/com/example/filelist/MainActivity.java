package com.example.filelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    DirectoryFragment directoryFragment;
    FileFragment fileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Yeu cau cap quyen truy cap bo nho ngoai
        if(Build.VERSION.SDK_INT >= 23){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
            }
        }

        if(Build.VERSION.SDK_INT >= 23){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

                //Tao du lieu vi du cho TextFile.txt
                try {
                    String content = "Chúc Mừng Năm Mới 2022";
                    FileOutputStream fos = new FileOutputStream(sdPath + "/TextFile.txt");
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(content);
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.v("TAG", "a");
                }

                updateData(sdPath);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission granted");
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

                //Tao du lieu vi du cho TextFile.txt
                try {
                    String content = "Chúc Mừng Năm Mới 2022";
                    FileOutputStream fos = new FileOutputStream(sdPath + "/TextFile.txt");
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(content);
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.v("TAG", "nohope");
                }

                updateData(sdPath);
            }
            else {
                Log.v("TAG", "Permission denied");
                Toast.makeText(this, "Access failed. Please provide the permission to the application!", Toast.LENGTH_LONG).show();
            }
    }

    @Override
    public void updateData(String path) {
        File folderFile = new File(path);
        if (folderFile.isDirectory()) {
            try{
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                directoryFragment = DirectoryFragment.newInstance(path);
                ft.add(R.id.main_holder, directoryFragment);
                ft.addToBackStack("2022");
                ft.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (folderFile.isFile()) {
            Log.v("TAG", "I'm a file");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fileFragment = FileFragment.newInstance(path);
            ft.add(R.id.main_holder, fileFragment);
            ft.addToBackStack("2022");
            ft.commit();
        }
    }
}