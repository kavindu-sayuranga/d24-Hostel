package lk.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.d24hostel.bo.custom.UserBO;
import lk.d24hostel.bo.custom.impl.UserBOImpl;
import lk.d24hostel.dto.UserDTO;
import lk.d24hostel.util.ValidationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField pwdPassword;
    public JFXButton btnLog;
    public AnchorPane apnMain;

    UserBO userBO = new UserBOImpl();

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern usernamePattern = Pattern.compile("^[A-z0-9]{3,10}$");
    LinkedHashMap<JFXPasswordField, Pattern> map1 = new LinkedHashMap<>();
    Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$");

    public void initialize(){
        pwdPassword.setVisible(false);

        //copy values for passwordField
        pwdPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            pwdPassword.setText(newValue);
        });

        storeValidation();
    }
    private void storeValidation() {
        map.put(txtUsername, usernamePattern);
        map1.put(pwdPassword, passwordPattern);

    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        ArrayList<UserDTO> allUser = userBO.getAllUser();

        for (UserDTO userDTO : allUser) {

            if(userDTO.getUserName().equals(txtUsername.getText()) && userDTO.getPassword().equals(pwdPassword.getText())){
                lordWindow();
            }else{
                new Alert(Alert.AlertType.ERROR,"Login Failed..").show();
            }
        }
    }

    public void lordWindow() throws IOException {

        Stage stage = (Stage) apnMain.getScene().getWindow();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/lk/d24/hostel/view/HomeForm.fxml"));

        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1);

        stage.setScene(scene1);

        HomeFormController controller = loader1.getController();
        controller.getAllData(txtUsername.getText(),pwdPassword.getText());

        stage.centerOnScreen();

    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextFields(map, btnLog);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void passwordFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXPasswordField(map1, btnLog);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXPasswordField) {
                JFXPasswordField errorText = (JFXPasswordField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}
