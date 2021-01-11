package com.example.bookwormproject.model;

public class Review {

    public String ReviewId, MemberId, LibraryId, ReviewTitle, ReviewDescription;

    public Review(String reviewId, String memberId, String libraryId, String reviewTitle, String reviewDescription) {
        ReviewId = reviewId;
        MemberId = memberId;
        LibraryId = libraryId;
        ReviewTitle = reviewTitle;
        ReviewDescription = reviewDescription;
    }

    public String getReviewId() {
        return ReviewId;
    }

    public void setReviewId(String reviewId) {
        this.ReviewId = reviewId;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        this.MemberId = memberId;
    }

    public String getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(String libraryId) {
        this.LibraryId = libraryId;
    }

    public String getReviewTitle() {
        return ReviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.ReviewTitle = reviewTitle;
    }

    public String getReviewDescription() {
        return ReviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.ReviewDescription = reviewDescription;
    }
}
