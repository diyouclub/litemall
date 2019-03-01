package org.linlinjava.litemall.admin.dto;

/**
 * Created by 10415 on 2019/2/28.
 */
public class TabInfoDTO {
    private String  infoTitle;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public void setInfoDescription(String infoDescription) {
        this.infoDescription = infoDescription;
    }

    public String getInfoMainImg() {
        return infoMainImg;
    }

    public void setInfoMainImg(String infoMainImg) {
        this.infoMainImg = infoMainImg;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    public int getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(int showIndex) {
        this.showIndex = showIndex;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getTopRank() {
        return topRank;
    }

    public void setTopRank(int topRank) {
        this.topRank = topRank;
    }

    public int getOpenRelated() {
        return openRelated;
    }

    public void setOpenRelated(int openRelated) {
        this.openRelated = openRelated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfoShortTitle() {
        return infoShortTitle;
    }

    public void setInfoShortTitle(String infoShortTitle) {
        this.infoShortTitle = infoShortTitle;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    private String infoShortTitle;
    private int infoId;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getAssignPhone() {
        return assignPhone;
    }

    public void setAssignPhone(String assignPhone) {
        this.assignPhone = assignPhone;
    }

    private String assignPhone;
    private int contentId;
    private String infoDescription;
    private String infoMainImg;
    private int clsId;
    private int showIndex;
    private int scope;
    private int topRank;
    private int openRelated;
    private String content;
    private String author;
    private String name;

}
