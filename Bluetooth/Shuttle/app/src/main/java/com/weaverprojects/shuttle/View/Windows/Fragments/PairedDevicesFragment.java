package com.weaverprojects.shuttle.View.Windows.Fragments;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.weaverprojects.shuttle.R;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Keith on 2015-10-21.
 */
public class PairedDevicesFragment extends Fragment{
    public final String TAG = "Shuttle_PairedDevicesFragment_";

    protected Activity mActivity;
    protected ArrayAdapter mArrayAdapter;
    protected ArrayList<String> devicesList;

    public static BluetoothAdapter mBluetoothAdapter;
    int REQUEST_ENABLE_BT = 1;


    protected ListView mainListView;
    protected TextView noDevicesTextView;

    @Override
    public void onAttach(Activity act)
    {
        super.onAttach(act);
        mActivity = act;
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paired_devices, container, false);
        declareUI(view);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(mBluetoothAdapter == null){
            Toast.makeText(mActivity, "Bt not supported", Toast.LENGTH_SHORT).show();
        }else {

            //TURN ON BLUETOOTH
            if (!mBluetoothAdapter.isEnabled()) {
                Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
            } else {
                Toast.makeText(mActivity, "Bluetooth is already on", Toast.LENGTH_SHORT).show();

                mBluetoothAdapter.startDiscovery();

                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                Log.v(TAG, "Paired Devices:" + pairedDevices.size());

                devicesList = new ArrayList<>();

                mArrayAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_list_item_1, devicesList);
                mainListView.setAdapter(mArrayAdapter);

                mBluetoothAdapter.startDiscovery();

                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                mActivity.registerReceiver(mPairReceiver, filter); // Don't forget to unregister during onDestroy

                Intent discoverableIntent = new
                        Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(discoverableIntent);

            }
        }



        return view;
    }
    private final BroadcastReceiver mPairReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state        = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                final int prevState    = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);

                if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                    Toast.makeText(mActivity, "Paired", Toast.LENGTH_LONG).show();
                    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        // Add the name and address to an array adapter to show in a ListView
                        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                        //mark(6);
                        mArrayAdapter.notifyDataSetChanged();

                    }
                } else if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED){
                    //Toast.makeText(mContext, "Unpaired", Toast.LENGTH_LONG).show();
                }
            }

        }
    };
    protected void declareUI(View view){
        mainListView = (ListView) view.findViewById(R.id.mainListView);
        noDevicesTextView = (TextView) view.findViewById(R.id.noDevicesTextView);
    }
}
