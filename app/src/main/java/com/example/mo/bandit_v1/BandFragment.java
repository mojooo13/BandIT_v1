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
public class BandFragment extends Fragment {

    ImageView bandImageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.band, container, false);

        bandImageView = (ImageView) view.findViewById(R.id.emptybandImageView);
        bandImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent1 , "Select Band Picture"),1);

            }
        });

        BandData bandData = new BandData("ACDC", "Moritz Hauch", "Rock N Roll", "High Way To Hell", "Guitar");

        TextView bandName1BandTextView = (TextView) view.findViewById(R.id.bandname1BandTextView);
        bandName1BandTextView.setText(bandData.getBandName());

        Button editBandBandButton = (Button) view.findViewById(R.id.editBandBandButton);
        editBandBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),EditBandActivity.class));
            }
        });
        Button createBandButton = (Button) view.findViewById(R.id.createBandBandButton);
        createBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CreateBandActivity.class));
            }
        });

        return view;
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == Activity.RESULT_OK){
            if (reqCode == 1)
                bandImageView.setImageURI(data.getData());
        }
    }
}
