package sample;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    private double w = 250.0D;
    private double h = 250.0D;

    public ClockPane() {
        this.setCurrentTime();
    }

    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.paintClock();
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        this.paintClock();
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        this.paintClock();
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
        this.paintClock();
    }

    public double getW() {
        return this.w;
    }

    public void setW(double w) {
        this.w = w;
        this.paintClock();
    }

    public double getH() {
        return this.h;
    }

    public void setH(double h) {
        this.h = h;
        this.paintClock();
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar();
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
        this.paintClock();
    }

    protected void paintClock() {
        double clockRadius = Math.min(this.w, this.h) * 0.8D * 0.5D;
        double centerX = this.w / 2.0D;
        double centerY = this.h / 2.0D;
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX - 5.0D, centerY - clockRadius + 12.0D, "12");
        Text t2 = new Text(centerX - clockRadius + 3.0D, centerY + 5.0D, "9");
        Text t3 = new Text(centerX + clockRadius - 10.0D, centerY + 3.0D, "3");
        Text t4 = new Text(centerX - 3.0D, centerY + clockRadius - 3.0D, "6");
        double sLength = clockRadius * 0.8D;
        double scondX = centerX + sLength * Math.sin((double)this.second * 0.10471975511965977D);
        double scondY = centerY - sLength * Math.cos((double)this.second * 0.10471975511965977D);
        Line sline = new Line(centerX, centerY, scondX, scondY);
        sline.setStroke(Color.RED);
        double mLength = clockRadius * 0.65D;
        double minuteX = centerX + mLength * Math.sin((double)this.minute * 0.10471975511965977D);
        double minuteY = centerY - mLength * Math.cos((double)this.minute * 6.283185307179586D / 60.0D);
        Line mline = new Line(centerX, centerY, minuteX, minuteY);
        mline.setStroke(Color.BLUE);
        double hLength = clockRadius * 0.5D;
        double hourX = centerX + hLength * Math.sin(((double)(this.hour % 12) + (double)this.minute / 60.0D) * 0.5235987755982988D);
        double hourY = centerY - hLength * Math.cos((double)(this.hour % 12 + this.minute / 60) * 0.5235987755982988D);
        Line hline = new Line(centerX, centerY, hourX, hourY);
        hline.setStroke(Color.GREEN);
        this.getChildren().clear();
        this.getChildren().addAll(new Node[]{circle, t1, t2, t3, t4, sline, mline, hline});
    }
}
