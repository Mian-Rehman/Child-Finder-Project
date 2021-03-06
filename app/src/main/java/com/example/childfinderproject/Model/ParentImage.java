package com.example.childfinderproject.Model;

public class ParentImage {

    String parentId;
    String parentImage;
    String currentLatitude;
    String currentLongitude;

    public ParentImage(String parentId, String parentImage, String currentLatitude, String currentLongitude) {
        this.parentId = parentId;
        this.parentImage = parentImage;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
    }

    public ParentImage() {
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentImage() {
        return parentImage;
    }

    public void setParentImage(String parentImage) {
        this.parentImage = parentImage;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }
}
