package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;


public class Controller {
    @FXML
    private ImageView btn_circle_clock;
    @FXML
    private ImageView btn_digit_clock;
    @FXML
    private ImageView btn_time_zone;
    @FXML
    private ImageView btn_switch_hour;
    @FXML
    private AnchorPane h_circle_clock;
    @FXML
    private AnchorPane h_digit_clock;
    @FXML
    private AnchorPane h_time_zone;
    @FXML
    private AnchorPane h_switch_hour;
    @FXML
    private CheckBox cb_taiwan;
    @FXML
    private CheckBox cb_america;
    @FXML
    private CheckBox cb_japan;
    @FXML
    private CheckBox cb_uk;
    @FXML
    Label dateLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label dayLabel;
    EventHandler eh = (EventHandler<ActionEvent>) event -> {
        if (event.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox)event.getSource();
            if ("Taiwan".equals(chk.getText())) {
                Controller.this.cb_taiwan.setSelected(true);
                Controller.this.cb_america.setSelected(false);
                Controller.this.cb_japan.setSelected(false);
                Controller.this.cb_uk.setSelected(false);
                System.out.println("Taiwan");
            } else if ("America".equals(chk.getText())) {
                Controller.this.cb_taiwan.setSelected(false);
                Controller.this.cb_america.setSelected(true);
                Controller.this.cb_japan.setSelected(false);
                Controller.this.cb_uk.setSelected(false);
                System.out.println("America");
            } else if ("Japan".equals(chk.getText())) {
                Controller.this.cb_taiwan.setSelected(false);
                Controller.this.cb_america.setSelected(false);
                Controller.this.cb_japan.setSelected(true);
                Controller.this.cb_uk.setSelected(false);
                System.out.println("Japan");
            } else if ("United Kingdom".equals(chk.getText())) {
                Controller.this.cb_taiwan.setSelected(false);
                Controller.this.cb_america.setSelected(false);
                Controller.this.cb_japan.setSelected(false);
                Controller.this.cb_uk.setSelected(true);
                System.out.println("United Kingdom");
            }
        }

    };

    public Controller() {
    }

    @FXML
    private void handleButtonAction(MouseEvent event) throws InterruptedException {
        if (event.getTarget() == this.btn_circle_clock) {
            ClockPane clock = new ClockPane();
            EventHandler<ActionEvent> eventHandler = (e) -> {
                this.h_circle_clock.getChildren().clear();
                clock.setCurrentTime();
                String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
                Label lblCurrentTime = new Label(timeString);
                lblCurrentTime.setTextFill(Color.web("#0076a3"));
                lblCurrentTime.setFont(new Font("Arial", 30.0D));
                AnchorPane var10000 = this.h_circle_clock;
                AnchorPane.setTopAnchor(clock, 50.0D);
                var10000 = this.h_circle_clock;
                AnchorPane.setRightAnchor(clock, 100.0D);
                var10000 = this.h_circle_clock;
                AnchorPane.setBottomAnchor(lblCurrentTime, 50.0D);
                var10000 = this.h_circle_clock;
                AnchorPane.setRightAnchor(lblCurrentTime, 150.0D);
                this.h_circle_clock.getChildren().add(clock);
                this.h_circle_clock.getChildren().add(lblCurrentTime);
            };
            Timeline animation = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(1000.0D), eventHandler, new KeyValue[0])});
            animation.setCycleCount(-1);
            animation.play();
            this.h_circle_clock.widthProperty().addListener((ov) -> {
                clock.setW(this.h_circle_clock.getWidth());
            });
            this.h_circle_clock.heightProperty().addListener((ov) -> {
                clock.setH(this.h_circle_clock.getHeight());
            });
            this.h_circle_clock.setVisible(true);
            this.h_digit_clock.setVisible(false);
            this.h_time_zone.setVisible(false);
            this.h_switch_hour.setVisible(false);
        } else if (event.getTarget() == this.btn_digit_clock) {
            EventHandler<ActionEvent> eventHandler = (e) -> {
                digitclock clock = new digitclock();
                clock.calendar = Calendar.getInstance();
                clock.format = new SimpleDateFormat("hh:mm:ss a");
                clock.date = clock.calendar.getTime();
                clock.time = clock.format.format(clock.date);
                clock.format = new SimpleDateFormat("西元yyyy年 MMMM dd ");
                clock.date = clock.calendar.getTime();
                clock.month = clock.format.format(clock.date);
                clock.format = new SimpleDateFormat("EEEE");
                clock.date = clock.calendar.getTime();
                clock.day = clock.format.format(clock.date);
                dateLabel.setText(String.valueOf(clock.month));
                timeLabel.setText(clock.time);
                dayLabel.setText(clock.day);
            };
            Timeline animation = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(1000.0D), eventHandler, new KeyValue[0])});
            animation.setCycleCount(-1);
            animation.play();
                    this.h_circle_clock.setVisible(false);
                    this.h_digit_clock.setVisible(true);
                    this.h_time_zone.setVisible(false);
                    this.h_switch_hour.setVisible(false);
        } else if (event.getTarget() == this.btn_time_zone) {
            this.cb_taiwan.setOnAction(this.eh);
            this.cb_america.setOnAction(this.eh);
            this.cb_japan.setOnAction(this.eh);
            this.cb_uk.setOnAction(this.eh);
            this.h_circle_clock.setVisible(false);
            this.h_digit_clock.setVisible(false);
            this.h_time_zone.setVisible(true);
            this.h_switch_hour.setVisible(false);
        } else {
            this.h_circle_clock.setVisible(false);
            this.h_digit_clock.setVisible(false);
            this.h_time_zone.setVisible(false);
            this.h_switch_hour.setVisible(true);
        }

    }
}
