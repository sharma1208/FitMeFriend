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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UploadActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String spinnerSelectedText = "none";
    ActivityUploadBinding binding;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    TextView spinnerCategory;
    String uriToAdd = "none";
    public static ArrayList<Pants> pantsList = new ArrayList<>();;
    public static ArrayList<Shirts> shirtsList =  new ArrayList<>();;


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
                uploadImageButtonClicked(v);
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


    public void uploadImageButtonClicked(View view) {

        // Everyone needs this part below this comment
        Log.d("Denna", "Completed ur mom");
        String imageStringUri = imageUri.toString();
        if (spinnerSelectedText.equals("Shirt")) {
            Shirts m = new Shirts("Shirt", imageStringUri);
            shirtsList.add(m);

            m.setImageResourceId(uriToAdd);     // testing w denna
            //LogInActivity.firebaseHelper.addDataShirts(m);
            uploadImage(m);


        } else {
            Pants p = new Pants("Pant", imageStringUri);
            pantsList.add(p);
            //LogInActivity.firebaseHelper.addDataPants(p);
            p.setpImageResourceId(uriToAdd);
            uploadImage(p);
            Log.i("denna", String.valueOf(pantsList));
        }




    }



    public void uploadImage(Pants p) {
        imageUri = uploadImage(new StorageCallback() {
            @Override
            public void onCallback(Uri myUri) {
                Log.d("Denna", "inside onCallback: " + myUri.toString());
                imageUri = myUri;
                p.setpImageResourceId(imageUri.toString());
                LogInActivity.firebaseHelper.addDataPants(p);


            }
        });
    }

    public void uploadImage(Shirts s) {
        imageUri = uploadImage(new StorageCallback() {
            @Override
            public void onCallback(Uri myUri) {
                Log.d("Denna", "inside onCallback: " + myUri.toString());
                imageUri = myUri;
                s.setImageResourceId(imageUri.toString());
                LogInActivity.firebaseHelper.addDataShirts(s);
            }
        });
    }



    private Uri uploadImage(StorageCallback storageCallback) {

        final Uri[] uriToReturn = new Uri[1];

        // Lets user know if file is being uploaded
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File...");
        progressDialog.show();

        // String in the form of year, month, day, hours, mins, and seconds
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/" + fileName);
        UploadTask uploadTask = (UploadTask) storageReference.putFile(imageUri);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // don't want image in imagview as the image has been updated
                        binding.firebaseimage.setImageURI(null);
                        Toast.makeText(UploadActivity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                        // Tell the user if successfully uploaded
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Dismiss progress if failed
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(UploadActivity.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                });

        // After the image has been uploaded, we can access the Task that was done and
        // get the download Url.
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            final Uri uriReturn = null;

            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                } else {
                    // Continue with the task to get the download URL
                    // return null;
                    Log.d("Denna", "is this line reached? " + storageReference.getDownloadUrl());
                    Task<Uri> testUrl = storageReference.getDownloadUrl();

                    return storageReference.getDownloadUrl();
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    //   URL downloadURL = task.getResult();

                    uriToReturn[0] = downloadUri;
                    Log.d("Denna", "downloadUri I think is (NEW 9:04 pm): " + downloadUri);
                    if (downloadUri instanceof Uri) {
                        Log.d("Denna", "downloadUri is a URI");
                    } else {
                        Log.d("Denna", "downloadUri is NOT a URI");
                    }
                    storageCallback.onCallback(downloadUri);

                } else {
                    // Handle failures
                    // ...
                }
            }
        });
        return uriToReturn[0];

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
            uriToAdd = imageUri.toString();
            Log.i("Denna", "Uri:" + imageUri);
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


    // TRY this interface to see if can make the add method call pause until done getting the uri
    public interface StorageCallback {
        void onCallback(Uri myUri);
    }
































}
