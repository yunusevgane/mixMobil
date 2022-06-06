package com.financemoney.yoga.utils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDate;


public final class CalendarDay implements Parcelable {



  private final LocalDate date;


  public CalendarDay(final int year, final int month, final int day) {
    date = LocalDate.of(year, month, day);
  }



  private CalendarDay(@NonNull final LocalDate date) {
    this.date = date;
  }



  @NonNull public static CalendarDay today() {
    return from(LocalDate.now());
  }


  @NonNull public static CalendarDay from(int year, int month, int day) {
    return new CalendarDay(year, month, day);
  }



  public static CalendarDay from(@Nullable LocalDate date) {
    if (date == null) {
      return null;
    }
    return new CalendarDay(date);
  }


  public int getYear() {
    return date.getYear();
  }



  public int getMonth() {
    return date.getMonthValue();
  }



  public int getDay() {
    return date.getDayOfMonth();
  }


  @NonNull public LocalDate getDate() {
    return date;
  }



  public boolean isInRange(@Nullable CalendarDay minDate, @Nullable CalendarDay maxDate) {
    return !(minDate != null && minDate.isAfter(this)) &&
        !(maxDate != null && maxDate.isBefore(this));
  }


  public boolean isBefore(@NonNull final CalendarDay other) {
    return date.isBefore(other.getDate());
  }


  public boolean isAfter(@NonNull final CalendarDay other) {
    return date.isAfter(other.getDate());
  }

  @Override public boolean equals(Object o) {
    return o instanceof CalendarDay && date.equals(((CalendarDay) o).getDate());
  }

  @Override
  public int hashCode() {
    return hashCode(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
  }

  private static int hashCode(int year, int month, int day) {


    return (year * 10000) + (month * 100) + day;
  }

  @Override
  public String toString() {
    return "CalendarDay{" + date.getYear() + "-" + date.getMonthValue() + "-"
        + date.getDayOfMonth() + "}";
  }


  public CalendarDay(Parcel in) {
    this(in.readInt(), in.readInt(), in.readInt());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(date.getYear());
    parcel.writeInt(date.getMonthValue());
    parcel.writeInt(date.getDayOfMonth());
  }




  public static final Creator<CalendarDay> CREATOR = new Creator<CalendarDay>() {
    public CalendarDay createFromParcel(Parcel in) {
      return new CalendarDay(in);
    }

    public CalendarDay[] newArray(int size) {
      return new CalendarDay[size];
    }
  };
}