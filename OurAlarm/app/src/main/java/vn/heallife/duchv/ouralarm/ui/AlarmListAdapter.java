package vn.heallife.duchv.ouralarm.ui;

/**
 * Created by duchv on 2/13/15.
 */
public class AlarmListAdapter {

    int h;

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    int m;

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void Alarm(int h, int m,String message){
        setH(h);
        setM(m);
        setMessage(message);
    }

}
