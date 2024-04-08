package com.example.ex1;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);

        // Register ImageView for Context Menu
        registerForContextMenu(img);

        // Register ImageView for Popup Menu
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_ct_edit) {
            // Handle Edit event
            return true;
        } else if (item.getItemId() == R.id.menu_ct_send) {
            // Handle Send event
            return true;
        } else if (item.getItemId() == R.id.menu_ct_delete) {
            // Handle Delete event
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.option_menu, popupMenu.getMenu());

        // Register event for Popup Menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_sm_new) {
                    showToast("New");
                    return true;
                } else if (item.getItemId() == R.id.menu_sm_save) {
                    showToast("Save");
                    return true;
                } else if (item.getItemId() == R.id.menu_sm_load) {
                    showToast("Load");
                    return true;
                } else if (item.getItemId() == R.id.menu_sm_exit) {
                    showToast("Exit");
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}