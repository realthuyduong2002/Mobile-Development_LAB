package com.example.exercise4;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyFileToDCIM();
    }

    // Method to copy file to DCIM directory
    private void copyFileToDCIM() {
        File sourceFile = new File(getFilesDir(), "myfile.txt");
        File destDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "myfile.txt");

        try {
            copyFile(sourceFile, destDir);
            Toast.makeText(this, "File copied to DCIM directory", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error copying file", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to copy file from source to destination
    private void copyFile(File sourceFile, File destFile) throws IOException {
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }
}

