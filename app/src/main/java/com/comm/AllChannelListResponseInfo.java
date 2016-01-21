package com.comm;

import java.util.ArrayList;
import java.util.List;

public class AllChannelListResponseInfo {
    private String type;
    private String contentType;
    private String title;
    private Object thumbnailURLs;
    private Object authorName;
    private Object authorHeadImageURL;
    private Object badges;
    private int readingCount;
    private int commentsCount;
    private int contentId;
    private Object contentURL;
    private int adId;
    private List<AllChannelListResponseInfoChild> childElements;

    public AllChannelListResponseInfo() {
        childElements = new ArrayList<AllChannelListResponseInfoChild>();
    }

    @Override
    public String toString() {
        return type + " " + contentType + " " + title;
    }

    public String gettype() {
        if (type == null) {
            return "";
        } else {
            return type;
        }
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getcontentType() {
        if (contentType == null) {
            return "";
        } else {
            return contentType;
        }
    }

    public void setcontentType(String contentType) {
        this.contentType = contentType;
    }

    public String gettitle() {
        if (title == null) {
            return "";
        } else {
            return title;
        }
    }

    public void settitle(String title) {
        this.title = title;
    }

    public Object getthumbnailURLs() {
        return thumbnailURLs;
    }

    public void setthumbnailURLs(Object thumbnailURLs) {
        this.thumbnailURLs = thumbnailURLs;
    }

    public Object getauthorName() {
        return authorName;
    }

    public void setauthorName(Object authorName) {
        this.authorName = authorName;
    }

    public Object getauthorHeadImageURL() {
        return authorHeadImageURL;
    }

    public void setauthorHeadImageURL(Object authorHeadImageURL) {
        this.authorHeadImageURL = authorHeadImageURL;
    }

    public Object getbadges() {
        return badges;
    }

    public void setbadges(Object badges) {
        this.badges = badges;
    }

    public int getreadingCount() {
        return readingCount;
    }

    public void setreadingCount(int readingCount) {
        this.readingCount = readingCount;
    }

    public int getcommentsCount() {
        return commentsCount;
    }

    public void setcommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getcontentId() {
        return contentId;
    }

    public void setcontentId(int contentId) {
        this.contentId = contentId;
    }

    public Object getcontentURL() {
        return contentURL;
    }

    public void setcontentURL(Object contentURL) {
        this.contentURL = contentURL;
    }

    public int getadId() {
        return adId;
    }

    public void setadId(int adId) {
        this.adId = adId;
    }

    public List<AllChannelListResponseInfoChild> getchildElements() {
        return childElements;
    }

    public void setchildElements(List<AllChannelListResponseInfoChild> childElements) {
        this.childElements = childElements;
    }
}
