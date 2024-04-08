package com.example.ex4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import androidx.annotation.Nullable;

public class ContactInfo extends AppCompatActivity {

    TextView nameTxtView, emailTxtView, projectTxtView;
    Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactinfo);

        nameTxtView = findViewById(R.id.nameTxtView);
        emailTxtView = findViewById(R.id.emailTxtView);
        projectTxtView = findViewById(R.id.projectTxtView);
        finishBtn = findViewById(R.id.finishBtn);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("nameKey");
            String email = bundle.getString("emailKey");
            String project = bundle.getString("projectKey");

            nameTxtView.setText(name);
            emailTxtView.setText(email);
            projectTxtView.setText(project);
        }

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
