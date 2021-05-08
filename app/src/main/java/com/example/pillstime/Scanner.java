package com.example.pillstime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Size;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scanner extends AppCompatActivity {
    private ListenableFuture cameraProviderFuters;
    private ExecutorService cameraExecutor;
    private PreviewView previewView;
    private MyImageAnalyzer analyzer;
    DatabaseReference mPostReference;
    String times ;
    String how ;
    String from ;
    String  to ;
    String Number ;
    String phonenumber ;
    String name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        mPostReference = FirebaseDatabase.getInstance().getReference("Medecins");
        previewView = findViewById(R.id.previewview);

        this.getWindow().setFlags(1024,1024);

  cameraExecutor = Executors.newSingleThreadExecutor();
  cameraProviderFuters = ProcessCameraProvider.getInstance(this);
analyzer = new MyImageAnalyzer(getSupportFragmentManager());
  cameraProviderFuters.addListener(new Runnable() {
      @Override
      public void run() {
          try {
              if(ActivityCompat.checkSelfPermission(Scanner.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){

                  ActivityCompat.requestPermissions(Scanner.this,new String[] {Manifest.permission.CAMERA},101);

              }else{
                  ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) cameraProviderFuters.get();
                  bindpreview(processCameraProvider);
              }

          }catch (ExecutionException e){
              e.printStackTrace();
              
              
              
          }catch (InterruptedException e){
              
              e.printStackTrace();
              
          }
      }
  },ContextCompat.getMainExecutor(this));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
   if(requestCode==101 && grantResults.length>0){
       ProcessCameraProvider processCameraProvider = null;
       try {
           processCameraProvider = (ProcessCameraProvider) cameraProviderFuters.get();
       } catch (ExecutionException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       bindpreview(processCameraProvider);




   }

    }

    private void bindpreview(ProcessCameraProvider processCameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        ImageCapture imagecapture = new ImageCapture.Builder().build();
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(1280,720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();
        imageAnalysis.setAnalyzer(cameraExecutor,analyzer);
        processCameraProvider.unbindAll();
        processCameraProvider.bindToLifecycle(this,cameraSelector,preview,imagecapture,imageAnalysis);

    }
    public  class MyImageAnalyzer implements ImageAnalysis.Analyzer{

        private FragmentManager fragmentManager;
        private Bottomdialog bd;

        public MyImageAnalyzer(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
            this.bd = new Bottomdialog();
        }


        @Override
        public void analyze(@NonNull @NotNull ImageProxy image) {
        scanbarcode(image);
        }

        private void scanbarcode(ImageProxy image) {
            @SuppressLint("UnsafeOptInUsageError") Image image1 = image.getImage();
            assert image1 !=null;
            InputImage inputImage = InputImage.fromMediaImage(image1,image.getImageInfo().getRotationDegrees());
            BarcodeScannerOptions options =
                    new BarcodeScannerOptions.Builder()
                            .setBarcodeFormats(
                                    Barcode.FORMAT_QR_CODE,
                                    Barcode.FORMAT_AZTEC)
                            .build();
            BarcodeScanner scanner = BarcodeScanning.getClient();
            Task<List<Barcode>> result = scanner.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                        @Override
                        public void onSuccess(List<Barcode> barcodes) {
                            readerBarcodeData(barcodes);
                            // Task completed successfully
                            // ...
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Task failed with an exception
                            // ...
                        }
                    }).addOnCompleteListener(new OnCompleteListener<List<Barcode>>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<List<Barcode>> task) {
                            image.close();
                        }
                    });
        }


    private void readerBarcodeData(List<Barcode> barcodes) {
        for (Barcode barcode: barcodes) {
            Rect bounds = barcode.getBoundingBox();
            Point[] corners = barcode.getCornerPoints();

            String rawValue = barcode.getRawValue();

            int valueType = barcode.getValueType();
            // See API reference for complete list of supported types
            switch (valueType) {
                case Barcode.TYPE_WIFI:
                    String ssid = barcode.getWifi().getSsid();
                    String password = barcode.getWifi().getPassword();
                    int type = barcode.getWifi().getEncryptionType();
                    break;
                case Barcode.TYPE_TEXT:
                    if(!bd.isAdded()){
                        bd.show(fragmentManager,"");
                    }
                    bd.fetchurl11(name,Number,times);
                    String id = barcode.getDisplayValue();
                    mPostReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Medecin notif = new Medecin();
                            notif = snapshot.child(id).getValue(Medecin.class);
                             String name = String.valueOf(notif.getName());
                               String times = String.valueOf(notif.getTimes());
                                String how = String.valueOf(notif.getHow());
                                String from = String.valueOf(notif.getFrom());
                                String  to = String.valueOf(notif.getTo());
                                String Number = String.valueOf(notif.getPills());
                                String phonenumber =  String.valueOf(notif.getPhone_number());





                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            Toast.makeText(Scanner.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        }
                    });





                    break;
            }
        }
    }


    }
}