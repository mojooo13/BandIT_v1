package com.example.mo.bandit_v1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mo on 21.10.2014.
 */
public class ProfilFragment extends Fragment{


    ImageView contactImageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.profil, container, false);

        contactImageView = (ImageView) view.findViewById(R.id.imageView);
        contactImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent , "Select Profil-Picture"),1);

            }
        });

        //int profilID = getActivity().getIntent().getExtras().getInt("profilID");
        //final ProfilData profilData = new ProfilData(profilID);
        Intent intent = getActivity().getIntent();
        Data data = intent.getParcelableExtra("data");
        final ProfilData profilData = data.profilData;
        //final ProfilData profilData = new ProfilData("Moritz", "Hauch" , "moritz.hauch1@gmx.net", "Rupert Gugg Str 4", "Gitarre", "Hard Rock");

        TextView vornameProfilTextView = (TextView) view.findViewById(R.id.vornameProfilTextView);
        TextView nachnameProfilTextView = (TextView) view.findViewById(R.id.nachnameProfilTextView);
        TextView emailProfilTextView = (TextView) view.findViewById(R.id.emailProfilTextView);
        TextView adressProfilTextView = (TextView) view.findViewById(R.id.adressProfilText);
        TextView instrumentProfilTextView = (TextView) view.findViewById(R.id.instrumetProfilTextView);
        TextView genreProfilTextView = (TextView) view.findViewById(R.id.genreProfilTextView);

        vornameProfilTextView.setText(profilData.getProfilVorname().toString());
        nachnameProfilTextView.setText(profilData.getProfilNachname().toString());
        emailProfilTextView.setText(profilData.getProfilEmail().toString());
        adressProfilTextView.setText(profilData.getProfilAdress().toString());


        String instrumentText = "instruments:";
        for(int i = 0; i < profilData.getProfilInstruments().length;i++){
            instrumentText = instrumentText + profilData.getProfilInstruments()[i];
        }

        instrumentProfilTextView.setText(instrumentText);
        genreProfilTextView.setText("Genre: " + profilData.getProfilGenre().toString());
        Button editProfilButton = (Button) view.findViewById(R.id.editProfilButton);
        editProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditProfilActivity.class);
                intent.putExtra("vorname",profilData.getProfilVorname().toString());
                intent.putExtra("nachname",profilData.getProfilNachname().toString());
                intent.putExtra("email",profilData.getProfilEmail().toString());
                intent.putExtra("adress",profilData.getProfilAdress().toString());
                //intent.putExtra("instrument",profilData.getProfilInstrument().toString());
                intent.putExtra("genre",profilData.getProfilGenre().toString());
                startActivity(intent);
            }
        });


        return view;
    }
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == Activity.RESULT_OK){
            if (reqCode == 1)
                contactImageView.setImageURI(data.getData());
        }
    }

}
