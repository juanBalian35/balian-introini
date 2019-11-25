package ecoshop.frontend;


import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Toast {

        private static int TOAST_TIMEOUT = 5000;

        private static Popup createPopup(final String mensaje, final String mensajeBtn) {
            final Popup popup = new Popup();
            popup.setAutoFix(true);
            Label label = new Label(mensaje);
            label.setMaxWidth(500);
            label.setWrapText(true);
            JFXButton btn = new JFXButton();
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(10);
            btn.setText(mensajeBtn);
            btn.setButtonType(JFXButton.ButtonType.FLAT);
            btn.setFocusTraversable(false);
            btn.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                new Timeline(new KeyFrame(
                        Duration.seconds( 0.4),
                        ae -> {
                            final DoubleProperty yProperty = new SimpleDoubleProperty(popup.getY());
                            yProperty.addListener((ob, n, n1) -> popup.setY(n1.doubleValue()));

                            Timeline timeIn = new Timeline();
                            timeIn.getKeyFrames().add(
                                    new KeyFrame(Duration.seconds(0.4),
                                            new KeyValue(yProperty, popup.getY() + 23, Interpolator.EASE_BOTH)));
                            timeIn.play();
                        })).play();

                new Timeline(new KeyFrame(
                        Duration.seconds(1),
                        ae -> popup.hide())).play();
                        }
                });
            hbox.getStylesheets().add("/ecoshop/frontend/estilos/estilos.css");
            label.getStylesheets().add("/ecoshop/frontend/estilos/estilos.css");
            label.getStyleClass().add("popup-texto");
            btn.getStylesheets().add("/ecoshop/frontend/estilos/estilos.css");
            btn.getStyleClass().add("popup-boton");
            hbox.getStyleClass().add("popup");
            hbox.getChildren().addAll(label,btn);
            popup.getContent().add(hbox);
            
            return popup;
        }

    public static void show(final String mensaje, final String mensajeBtn, final int tiempo, final Control control) {
        Stage stage = (Stage) control.getScene().getWindow();
        final Popup popup = createPopup(mensaje, mensajeBtn);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getHeight()-10);

            final DoubleProperty yProperty = new SimpleDoubleProperty(popup.getY());
            yProperty.addListener((ob, n, n1) -> popup.setY(n1.doubleValue()));

            Timeline timeIn = new Timeline();
            timeIn.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(yProperty, popup.getY() - 23, Interpolator.EASE_BOTH)));
            timeIn.play();

        });
        popup.show(stage);

        new Timeline(new KeyFrame(
                Duration.seconds(tiempo - 0.5),
                ae -> {
                    final DoubleProperty yProperty = new SimpleDoubleProperty(popup.getY());
                    yProperty.addListener((ob, n, n1) -> popup.setY(n1.doubleValue()));

                    Timeline timeIn = new Timeline();
                    timeIn.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(0.4),
                                    new KeyValue(yProperty, popup.getY() + 23, Interpolator.EASE_BOTH)));
                    timeIn.play();
                })).play();

        new Timeline(new KeyFrame(
                Duration.seconds(tiempo),
                ae -> popup.hide())).play();
    }
}