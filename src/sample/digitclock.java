package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;


public class digitclock implements Runnable, Initializable {


    //Linking FXML components to Java code :

    @FXML
    Label dateLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label dayLabel;

    //Initializing variables

    Thread thread = null;
    String time = "";
    String month = "";
    String day = "";
    SimpleDateFormat format;
    Date date;
    Calendar calendar;


    @Override
    public void initialize(URL location, ResourceBundle resources) { //Start timer using thread on application startup
        thread = new Thread(this);
        thread.start();
    }
    public void run() {
        try {
            while (true) {
                //Setting date format and variables:
                calendar = Calendar.getInstance();

                format = new SimpleDateFormat("hh:mm:ss a");
                date = calendar.getTime();
                time = format.format(date);

                format = new SimpleDateFormat("MMMM dd yyyy");
                date = calendar.getTime();
                month = format.format(date);

                format = new SimpleDateFormat("EEEE");
                date = calendar.getTime();
                day = format.format(date);

                //Setting elements to pane:

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dateLabel.setText(String.valueOf(month));
                        timeLabel.setText(time);
                        dayLabel.setText(day);

                    }
                });

                Thread.sleep(1000);
            }
        } catch (Exception e) { //Error check
            dateLabel.setText("");
            timeLabel.setText("Error occurred!!");
            dayLabel.setText("");
        }
    }
}
