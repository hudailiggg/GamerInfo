package com.comm;

import java.util.List;

public class AllChannelListResponseInfoChild {
    private String type;
    private String contentType;
    private String title;
    private List<String> thumbnailURLs;
    private String authorName;
    private String authorHeadImageURL;
    private List<Object> badges;
    private int readingCount;
    private int commentsCount;
    private int contentId;
    private Object contentURL;
    private int adId;
    private boolean dataPackage;
    private Object childElements;

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

    public List<String> getthumbnailURLs() {
        return thumbnailURLs;
    }

    public void setthumbnailURLs(List<String> thumbnailURLs) {
        this.thumbnailURLs = thumbnailURLs;
    }

    public String getauthorName() {
        if (authorName == null) {
            return "";
        } else {
            return authorName;
        }
    }

    public void setauthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getauthorHeadImageURL() {
        if (authorHeadImageURL == null) {
            return "";
        } else {
            return authorHeadImageURL;
        }
    }

    public void setauthorHeadImageURL(String authorHeadImageURL) {
        this.authorHeadImageURL = authorHeadImageURL;
    }

    public List<Object> getbadges() {
        return badges;
    }

    public void setbadges(List<Object> badges) {
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

    public boolean getdataPackage() {
        return dataPackage;
    }

    public void setdataPackage(boolean dataPackage) {
        this.dataPackage = dataPackage;
    }

    public Object getchildElements() {
        return childElements;
    }

    public void setchildElements(Object childElements) {
        this.childElements = childElements;
    }
}
