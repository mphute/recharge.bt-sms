package edu.vit.eddv2;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;


public class RechargeFragment extends Fragment implements View.OnClickListener {
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    Button btn_100;
    Button btn_200;
    Button btn_300;
    Button btn_custom;
    EditText units_custom;
    BroadcastReceiver receiver = null;
    String address = null , name=null;
    String usernumber= "9890919400";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_recharge, container, false);
        btn_100 = (Button) v.findViewById(R.id.btn_100);
        btn_200 = (Button) v.findViewById(R.id.btn_200);
        btn_300 = (Button) v.findViewById(R.id.btn_300);
        btn_custom = (Button) v.findViewById(R.id.btn_custom);
        units_custom = (EditText) v.findViewById(R.id.editText_custom);


        btn_100.setOnClickListener(this);
        btn_200.setOnClickListener(this);
        btn_300.setOnClickListener(this);
        btn_custom.setOnClickListener(this);


        return v;
    }


    public void onClick(View v) {
        String units = units_custom.getText().toString();
        if(checkPermission((Manifest.permission.SEND_SMS))) {
            switch(v.getId()) {
                case R.id.btn_100:
                    units = "100";
                    break;
                case R.id.btn_200:
                    units = "200";
                    break;
                case R.id.btn_300:
                    units = "300";
                    break;
            }

            String sysunits="#"+units+"*";
            SmsManager smsManager = SmsManager.getDefault();
           // smsManager.sendTextMessage(spnumber,null,sysunits,null,null);
            smsManager.sendTextMessage(usernumber,null,units,null,null);
            Toast.makeText(getContext(),"Message Sent", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(),"Recharge done", Toast.LENGTH_SHORT).show();
        }
        else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
            Toast.makeText(getContext(),"Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean checkPermission (String permission){

        int check = ContextCompat.checkSelfPermission(getActivity(),permission);
        return(check == PackageManager.PERMISSION_GRANTED);

    }





}

