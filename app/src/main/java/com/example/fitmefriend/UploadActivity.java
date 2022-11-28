package com.example.fitmefriend;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fitmefriend.databinding.ActivityUploadBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UploadActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    String spinnerSelectedText = "none";
    ActivityUploadBinding binding;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    TextView spinnerCategory;



    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        binding.uploadImageButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        }));

        spinner = findViewById(R.id.mySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list,
                getResources().getStringArray(R.array.memoryRatings));

        // this attaches my custom row design (how I want each row to look)
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerCategory = findViewById(R.id.priorityChosen);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    private void uploadImage() {

        // Everyone needs this part below this comment
        Log.d("Denna", "Completed ur mom");
        String imageStringUri = imageUri.toString();
        if(spinnerSelectedText.equals("Shirt")) {

            Shirts m = new Shirts("Shirt", imageStringUri);
            LogInActivity.firebaseHelper.addDataShirts(m);
        }else{
            Pants p = new Pants("Pant", imageStringUri);
            LogInActivity.firebaseHelper.addDataPants(p);
        }

        // Lets user know if file is being uploaded
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File...");
        progressDialog.show();

        // String in the form of year, month, day, hours, mins, and seconds
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName);

        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // don't want image in imagview as the image has been updated
                        binding.firebaseimage.setImageURI(null);
                        Toast.makeText(UploadActivity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                        // Tell the user if successfully uploaded
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Dismiss progress if failed
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        Toast.makeText(UploadActivity.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if selectImage selected an image
        if (requestCode == 100 && data != null) {
            imageUri = data.getData();
            binding.firebaseimage.setImageURI(imageUri);

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedText = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinnerSelectedText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
