package com.example.bookwormproject.model;

public class Library {

    public String LibraryId, LibraryName, LibraryAddress, LibraryPhone, Latitude, Longitude;


    public Library(String LibraryId, String LibraryName, String LibraryAddress, String LibraryPhone, String Latitude, String Longitude){
        this.LibraryId = LibraryId;
        this.LibraryName = LibraryName;
        this.LibraryAddress = LibraryAddress;
        this.LibraryPhone = LibraryPhone;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public String getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(String libraryId) {
        LibraryId = libraryId;
    }

    public String getLibraryName() {
        return LibraryName;
    }

    public void setLibraryName(String libraryName) {
        LibraryName = libraryName;
    }

    public String getLibraryAddress() {
        return LibraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        LibraryAddress = libraryAddress;
    }

    public String getLibraryPhone() {
        return LibraryPhone;
    }

    public void setLibraryPhone(String libraryPhone) {
        LibraryPhone = libraryPhone;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }


}
