package com.example.fitmefriend;

import android.os.Parcel;
import android.os.Parcelable;

public class Pants extends Clothes implements Parcelable {
    private String pColor;
    private String pCategory;
    private  int pImageResourceId;
    private String pdocId;

    public Pants() {
        this.pColor = "";
        this.pCategory = "";
        this.pImageResourceId = 0;
        this.pdocId= "No doc Id";
    }

    public Pants(String pColor, String pCategory, int pImageResourceId, String pdocId) {
        this.pColor = pColor;
        this.pCategory = pCategory;
        this.pImageResourceId = pImageResourceId;
        this.pdocId = "No doc Id";
    }



    /** This is a "constructor" of sorts that is needed with the Parceable interface to
     * tell the intent how to create a Memory object when it is received from the intent
     * basically it is setting each instance variable as a String or Int
     *
     * MAKE SURE THE ORDER OF THESE VARS IS CONSISTENT WITH ALL CONSTRUCTOR TYPE METHODS
     * @param parcel    the parcel that is received from the intent
     */

    public Pants(Parcel parcel) {
       pColor = parcel.readString();
       pCategory = parcel.readString();
       pImageResourceId = parcel.readInt();
       pdocId = parcel.readString();
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
        dest.writeString(pColor);
        dest.writeString(pCategory);
        dest.writeInt(pImageResourceId);
        dest.writeString(pdocId);



    }


    // this code is needed for the Memory class to work with Parcelable
    public static final Parcelable.Creator<Pants> CREATOR = new
            Parcelable.Creator<Pants>() {

                @Override
                public Pants createFromParcel(Parcel parcel) {
                    return new Pants(parcel);
                }

                @Override
                public Pants[] newArray(int size) {
                    return new Pants[0];
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




    public String getpColor() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public int getpImageResourceId() {
        return pImageResourceId;
    }

    public void setpImageResourceId(int pImageResourceId) {
        this.pImageResourceId = pImageResourceId;
    }
}
