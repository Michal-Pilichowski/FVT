package pilichm.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pilichm.utils.Context;
import pilichm.utils.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddSignatureController {

    @FXML
    private void selectSourceFile(){
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jFileChooser.showOpenDialog(null);
        if (returnValue==JFileChooser.APPROVE_OPTION){
            try {
                FileInputStream sourceFileInputStream = new FileInputStream(jFileChooser.getSelectedFile().getAbsolutePath());
                Image sourceImage = new Image(sourceFileInputStream);
                Context context = Context.getContext();
                ImageView sourceImageView = (ImageView) context.getBorderPane().lookup("#source_image");
                sourceImageView.setImage(sourceImage);
            } catch (FileNotFoundException e) {
                Utils._log("Error setting selected image.");
                e.printStackTrace();
            }
        } else {
            Utils._log("Error loading selected file.");
        }
    }

    @FXML
    private void zoomSignature(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/pilichm/gui/layout/signature_zoom.fxml"));
        Context context = Context.getContext();

        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Signature zoom");
            stage.getIcons().add(new Image("file:src/ic_main.png"));
            stage.setScene(scene);

            ImageView calculatedSignature = (ImageView) context.getBorderPane().lookup("#calculated_image");
            ImageView zoomedSignature = (ImageView) scene.lookup("#signature_zoom");
            zoomedSignature.setImage(calculatedSignature.getImage());



            stage.show();
        } catch (IOException e) {
            Utils._log("Error while opening large signature window.");
            e.printStackTrace();
        }
    }

}
