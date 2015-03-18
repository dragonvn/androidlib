package vn.heallife.duchv.ouralarm.background.model;

/**
 * Created by duchv on 2/13/15.
 */
public class Alarm {
    private String hour;
    private String minus;
    private String text;
    private boolean[] repeat;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean[] getRepeat() {
        return repeat;
    }

    public void setRepeat(boolean[] repeat) {
        this.repeat = repeat;
    }
    public Alarm(String h, String m, String msg){
        setHour(h);
        setMinus(m);
        setText(msg);
    }
}
