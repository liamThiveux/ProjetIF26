package fr.utt.if26.projet.projetif26;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by liamo on 19/11/2017.
 */

public class RecetteAdapter extends ArrayAdapter<Recette2> {

    public RecetteAdapter (Context context, ArrayList<Recette2> recettes){
            super(context,0,recettes);
        }

    // @Override
        /*public View getView(int position, View convertView, ViewGroup parent) {
            Recette recette = getItem(position);
            RecetteHolder holder = null;

            if(convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
            }
            holder = new RecetteHolder();
            holder.titre = (TextView) convertView.findViewById(R.id.rowTitle);
            holder.titre.setText(recette.getTitre());
            holder.photo = (ImageView) convertView.findViewById(R.id.rowPicture);
            File imgFile = new  File(recette.getPhoto());
            Log.d("PHOTOurl",recette.getPhoto());

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView myImage = holder.photo;

                myImage.setImageBitmap(myBitmap);

            };
            return convertView;
        }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recette2 recette = getItem(position);
        RecetteHolder holder = null;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        holder = new RecetteHolder();
        holder.titre = (TextView) convertView.findViewById(R.id.rowTitle);
        holder.titre.setText(recette.getTitre());
        holder.photo = (ImageView) convertView.findViewById(R.id.rowPicture);
        File imgFile = new  File(recette.getPhoto());
        Log.d("PHOTOurl",recette.getPhoto());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = holder.photo;

            myImage.setImageBitmap(myBitmap);

        };
        return convertView;
    }

        @Override
        public int getCount(){
            return super.getCount();
        }

        static class RecetteHolder
        {
            TextView titre;
            ImageView photo;
        }


    }
