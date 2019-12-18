package com.yt.tbottomnavgationlibrary.base.bean;

public class DefaultDataBean {

    private String text;
    private int seletctedRes;//未选中的图片资源
    private int unSelectedRes;//选中的图片资源
    private int unSelectedTextColor;
    private int textSize;
    private int selectedTextColor;

    private boolean isSelected;//是否选中

    public DefaultDataBean(String text, int seletctedRes, int unSelectedRes, int unSelectedTextColor, int selectedTextColor, boolean isSelected) {
        this.text = text;
        this.seletctedRes = seletctedRes;
        this.unSelectedRes = unSelectedRes;
        this.unSelectedTextColor = unSelectedTextColor;

        this.selectedTextColor = selectedTextColor;
        this.isSelected = isSelected;
    }

    public DefaultDataBean(String text, int seletctedRes, int unSelectedRes, int unSelectedTextColor, int selectedTextColor, boolean isSelected, int textSize) {
        this.text = text;
        this.seletctedRes = seletctedRes;
        this.unSelectedRes = unSelectedRes;
        this.unSelectedTextColor = unSelectedTextColor;
        this.textSize = textSize;
        this.selectedTextColor = selectedTextColor;
        this.isSelected = isSelected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSeletctedRes() {
        return seletctedRes;
    }

    public void setSeletctedRes(int seletctedRes) {
        this.seletctedRes = seletctedRes;
    }

    public int getUnSelectedRes() {
        return unSelectedRes;
    }

    public void setUnSelectedRes(int unSelectedRes) {
        this.unSelectedRes = unSelectedRes;
    }

    public int getUnSelectedTextColor() {
        return unSelectedTextColor;
    }

    public void setUnSelectedTextColor(int unSelectedTextColor) {
        this.unSelectedTextColor = unSelectedTextColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
