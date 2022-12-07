package com.example.fitmefriend;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The purpose of this class is to hold ALL the code to communicate with Firebase.  This class
 * will connect with Firebase auth and Firebase firestore.  Each class that needs to verify
 * authentication OR access data from the database will reference a variable of this class and
 * call a method of this class to handle the task.  Essentially this class is like a "gopher" that
 * will go and do whatever the other classes want or need it to do.  This allows us to keep all
 * our other classes clean of the firebase code and also avoid having to update firebase code
 * in many places.  This is MUCH more efficient and less error prone.
 */
public class FirebaseHelper {
    public final String TAG = "Denna";
    private static String uid = null;      // var will be updated for currently signed in user
    private FirebaseAuth mAuth;
    private static FirebaseFirestore db;


    public FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void logOutUser() {
        mAuth.signOut();
        this.uid = null;
    }


    public void updateUid(String uid) {
        this.uid = uid;
    }


    public void attachReadDataToUserPants() {
        // This is necessary to avoid the issues we ran into with data displaying before it
        // returned from the asynch method calls
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getUid();
            readDataPants(new FirestoreCallbackPants() {
                @Override
                public void onCallback(ArrayList<Pants> pantsArrayList) {
                    Log.d(TAG, "Inside attachReadDataToUser, onCallback " + UploadActivity.pantsList.toString());
                }
            });
        }
        else {
            Log.d(TAG, "No one logged in");
        }
    }

    public void attachReadDataToUserShirts() {
        // This is necessary to avoid the issues we ran into with data displaying before it
        // returned from the asynch method calls
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getUid();
            readDataShirts(new FirestoreCallbackShirts() {
                @Override
                public void onCallback(ArrayList<Shirts> shirtsArrayList) {
                    Log.d(TAG, "Inside attachReadDataToUser, onCallback " + UploadActivity.shirtsList.toString());
                }
            });
        }
        else {
            Log.d(TAG, "No one logged in");
        }
    }


    public void addUserToFirestore(String name, String newUID) {
        // Create a new user with their name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        // Add a new document with a docID = to the authenticated user's UID
        db.collection("users").document(newUID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, name + "'s user account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding user account", e);
                    }
                });
    }

    public void addDataPants(Pants p) {
        // add Memory m to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        addDataPants(p, new FirestoreCallbackPants() {
            @Override
            public void onCallback(ArrayList<Pants> myList) {
                Log.i(TAG, "Inside addData, onCallback :" + UploadActivity.pantsList.toString());
            }
        });
    }

    public void addDataShirts(Shirts s ) {
        // add Memory m to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        addDataShirts(s, new FirestoreCallbackShirts() {
            @Override
            public void onCallback(ArrayList<Shirts> myList) {
                Log.i(TAG, "Inside addData, onCallback :"  + UploadActivity.shirtsList.toString());
            }
        });
    }


    private void addDataPants(Pants p,  FirestoreCallbackPants firestoreCallbackPants) {
        db.collection("users").document(uid).collection("pantsList")
                .add(p)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Memory that was just added.
                        db.collection("users").document(uid).collection("pantsList").
                                document(documentReference.getId()).update("docID", documentReference.getId());
                        Log.i(TAG, "just added " + p.getpCategory());
                        readDataPants(firestoreCallbackPants);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }

    private void addDataShirts(Shirts s,  FirestoreCallbackShirts firestoreCallbackShirts) {
        db.collection("users").document(uid).collection("shirtsList")
                .add(s)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Memory that was just added.
                        db.collection("users").document(uid).collection("shirtsList").
                                document(documentReference.getId()).update("docID", documentReference.getId());
                        Log.i(TAG, "just added " + s.getCategory());
                        readDataShirts(firestoreCallbackShirts);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }






/* https://www.youtube.com/watch?v=0ofkvm97i0s
This video is good!!!   Basically he talks about what it means for tasks to be asynchronous
and how you can create an interface and then using that interface pass an object of the interface
type from a callback method and access it after the callback method.  It also allows you to delay
certain things from occurring until after the onSuccess is finished.
*/

    private void readDataPants(FirestoreCallbackPants firestoreCallbackPants) {
        if (UploadActivity.pantsList != null) {
            //UploadActivity.pantsList.clear();        // empties the AL so that it can get a fresh copy of data
            db.collection("users").document(uid).collection("myPants")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult()) {
                                    Pants pants = doc.toObject(Pants.class);
                                    UploadActivity.pantsList.add(pants);

                                }

                                Log.i(TAG, "Success reading data: " + UploadActivity.pantsList.toString());
                                firestoreCallbackPants.onCallback(UploadActivity.pantsList);
                            } else {
                                Log.d(TAG, "Error getting documents: " + task.getException());
                            }
                        }
                    });
        }
    }

    private void readDataShirts(FirestoreCallbackShirts firestoreCallbackShirts) {
        if (UploadActivity.shirtsList != null) {
            //UploadActivity.shirtsList.clear();
            db.collection("users").document(uid).collection("myShirts")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult()) {
                                    Shirts shirts = doc.toObject(Shirts.class);
                                    UploadActivity.shirtsList.add(shirts);

                                }

                                    Log.i(TAG, "Success reading data: " + UploadActivity.shirtsList.toString());
                                firestoreCallbackShirts.onCallback(UploadActivity.shirtsList);
                            } else {
                                Log.d(TAG, "Error getting documents: " + task.getException());
                            }
                        }
                    });
        }

    }




    //https://stackoverflow.com/questions/48499310/how-to-return-a-documentsnapshot-as-a-result-of-a-method/48500679#48500679
    public  interface FirestoreCallbackPants {
        void onCallback(ArrayList<Pants> myList) ;



    }

    public interface FirestoreCallbackShirts{
        void onCallback(ArrayList<Shirts> myList) ;


    }


    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        FirebaseHelper.uid = uid;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public static FirebaseFirestore getDb() {
        return db;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }
}

