package com.example.fitmefriend;

import android.os.Parcel;
import android.os.Parcelable;

public class Outfits implements Parcelable {
    public Shirts s;
    public Pants p;

    public Outfits (Shirts s, Pants p){
        this.s = s;
        this.p = p;
    }

    protected Outfits(Parcel in) {
        s = in.readParcelable(Shirts.class.getClassLoader());
        p = in.readParcelable(Pants.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(s, flags);
        dest.writeParcelable(p, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Outfits> CREATOR = new Creator<Outfits>() {
        @Override
        public Outfits createFromParcel(Parcel in) {
            return new Outfits(in);
        }

        @Override
        public Outfits[] newArray(int size) {
            return new Outfits[size];
        }
    };

    public Shirts getS() {
        return s;
    }

    public void setS(Shirts s) {
        this.s = s;
    }

    public Pants getP() {
        return p;
    }

    public void setP(Pants p) {
        this.p = p;
    }
}

