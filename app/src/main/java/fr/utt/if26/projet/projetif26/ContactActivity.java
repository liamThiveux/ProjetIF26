package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by liamo on 14/11/2017.
 */

public class ContactActivity extends AppCompatActivity implements OnClickListener {

    Button b1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactactivity);

        b1 = (Button) findViewById(R.id.submitButton);
        b1.setOnClickListener(this);
}

    public void onClick(View view) {
        Intent intent1 = new Intent(this,FeedBackActivity.class);
        startActivity(intent1);
    }
}
