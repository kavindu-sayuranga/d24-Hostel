package lk.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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

    String userType;

    public void initialize() throws IOException {
        NameSideAnchorPane.setVisible(false);
        setUI("DashBoardForm");
        loadDateAndTime();

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
        Object button = mouseEvent.getSource();
        if(button instanceof JFXButton){
            JFXButton jfxButton = (JFXButton) button;
            if (jfxButton.getId().equals("ManageRoomButton")){
                setUI("RoomForm");
            }else if (jfxButton.getId().equals("RegisterStudentButton")){
                setUI("StudentForm");
            }else if (jfxButton.getId().equals("ManageStudentButton")){
                setUI("StudentRegistationForm");
            }else if (jfxButton.getId().equals("LogOutButton")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginForm.fxml"));
                Parent parent= loader.load();
                Stage stage =new Stage(StageStyle.DECORATED);
                stage.setTitle("Log Out");
                stage.setScene(new Scene(parent));
                stage.show();

                Stage stage1 = (Stage) btnManageRooms.getScene().getWindow();
                stage1.close();
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

    public void getAllData(String text,String password) {
        userName=text;
        lblAdmin.setText(userName);
    }
}
