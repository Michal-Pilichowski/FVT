package pilichm.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pilichm.utils.Context;
import pilichm.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuController {

    @FXML
    private void exitProgram(){
        System.exit(0);
    }

    @FXML
    private void moveToAddSignatureScreen(){
        try {
            Context context = Context.getContext();

            context.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("/pilichm/gui/layout/add_signature_window.fxml")));

            ImageView sourceImageView = (ImageView) context.getBorderPane().lookup("#source_image");
            FileInputStream sourceFileInputStream = new FileInputStream("C:\\Users\\Michał Pilichowski\\Pictures\\vein.jpg");
            Image sourceImage = new Image(sourceFileInputStream);
            sourceImageView.setImage(sourceImage);

            ImageView signatureImageView = (ImageView) context.getBorderPane().lookup("#calculated_image");
            FileInputStream signatureFileInputStream = new FileInputStream("C:\\Users\\Michał Pilichowski\\Pictures\\vein.jpg");
            Image signatureImage = new Image(signatureFileInputStream);
            signatureImageView.setImage(signatureImage);

        } catch (IOException e) {
            Utils._log("Error while loading add signature screen.");
            e.printStackTrace();
        }
    }

    @FXML
    private void moveToPreferencesScreen(){
        try {
            Context context = Context.getContext();
            context.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("/pilichm/gui/layout/preferences_window.fxml")));

            Label numberOfRepetitionsLabel = (Label) context.getBorderPane().lookup("#label_current_number_of_repetitions");
            numberOfRepetitionsLabel.setText(String.valueOf(context.getNO_OF_REPETITIONS()));

            Label sizeOfIntersectionLabel = (Label) context.getBorderPane().lookup("#label_current_size_of_intersection");
            sizeOfIntersectionLabel.setText(String.valueOf(context.getSIZE_OF_INTERSECTION()));

            CheckBox debugEnabledCheckBox = (CheckBox) context.getBorderPane().lookup("#check_box_enable_debugging");
            debugEnabledCheckBox.setSelected(context.isDEBUG_ENABLED());

            debugEnabledCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                context.setDEBUG_ENABLED(newValue);
            });

            Slider numberOfRepetitionsSlider = (Slider) context.getBorderPane().lookup("#slider_number_of_repetitions");
            numberOfRepetitionsSlider.setValue(Double.parseDouble(String.valueOf(context.getNO_OF_REPETITIONS())));
            numberOfRepetitionsSlider.setMinorTickCount(1000);
            numberOfRepetitionsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                context.setNO_OF_REPETITIONS(new Double(numberOfRepetitionsSlider.getValue()).intValue());
                numberOfRepetitionsLabel.textProperty().setValue(String.valueOf(context.getNO_OF_REPETITIONS()));
            });

            Slider sizeOfIntersectionSlider = (Slider) context.getBorderPane().lookup("#slider_size_of_intersection");
            sizeOfIntersectionSlider.setValue(Double.parseDouble(String.valueOf(context.getSIZE_OF_INTERSECTION())));
            sizeOfIntersectionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                context.setSIZE_OF_INTERSECTION(new Double(sizeOfIntersectionSlider.getValue()).intValue());
                sizeOfIntersectionLabel.textProperty().setValue(String.valueOf(context.getSIZE_OF_INTERSECTION()));
            });

        } catch (IOException e) {
            Utils._log("Error while loading preferences screen.");
            e.printStackTrace();
        }
    }

}
