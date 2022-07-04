package lk.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.d24hostel.bo.BOFactory;
import lk.d24hostel.bo.custom.UserBO;
import lk.d24hostel.dto.UserDTO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class HomeFormController {
    public Label lblDate;
    public Label lblTime;
    public AnchorPane MainAnchorPane;
    public AnchorPane NameSideAnchorPane;
    public JFXButton btnManageRooms;
    public JFXButton btnRegStudents;
    public JFXButton btnManageStudents;
    public JFXButton btnManageRevDetails;
    public JFXButton btnLogout;
    public AnchorPane icnSideAnchorPane;
    public Label lblAdmin;
    public JFXPasswordField pwdPassword;
    public AnchorPane txtDownPane;
    public JFXTextField txtUserName;
    public FontAwesomeIconView icnEye;
    public JFXTextField txtPassword;
    public JFXButton btnUpdate;
    public JFXTextField txtOldPassword;

    String userType;
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() throws IOException {
        loadDateAndTime();
        txtDownPane.setVisible(false);
        NameSideAnchorPane.setVisible(false);

        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            pwdPassword.setText(newValue);
        });

        txtUserName.setEditable(false);

    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void NameSideOnAction(MouseEvent mouseEvent) {
        NameSideAnchorPane.setVisible(false);
    }

    public void btnButtonOnAction(MouseEvent mouseEvent) throws IOException {

        Object o = mouseEvent.getSource();

        if (o instanceof JFXButton){
            JFXButton button= (JFXButton) o;

            if(button.getId().equals("DashBoardImage")){
                setUI("StudentRegistationForm");

            }if(button.getId().equals("ManageRoomButton")){
                setUI("RoomForm");

            }if(button.getId().equals("ManageStudentButton")){
                setUI("StudentRegistationForm");

            }if(button.getId().equals("RegisterStudentButton")){
                setUI("StudentForm");

            }if(button.getId().equals("ReservationButton")){
                setUI("ReservationDetailForm");

            }if(button.getId().equals("LogOutButton")){
                setUI("LoginForm");
            }

        }
    }

    public void iconSideOnAction(MouseEvent mouseEvent) {NameSideAnchorPane.setVisible(true);
    }

    public void imgDashBoardOnAction(MouseEvent mouseEvent) throws IOException {
        setUI("DashBoardForm");
    }

    public void setUI(String URI) throws IOException {
        MainAnchorPane.getChildren().clear();
        MainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../view/" + URI + ".fxml")));
    }

    String userName;
    String pas;

    public void getAllData(String text,String password) {
        userName=text;
        lblAdmin.setText(userName);
        txtUserName.setText(lblAdmin.getText());

        pas=password;
    }

    public void eyeClickOnAction(MouseEvent mouseEvent) {
        if (icnEye.getGlyphName().equals("EYE_SLASH")) { // must show password
            icnEye.setGlyphName("EYE");

            txtPassword.setText(pwdPassword.getText()); //copy PwdPassword data to  txtPW
            pwdPassword.setVisible(false);  //PWField hidden
            txtPassword.setVisible(true);   //txtField Shown

        } else if (icnEye.getGlyphName().equals("EYE")) {  // must hide  password
            icnEye.setGlyphName("EYE_SLASH");

            pwdPassword.setText(txtPassword.getText());
            txtPassword.setVisible(false); //txtField hide
            pwdPassword.setVisible(true);  //PWField shown

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        for (UserDTO userDTO : userBO.getAllUser()) {

            if(txtUserName.getText().equals(userDTO.getUserName()) && txtOldPassword.getText().equals(userDTO.getPassword())){

                System.out.println(userDTO.getUserId()+" - "+userDTO.getName()+" - "+userDTO.getUserName() +" - "+userDTO.getPassword());

                userBO.updateUser(new UserDTO(
                        userDTO.getUserId(),
                        userDTO.getName(),
                        userDTO.getUserName(),
                        pwdPassword.getText()
                ));

                pwdPassword.clear();
                txtPassword.clear();

            }
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtOldPassword.setText(pas);
    }

    public void upDownClickOnAction(MouseEvent mouseEvent) throws IOException {
        txtDownPane.setVisible(true);

        for (UserDTO userDTO : userBO.getAllUser()) {

            if(txtUserName.getText().equals(userDTO.getUserName()) ){

                txtOldPassword.setText(userDTO.getPassword());
            }
        }
    }

    public void downPaneCloseOnAction(MouseEvent mouseEvent) {txtDownPane.setVisible(false);
    }
}
