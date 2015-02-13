package vn.heallife.duchv.ouralarm.background.model;

/**
 * Created by duchv on 2/13/15.
 */
public class Alarm {
    private int hour;
    private int minus;
    private String text;
    private boolean[] repeat;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean[] getRepeat() {
        return repeat;
    }

    public void setRepeat(boolean[] repeat) {
        this.repeat = repeat;
    }
}
