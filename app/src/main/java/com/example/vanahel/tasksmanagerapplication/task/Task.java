package com.example.vanahel.tasksmanagerapplication.task;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    private String title;
    private String description;
    private Boolean isFavorite;
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public Task (String title, String description, boolean isFavorite){
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    private Task (Parcel in){
        title = in.readString();
        description = in.readString();
        isFavorite = Boolean.parseBoolean(in.readString());
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public Integer getFavoriteAsInt() {
        return isFavorite ? 1 : 0;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(isFavorite.toString());
    }

    public String toString() {
        return title + "," + description + "," + (isFavorite ? 1:0)  + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;
        return isFavorite != null ? isFavorite.equals(task.isFavorite) : task.isFavorite == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (isFavorite != null ? isFavorite.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
