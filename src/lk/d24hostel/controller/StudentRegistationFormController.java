package lk.d24hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import lk.d24hostel.bo.custom.ReservationBO;
import lk.d24hostel.bo.custom.impl.ReservationBOImpl;
import lk.d24hostel.dto.ReservedDTO;
import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.dto.StudentDTO;
import lk.d24hostel.entity.Room;
import lk.d24hostel.entity.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentRegistationFormController {

    public JFXComboBox<String>  cmbStudent;
    public JFXComboBox<String> cmbRoom;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeymoney;
    public JFXTextField txtQty;
    public JFXComboBox<String> cmbGender;
    public JFXDatePicker DOB;
    public Label lblAvailable;
    public JFXTextField txtRegisterId;
    public JFXTextField txtStatus;
    public Label lblAllRoom;
    public Label lblUsedRoom;
    public Label lblRemainRoom;

    ReservationBO reservationBO = new ReservationBOImpl();

    public void initialize() throws IOException {

        lblAvailable.setText(".................");
        cmbGender.getItems().addAll("Male","Female");

        for (StudentDTO studentDTO : reservationBO.getAllStudent()) {
            cmbStudent.getItems().add(studentDTO.getStudentId());
        }

        for (RoomDTO roomDTO : reservationBO.getAllRoom()) {
            cmbRoom.getItems().add(roomDTO.getRoomTypeId());
        }

        cmbRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                Room room = null;
                try {
                    room = reservationBO.getRoom(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtRoomType.setText(room.getType());
                txtKeymoney.setText(String.valueOf(room.getKeyMoney()));
                txtQty.setText(String.valueOf(room.getQty()));

                try {
                    List<ReservedDTO> reserveDTOS = reservationBO.searchReservedRoomById(newValue);

                    int count = 0;
                    for (ReservedDTO reserveDTO : reserveDTOS) {
                        count++;
                    }
                    int remainQty = Integer.parseInt(txtQty.getText())-count;
                    lblUsedRoom.setText(String.valueOf(count));
                    lblRemainRoom.setText(String.valueOf(room.getQty()));

                    if(remainQty==0){
                        lblAvailable.setText("Un-Available");
                    }else{
                        lblAvailable.setText("Available");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        cmbStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                Student student = null;
                try {
                    student = reservationBO.getStudent(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtStudentName.setText(student.getName());
                txtStudentAddress.setText(student.getAddress());
                cmbGender.getSelectionModel().select(student.getGender());
                DOB.setValue(student.getDob());
            }
        });

        searchRoomQty();
    }

    private void searchRoomQty() {
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
    }

    public void btnRegisterStudentOnAction(ActionEvent actionEvent) throws IOException {
        if (lblAvailable.getText().equalsIgnoreCase("Available")){
            Student student = new Student();
            student.setStudentId(cmbStudent.getValue());

            Room room = new Room();
            room.setRoomTypeId(cmbRoom.getValue());
            reservationBO.registerStudent(new ReservedDTO(
                    txtRegisterId.getText(),
                    LocalDate.now(),
                    student,
                    room,
                    txtStatus.getText()
            ));
            clearAllFields();
            lblAvailable.setText("00");
            lblUsedRoom.setText("00");
            lblRemainRoom.setText("00");
        }else{
            new Alert(Alert.AlertType.WARNING,"You Can't Register Student for This Room").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    private void clearAllFields(){
        cmbStudent.setValue(null);
        cmbRoom.setValue(null);
        txtStatus.clear();
    }
}
