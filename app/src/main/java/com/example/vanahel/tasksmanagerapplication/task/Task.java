package com.example.vanahel.tasksmanagerapplication.task;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    private String title;
    private String description;
    private boolean isFavorite;
    private String id;
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };


    public Task (String title, String description, boolean isFavorite, String id){
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
        this.id = id;
    }

    private Task (Parcel in){
        title = in.readString();
        description = in.readString();
        isFavorite = Boolean.parseBoolean(String.valueOf(in.readInt()));
        id = in.readString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeInt(isFavorite ? 1 : 0);
        parcel.writeString(id);
    }

    public String toString() {
        return title + "," + description + "," + (isFavorite ? 1 : 0) + "," + id + System.lineSeparator();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (isFavorite != task.isFavorite) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;
        return id != null ? id.equals(task.id) : task.id == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isFavorite ? 1 : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
