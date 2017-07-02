package com.serioscompany.weather.data.response.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 19:42.
 */
public class AutoCompleteResult implements Comparable<AutoCompleteResult>, Parcelable {

  public static final Parcelable.Creator<AutoCompleteResult> CREATOR = new Parcelable.Creator<AutoCompleteResult>() {
    @Override
    public AutoCompleteResult createFromParcel(final Parcel source) {
      return new AutoCompleteResult(source);
    }

    @Override
    public AutoCompleteResult[] newArray(final int size) {
      return new AutoCompleteResult[size];
    }
  };

  private static final String C = "RU";
  private final String name;
  private final String type;
  private final String c;
  private final String zmw;
  private final String tz;
  private final String tzs;
  private final String l;
  private final String ll;
  private final String latitude;
  private final String longitude;

  @JsonCreator
  public AutoCompleteResult(@JsonProperty("name") final String name,
                            @JsonProperty("type") final String type,
                            @JsonProperty("c") final String c,
                            @JsonProperty("zmw") final String zmw,
                            @JsonProperty("tz") final String tz,
                            @JsonProperty("tzs") final String tzs,
                            @JsonProperty("l") final String l,
                            @JsonProperty("ll") final String ll,
                            @JsonProperty("lat") final String latitude,
                            @JsonProperty("lon") final String longitude) {
    this.name = name;
    this.type = type;
    this.c = c;
    this.zmw = zmw;
    this.tz = tz;
    this.tzs = tzs;
    this.l = l;
    this.ll = ll;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  protected AutoCompleteResult(Parcel in) {
    this.name = in.readString();
    this.type = in.readString();
    this.c = in.readString();
    this.zmw = in.readString();
    this.tz = in.readString();
    this.tzs = in.readString();
    this.l = in.readString();
    this.ll = in.readString();
    this.latitude = in.readString();
    this.longitude = in.readString();
  }

  @Override
  public int compareTo(@NonNull final AutoCompleteResult o) {
    if (C.equals(o.getC())) {
      return C.equals(getC()) ? getName().compareTo(o.getName()) : 1;
    } else {
      return C.equals(getC()) ? -1 : getName().compareTo(o.getName());
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeString(this.type);
    dest.writeString(this.c);
    dest.writeString(this.zmw);
    dest.writeString(this.tz);
    dest.writeString(this.tzs);
    dest.writeString(this.l);
    dest.writeString(this.ll);
    dest.writeString(this.latitude);
    dest.writeString(this.longitude);
  }

  public String getC() {
    return c;
  }

  public String getL() {
    return l;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLl() {
    return ll;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getTz() {
    return tz;
  }

  public String getTzs() {
    return tzs;
  }

  public String getZmw() {
    return zmw;
  }

  @Override
  public int hashCode() {
    int result = getName() != null ? getName().hashCode() : 0;
    result = 31 * result + (getType() != null ? getType().hashCode() : 0);
    result = 31 * result + (getC() != null ? getC().hashCode() : 0);
    result = 31 * result + (getZmw() != null ? getZmw().hashCode() : 0);
    result = 31 * result + (getTz() != null ? getTz().hashCode() : 0);
    result = 31 * result + (getTzs() != null ? getTzs().hashCode() : 0);
    result = 31 * result + (getL() != null ? getL().hashCode() : 0);
    result = 31 * result + (getLl() != null ? getLl().hashCode() : 0);
    result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
    result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AutoCompleteResult)) {
      return false;
    }

    final AutoCompleteResult that = (AutoCompleteResult) o;

    if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
      return false;
    }
    if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) {
      return false;
    }
    if (getC() != null ? !getC().equals(that.getC()) : that.getC() != null) {
      return false;
    }
    if (getZmw() != null ? !getZmw().equals(that.getZmw()) : that.getZmw() != null) {
      return false;
    }
    if (getTz() != null ? !getTz().equals(that.getTz()) : that.getTz() != null) {
      return false;
    }
    if (getTzs() != null ? !getTzs().equals(that.getTzs()) : that.getTzs() != null) {
      return false;
    }
    if (getL() != null ? !getL().equals(that.getL()) : that.getL() != null) {
      return false;
    }
    if (getLl() != null ? !getLl().equals(that.getLl()) : that.getLl() != null) {
      return false;
    }
    if (getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null) {
      return false;
    }
    return getLongitude() != null ? getLongitude().equals(that.getLongitude()) : that.getLongitude() == null;
  }

  @Override
  public String toString() {
    return getName();
  }
}
