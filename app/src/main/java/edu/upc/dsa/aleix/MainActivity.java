package edu.upc.dsa.aleix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Btn = (Button) findViewById(R.id.button);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.name);
                if(name.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Write a name", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent (getApplicationContext(),Info.class);
                    intent.putExtra("name",name.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }
}
