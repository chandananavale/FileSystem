package com.chandana.filesystem;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createFile(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},9);
        } else {
            createFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                createFile();
                Toast.makeText(this, "File is Created", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void createFile(){
        File f=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),"hi.txt");
        try {
            f.createNewFile();
            FileOutputStream fos=new FileOutputStream(f);
            String s="Welcome to Java";
            byte[] b=s.getBytes();
            fos.write(b);
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
