package com.example.pk.countriescatalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CountryModel implements Parcelable{
    public String name;
    public String capital;
    public String region;
    public String subregion;
    public double area;
    public ArrayList<String> languages;

    public CountryModel(String name, String capital, String region, String subregion, double area, ArrayList<String> languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.area = area;
        this.languages = languages;
    }

    protected CountryModel(Parcel in) {
        name = in.readString();
        capital = in.readString();
        region = in.readString();
        subregion = in.readString();
        area = in.readDouble();
        languages = in.createStringArrayList();
    }

    public static final Creator<CountryModel> CREATOR = new Creator<CountryModel>() {
        @Override
        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel(in);
        }

        @Override
        public CountryModel[] newArray(int size) {
            return new CountryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(capital);
        parcel.writeString(region);
        parcel.writeString(subregion);
        parcel.writeDouble(area);
        parcel.writeStringList(languages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryModel that = (CountryModel) o;

        if (Double.compare(that.area, area) != 0) return false;
        if (!name.equals(that.name)) return false;
        if (!capital.equals(that.capital)) return false;
        if (!region.equals(that.region)) return false;
        if (!subregion.equals(that.subregion)) return false;
        return languages.equals(that.languages);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + capital.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + subregion.hashCode();
        temp = Double.doubleToLongBits(area);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + languages.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", area=" + area +
                ", languages=" + languages.toString() +
                '}';
    }
}
