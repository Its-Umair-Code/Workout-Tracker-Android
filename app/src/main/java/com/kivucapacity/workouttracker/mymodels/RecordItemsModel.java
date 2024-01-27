package com.kivucapacity.workouttracker.mymodels;

public class RecordItemsModel {

    int image, count;
    String title;

    public RecordItemsModel(int image, String title, int count) {
        this.image = image;
        this.title = title;
        this.count = count;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
