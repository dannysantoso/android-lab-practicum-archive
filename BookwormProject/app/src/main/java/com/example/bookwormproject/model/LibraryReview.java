package com.example.bookwormproject.model;

public class LibraryReview {

    public String ReviewId, MemberId, LibraryId, ReviewTitle, ReviewDescription;

    public String getReviewId() {
        return ReviewId;
    }

    public void setReviewId(String reviewId) {
        ReviewId = reviewId;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(String libraryId) {
        LibraryId = libraryId;
    }

    public String getReviewTitle() {
        return ReviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        ReviewTitle = reviewTitle;
    }

    public String getReviewDescription() {
        return ReviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        ReviewDescription = reviewDescription;
    }

    public LibraryReview(String reviewId, String memberId, String libraryId, String reviewTitle, String reviewDescription) {
        ReviewId = reviewId;
        MemberId = memberId;
        LibraryId = libraryId;
        ReviewTitle = reviewTitle;
        ReviewDescription = reviewDescription;
    }
}
