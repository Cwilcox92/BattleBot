package com.example.battlebot_programming_assignment_6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HostInfoDialog extends AppCompatDialogFragment {
    private EditText hostIpEnt;
    private EditText portEnt;
    private HostInfoListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v= inflater.inflate(R.layout.host_dialog, null);

        builder.setView(v)
                .setTitle("Enter Host Information")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String host = String.valueOf(hostIpEnt.getText());
                        String port= String.valueOf(portEnt.getText());
                        if(host.equals(" ") || port.equals(" "))
                        {
                            Toast.makeText(getContext(), "Please Enter The Values Above", Toast.LENGTH_SHORT).show();
                        }
                        listener.applyTexts(host,port);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        //These are sent to the main activity for now until i get teh server shit figured out
        hostIpEnt= v.findViewById(R.id.editTextIP);
        portEnt= v.findViewById(R.id.editTextPort);
        return  builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener= (HostInfoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "must implement HostInfoListener");
        }

    }

    public interface HostInfoListener
    {
        void applyTexts(String host, String port);
    }


}
