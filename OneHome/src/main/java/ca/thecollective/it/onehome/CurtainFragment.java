
/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;

public class CurtainFragment extends Fragment {

    Button OpenButton;
    Button CloseButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curtain, container, false);

        Button OpenButton = (Button) view.findViewById(R.id.OpenButton);
        OpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getResources().getString(R.string.curtain_open), Toast.LENGTH_SHORT).show();

            }
        });
        Button CloseButton = (Button) view.findViewById(R.id.CloseButton);
        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getResources().getString(R.string.curtain_close), Toast.LENGTH_SHORT).show();
            }
        });

        return view;


    }

}

