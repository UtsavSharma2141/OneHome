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
    }
}
