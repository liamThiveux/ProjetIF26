package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by liamo on 14/11/2017.
 */

public class AddRecipeeActivity extends AppCompatActivity implements OnClickListener {
    ImageView viewImage;
    EditText etTitle,etDesc,etNbIngredients,etIng1,etIng2,etIng3,etIng4,etIng5,etIng6,etIng7;
    TextView tvphoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipeeactivity);
        tvphoto = (TextView)findViewById(R.id.tvPhoto);
        ImageButton b1;
        Button bSubmit;
        b1 = (ImageButton) findViewById(R.id.addPhoto);
        b1.setOnClickListener(this);
        bSubmit = (Button)findViewById(R.id.submitRecipee);
        bSubmit.setOnClickListener(this);
        viewImage = (ImageView) findViewById(R.id.addPhoto);
    }

    public static final int PICK_IMAGE = 1;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addPhoto:
                selectImage(); //Demande à l'utilisateur de choisir une image dans sa gallerie
                break;
            case R.id.submitRecipee:
                submitRecipee(); //Stocke en BDD la recette
                break;
        }

        /*Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        intent.putExtra("return-data", true);
        startActivityForResult(intent,PICK_IMAGE);*/

        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);*/
    }
    private void submitRecipee(){
        String picturePath = tvphoto.getTag().toString();
        etTitle = (EditText)findViewById(R.id.etTitre);
        etDesc = (EditText)findViewById(R.id.etDesc);
        etNbIngredients = (EditText)findViewById(R.id.etNbIngredients);
        etIng1 = (EditText)findViewById(R.id.etIng1);
        etIng2 = (EditText)findViewById(R.id.etIng2);
        etIng3 = (EditText)findViewById(R.id.etIng3);
        etIng4 = (EditText)findViewById(R.id.etIng4);
        etIng5 = (EditText)findViewById(R.id.etIng5);
        etIng6 = (EditText)findViewById(R.id.etIng6);
        etIng7 = (EditText)findViewById(R.id.etIng7);

        String titre = etTitle.getText().toString();
        String Desc = etDesc.getText().toString();
        String ing1 = etIng1.getText().toString();
        String ing2 = etIng2.getText().toString();
        String ing3 = etIng3.getText().toString();
        String ing4 = etIng4.getText().toString();
        String ing5 = etIng5.getText().toString();
        String ing6 = etIng6.getText().toString();
        String ing7 = etIng7.getText().toString();

        String NbIngredients = etNbIngredients.getText().toString();
        RecetteIngredientPersistance db = new RecetteIngredientPersistance(this);
        //TODO ingDb.addIngredient(editText1.getText().toString(),....)
        db.addRecette(new Recette(titre,5,picturePath,Desc,ing1,ing2,ing3,ing4,ing5,ing6,ing7));
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), "Recette enregistrée, vous pouvez désormais la retrouver dans la liste des recettes ", duration);
        toast.show();
        Intent backHome = new Intent(this,WhatForActivity.class);
        startActivity(backHome);
    }

    private void selectImage() {

        final CharSequence[] options = { "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddRecipeeActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
               if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
           if (requestCode == 2) {
                Uri selectedImage = data.getData();
                viewImage.setImageURI(selectedImage);
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                if (picturePath.startsWith("/")) picturePath = picturePath.substring(1);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Drawable d = new BitmapDrawable(thumbnail);
                Toast t1 = Toast.makeText(getApplicationContext(),picturePath, Toast.LENGTH_LONG);
                t1.show();
                tvphoto.setTag(picturePath);
                viewImage.setBackground(d);
            }
        }
    }
}

