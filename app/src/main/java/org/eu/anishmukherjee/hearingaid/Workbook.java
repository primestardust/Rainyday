package org.eu.anishmukherjee.hearingaid;

import android.os.Parcel;
import android.os.Parcelable;

public class Workbook implements Parcelable {
    String wearHour;
    String wearMinute;
    String removeHour;
    String removeMinute;
    boolean situations[];
    String isComfortable;
    String timeFrame;
    String satisfaction;
    public Workbook(String wearHour, String wearMinute, String removeHour, String removeMinute, boolean listen_situations[],
                    String isComfortable, String timeFrame, String satisfaction) {
        this.wearHour = wearHour;
        this.wearMinute = wearMinute;
        this.removeHour = removeHour;
        this.removeMinute = removeMinute;
        this.situations = listen_situations;
        this.isComfortable = isComfortable;
        this.timeFrame = timeFrame;
        this.satisfaction = satisfaction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wearHour);
        dest.writeString(this.wearMinute);
        dest.writeString(this.removeHour);
        dest.writeString(this.removeMinute);
        dest.writeBooleanArray(this.situations);
        dest.writeString(this.isComfortable);
        dest.writeString(this.timeFrame);
        dest.writeString(this.satisfaction);
    }

    protected Workbook(Parcel in) {
        this.wearHour = in.readString();
        this.wearMinute = in.readString();
        this.removeHour = in.readString();
        this.removeMinute = in.readString();
        this.situations = in.createBooleanArray();
        this.isComfortable = in.readString();
        this.timeFrame = in.readString();
        this.satisfaction = in.readString();
    }

    public static final Creator<Workbook> CREATOR = new Creator<Workbook>() {
        @Override
        public Workbook createFromParcel(Parcel source) {
            return new Workbook(source);
        }

        @Override
        public Workbook[] newArray(int size) {
            return new Workbook[size];
        }
    };
}
