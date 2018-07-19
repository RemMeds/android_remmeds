package com.example.jeux.remmeds.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.Profil;

import java.text.DecimalFormat;
import java.util.Calendar;

public class FragmentProfil extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_profil, container, false);


    }

    private void getFormattedHour(final EditText whichField) {
        //Launch a time picker instead of keyboard on clock field
        whichField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar myCurrentTime = Calendar.getInstance();
                int hour = myCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = myCurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        DecimalFormat addZero = new DecimalFormat("00");
                        whichField.setText(addZero.format(selectedHour) + ":" + addZero.format(selectedMinute)); //NOSONAR
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Sélectionner heure");
                mTimePicker.show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        final String userID = MainActivity.getUserID();
        getActivity().setTitle("Profil");
        final Profil user = new Profil();
        final EditText fieldLastnameProfil = view.findViewById(R.id.nom_edittext_layout_profil);
        fieldLastnameProfil.setText(user.getLastname());
        final EditText fieldFirstnameProfil = view.findViewById(R.id.prenom_edittext_layout_profil);
        fieldFirstnameProfil.setText(user.getFirstname());
        final EditText fieldBreakfastProfil = view.findViewById(R.id.breakfast_editText_layout_profil);
        fieldBreakfastProfil.setText(user.getBreakfastHour());
        final EditText fieldLunchProfil = view.findViewById(R.id.lunch_edittext_layout_profil);
        fieldLunchProfil.setText(user.getLunchHour());
        final EditText fieldDinnerProfil = view.findViewById(R.id.dinner_edittext_layout_profil);
        fieldDinnerProfil.setText(user.getDinnerHour());
        final EditText fieldBedtimeProfil = view.findViewById(R.id.bedtime_edittext_layout_profil);
        fieldBedtimeProfil.setText(user.getBedHour());

        getFormattedHour(fieldBreakfastProfil);
        getFormattedHour(fieldLunchProfil);
        getFormattedHour(fieldDinnerProfil);
        getFormattedHour(fieldBedtimeProfil);

        Button enregistrerButton = view.findViewById(R.id.enregistrer_button_layout_profil);
        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFirstname = fieldFirstnameProfil.getText().toString();
                String newLastname = fieldLastnameProfil.getText().toString();
                String newBf = fieldBreakfastProfil.getText().toString();
                String newLun = fieldLunchProfil.getText().toString();
                String newDin = fieldDinnerProfil.getText().toString();
                String newBed = fieldBedtimeProfil.getText().toString();
                String mail = user.getMail();
                String urlUpdateProfil = "http://212.73.217.202:15020/user/update_account/" + userID + "&" + mail + "&" + newLastname + "&" + newFirstname + "&" + newBf + "&" + newLun + "&" + newDin + "&" + newBed;
                MainActivity.postDoInBackground(urlUpdateProfil);
                Toast.makeText(getActivity(), "Profil mis à jour", Toast.LENGTH_SHORT).show();
            }
        });
        Button changerMdpButton = view.findViewById(R.id.password_button_layout_profil);
        changerMdpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder passwordChange = new AlertDialog.Builder(getActivity());
                @SuppressLint("InflateParams") View myView = getLayoutInflater().inflate(R.layout.dialog_new_password, null);
                final EditText oldPassword = myView.findViewById(R.id.actuel_edittext_ayout_profil);
                final EditText newPassword = myView.findViewById(R.id.nouveau_edittext_layout_profil);
                final EditText newPasswordConfirmation = myView.findViewById(R.id.confnouveau_edittext_layout_profil);
                Button confirmation = myView.findViewById(R.id.confirmer_button_layout_profil);
                Button annuler = myView.findViewById(R.id.annuler_button_layout_profil);
                passwordChange.setView(myView);
                final AlertDialog dialog = passwordChange.create();
                dialog.show();
                confirmation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (oldPassword.getText().toString().equals(user.getPassword())) {
                            if (newPassword.getText().toString().equals(newPasswordConfirmation.getText().toString())) {
                                String strOldPassword = oldPassword.getText().toString();
                                String strNewPassword = newPassword.getText().toString();
                                String changePasswordURL = "http://212.73.217.202:15020/user/password_update/" + userID + "&" + strOldPassword + "&" + strNewPassword;
                                MainActivity.postDoInBackground(changePasswordURL);
                                Toast.makeText(getActivity(), "Mot de passe changé avec succès", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            } else {
                                Toast.makeText(getActivity(), "Le nouveau mot de passe n'est pas identique à sa confirmation", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Votre mot de passe actuel est incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });

    }
}