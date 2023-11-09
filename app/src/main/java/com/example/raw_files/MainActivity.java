package com.example.raw_files;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "rawtest.txt";
    EditText Text_Input;
    TextView textView;
    Button Raw_file_loader;
    Button Text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text_Input = findViewById(R.id.Text_Input);
        textView = findViewById(R.id.textView2);
        Raw_file_loader = findViewById(R.id.Raw_file_loader);
        Text1 = findViewById(R.id.Text1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.Credits)
        {
            Intent si = new Intent(this,credits.class);
            startActivity(si);
        }
        return true;
    }

    public void Text_Read(View view) {
        String g = String.valueOf(Text_Input.getText());
        textView.setText(g);
    }

    public void Read_Raw_File(View view) {
        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        int resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            textView.setText(sB);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}