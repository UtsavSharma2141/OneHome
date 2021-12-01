package ca.thecollective.it.onehome;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingBar {

    Activity activity;
    AlertDialog dialog;

    LoadingBar (Activity thisactivity){
        activity = thisactivity;
    }

    void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progressbar,null));
        dialog = builder.create();
        dialog.show();
    }

    public void dismissbar() {
        dialog.dismiss();
    }
}
