package john.gomez.com.weatherstone.model;


public class Period {
    private int minTempC;
    private int avgTempC;
    private int maxTempC;
    private int minTempF;
    private int avgTempF;
    private int maxTempF;
    private String dateTimeISO;
    private String icon;


    public int getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(int minTempC) {
        this.minTempC = minTempC;
    }

    public int getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(int avgTempC) {
        this.avgTempC = avgTempC;
    }

    public int getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(int maxTempC) {
        this.maxTempC = maxTempC;
    }

    public int getMinTempF() {
        return minTempF;
    }

    public void setMinTempF(int minTempF) {
        this.minTempF = minTempF;
    }

    public int getAvgTempF() {
        return avgTempF;
    }

    public void setAvgTempF(int avgTempF) {
        this.avgTempF = avgTempF;
    }

    public int getMaxTempF() {
        return maxTempF;
    }

    public void setMaxTempF(int maxTempF) {
        this.maxTempF = maxTempF;
    }

    public String getDateTimeISO() {
        return dateTimeISO;
    }

    public void setDateTimeISO(String dateTimeISO) {
        this.dateTimeISO = dateTimeISO;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
