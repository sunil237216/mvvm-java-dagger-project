package com.example.test.mvvmsampleapp.view.ui.main;


import android.Manifest;
import android.content.pm.PackageManager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.databinding.ScanFragmentBinding;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

/**
 * Created by sunil.jadhav on 11/13/2018.
 */

public class ScanFragment extends Fragment {


    SurfaceView surfaceView;
   EditText txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;

    private ScanFragmentBinding  binding;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.scan_fragment, container, false);
        initViews( binding.getRoot());
        // Create and set the adapter for the RecyclerView.

        return binding.getRoot();


    }

    private void initViews(View view) {
        txtBarcodeValue = (EditText) view.findViewById(R.id.txtBarcodeValue);
        surfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);
        btnAction = (Button) view.findViewById(R.id.btnAction);


        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (intentData.length() > 0) {

                    if(((MainActivity)getActivity()).getPhase() == 1) {
                        ((MainActivity) getActivity()).setTSN(intentData);
                        ((MainActivity)getActivity()).show(2);
                    }
                    else if(((MainActivity)getActivity()).getPhase() == 2){
                        String[] arrOfStr = intentData.split(":");
                        if(arrOfStr.length > 0 ) {
                            String modelno = arrOfStr[1];
                            ((MainActivity) getActivity()).setTSN(arrOfStr[arrOfStr.length - 1]);
                            ((MainActivity) getActivity()).setModel(modelno.substring(8, 13));
                            ((MainActivity) getActivity()).show(2);
                        }
                    }
                    else if(((MainActivity)getActivity()).getPhase() == 3){
                        String[] arrOfStr = intentData.split(":");
                        if(arrOfStr.length > 0 ) {
                            String modelno = arrOfStr[1];
                            ((MainActivity) getActivity()).setTSN(arrOfStr[arrOfStr.length - 1]);
                            ((MainActivity) getActivity()).setModel(modelno.substring(8, 13));
                            ((MainActivity) getActivity()).show(3);
                        }
                    }

                   // if (isEmail)
                       // startActivity(new Intent(ScannedBarcodeActivity.this, EmailActivity.class).putExtra("email_address", intentData));
                   // else {
                       // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
                  //  }
                }

            }
        });
    }


    private void initialiseDetectorsAndSources() {

        Toast.makeText(getActivity(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        barcodeDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getActivity(), barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new
                            String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getActivity(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {


                    txtBarcodeValue.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).email != null) {
                                txtBarcodeValue.removeCallbacks(null);
                                intentData = barcodes.valueAt(0).email.address;
                                txtBarcodeValue.setText(intentData);


                                isEmail = true;
                                btnAction.setText("ADD CONTENT TO THE MAIL");
                            } else {
                                isEmail = false;
                                btnAction.setText("CONFIRM");
                                intentData = barcodes.valueAt(0).displayValue;
                                txtBarcodeValue.setText(intentData);

                            }
                        }
                    });

                }
            }
        });
    }


    @Override
    public  void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }


}
