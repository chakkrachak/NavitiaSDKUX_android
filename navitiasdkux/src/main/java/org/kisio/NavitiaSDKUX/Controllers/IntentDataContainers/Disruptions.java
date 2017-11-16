package org.kisio.NavitiaSDKUX.Controllers.IntentDataContainers;

import android.os.Parcel;
import android.os.Parcelable;

import org.kisio.NavitiaSDK.models.Disruption;

import java.util.List;
import java.util.Objects;

public class Disruptions implements Parcelable {
    private List<Disruption> disruptions;

    public List<Disruption> getDisruptions() {
        return disruptions;
    }

    public void setDisruptions(List<Disruption> disruptions) {
        disruptions = disruptions;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeValue(disruptions);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Disruptions d = (Disruptions) o;
        return Objects.equals(this.disruptions, d.disruptions);
    }

    public Disruptions() {
        super();
    }

    Disruptions(Parcel in) {
        disruptions = in.readArrayList(null);
    }

    public static final Parcelable.Creator<Disruptions> CREATOR = new Parcelable.Creator<Disruptions>() {
        public Disruptions createFromParcel(Parcel in) {
            return new Disruptions(in);
        }
        public Disruptions[] newArray(int size) {
            return new Disruptions[size];
        }
    };
}
