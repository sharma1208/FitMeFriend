package com.example.fitmefriend;

import android.os.Parcel;
import android.os.Parcelable;

public class Shirts implements Parcelable{
    private String sCategory;
    private  String sImageResourceId;
    private String sdocId;

    public Shirts() {

        sCategory = "";
        sdocId ="No doc Id";
    }



    public Shirts(String category, String imageResourceId) {
        this.sCategory = category;
        this.sImageResourceId = imageResourceId;
        this.sdocId = sdocId;
    }


    public Shirts(String sImageResourceId) {
        this.sImageResourceId = sImageResourceId;
    }

    /** This is a "constructor" of sorts that is needed with the Parceable interface to
     * tell the intent how to create a Memory object when it is received from the intent
     * basically it is setting each instance variable as a String or Int
     *
     * MAKE SURE THE ORDER OF THESE VARS IS CONSISTENT WITH ALL CONSTRUCTOR TYPE METHODS
     * @param parcel    the parcel that is received from the intent
     */

    public Shirts(Parcel parcel) {
        sCategory = parcel.readString();
        sImageResourceId = parcel.readString();
        sdocId = parcel.readString();
    }


    /**
     * This is what is used when we send the Memory object through an intent
     * It is also a method that is part of the Parceable interface and is needed
     * to set up the object that is being sent.  Then, when it is received, the
     * other Memory constructor that accepts a Parcel reference can "unpack it"
     *
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sCategory);
        dest.writeString(sImageResourceId);
        dest.writeString(sdocId);

    }


    // this code is needed for the Memory class to work with Parcelable
    public static final Parcelable.Creator<Shirts> CREATOR = new
            Parcelable.Creator<Shirts>() {

                @Override
                public Shirts createFromParcel(Parcel parcel) {
                    return new Shirts(parcel);
                }

                @Override
                public Shirts[] newArray(int size) {
                    return new Shirts[0];
                }
            };



    /**
     * This method is required for the Parceable interface.  As of now, this method
     * is in the default state and doesn't really do anything.
     *
     * If your Parcelable class will have child classes, you'll need to
     * take some extra care with the describeContents() method. This would
     * let you identify the specific child class that should be created by
     * the Parcelable.Creator. You can read more about how this works on
     *  Stack Overflow with this link.
     *           https://stackoverflow.com/questions/4778834/purpose-of-describecontents-of-parcelable-interface
     * @return
     */

    @Override
    public int describeContents() {
        return 0;
    }



    public String getCategory() {
        return sCategory;
    }

    public void setCategory(String category) {
        this.sCategory = category;
    }

    public String getImageResourceId() {
        return sImageResourceId;
    }

    public void setImageResourceId(String imageResourceId) {
        this.sImageResourceId = imageResourceId;
    }
}
