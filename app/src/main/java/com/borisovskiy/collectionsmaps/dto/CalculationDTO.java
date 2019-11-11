package com.borisovskiy.collectionsmaps.dto;

public class CalculationDTO {

    public static final long TIME_UNDEFINED = -1;
    public static final String LABEL_TIME_UNDEFINED = "N/A";

    private long time = TIME_UNDEFINED;
    private int label;
    private String tag;
    private boolean isLoading = false;

    public CalculationDTO(int label, String tag) {
        this.label = label;
        this.tag = tag;
    }

    public int getLabelResourceId() {
        return label;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public String getTimeText() {
        return TIME_UNDEFINED == getTime() ? LABEL_TIME_UNDEFINED : String.valueOf(getTime());
    }
}

