package sample.util;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class MyNotification {
        // this method returns a notification panel with your specified title and text

        public static void createNotification(String title,String text){
            Notifications.create()
                    .title(title)
                    .text(text) .darkStyle()
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .show();
        }

}
