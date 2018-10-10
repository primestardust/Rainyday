package org.eu.anishmukherjee.hearingaid;

import android.os.Parcel;
import android.os.Parcelable;

public class Patient implements Parcelable {
    String name;
    String id;
    String password;
    String hearingAidDetails;
    String appointmentDate;
    String audiologistName;
    String snLeftHearingAid;
    String snRightHearingAid;
    String lossType;
    int progress;
    Workbook workbooks[];

    public Patient() {
        this.name = "";
        this.id = "";
        this.password = "";
        this.hearingAidDetails = "";
        this.appointmentDate = "";
        this.audiologistName = "";
        this.snLeftHearingAid = "";
        this.snRightHearingAid = "";
        this.lossType = "";
        this.progress = 0;
        workbooks = new Workbook[30];
    }

    public Patient(String name, String id, String password, String hearingAidDetails, String appointmentDate,
                   String audiologistName, String snLeftHearingAid, String snRightHearingAid, String lossType) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.hearingAidDetails = hearingAidDetails;
        this.appointmentDate = appointmentDate;
        this.audiologistName = audiologistName;
        this.snLeftHearingAid = snLeftHearingAid;
        this.snRightHearingAid = snRightHearingAid;
        this.lossType = lossType;
        this.progress = 0;
        workbooks = new Workbook[30];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.password);
        dest.writeString(this.hearingAidDetails);
        dest.writeString(this.appointmentDate);
        dest.writeString(this.audiologistName);
        dest.writeString(this.snLeftHearingAid);
        dest.writeString(this.snRightHearingAid);
        dest.writeString(this.lossType);
        dest.writeInt(this.progress);
        dest.writeTypedArray(this.workbooks, flags);
    }

    protected Patient(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.password = in.readString();
        this.hearingAidDetails = in.readString();
        this.appointmentDate = in.readString();
        this.audiologistName = in.readString();
        this.snLeftHearingAid = in.readString();
        this.snRightHearingAid = in.readString();
        this.lossType = in.readString();
        this.progress = in.readInt();
        this.workbooks = in.createTypedArray(Workbook.CREATOR);
    }

    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel source) {
            return new Patient(source);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}

/*
public class Patient implements Parcelable {
    String name;
    String id;
    String password;
    String hearingAidDetails;
    String appointmentDate;
    String audiologistName;
    String snLeftHearingAid;
    String snRightHearingAid;
    String lossType;
    int progress;

    public Patient(String name, String id, String password, String hearingAidDetails, String appointmentDate,
                   String audiologistName, String snLeftHearingAid, String snRightHearingAid, String lossType) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.hearingAidDetails = hearingAidDetails;
        this.appointmentDate = appointmentDate;
        this.audiologistName = audiologistName;
        this.snLeftHearingAid = snLeftHearingAid;
        this.snRightHearingAid = snRightHearingAid;
        this.lossType = lossType;
        this.progress = 0;
    }


    protected Patient(Parcel in) {
        name = in.readString();
        id = in.readString();
        password = in.readString();
        hearingAidDetails = in.readString();
        appointmentDate = in.readString();
        audiologistName = in.readString();
        snLeftHearingAid = in.readString();
        snRightHearingAid = in.readString();
        lossType = in.readString();
        progress = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(password);
        dest.writeString(hearingAidDetails);
        dest.writeString(appointmentDate);
        dest.writeString(audiologistName);
        dest.writeString(snLeftHearingAid);
        dest.writeString(snRightHearingAid);
        dest.writeString(lossType);
        dest.writeInt(progress);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}
*/