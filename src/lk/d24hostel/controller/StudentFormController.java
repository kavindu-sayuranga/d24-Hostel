package lk.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.d24hostel.bo.custom.StudentBO;
import lk.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.d24hostel.dto.StudentDTO;
import lk.d24hostel.util.ValidationUtil;
import lk.d24hostel.view.tdm.StudentTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StudentFormController {
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentContact;
    public JFXDatePicker DOB;
    public JFXComboBox<String> cmbGender;
    public TableView<StudentTM> tblStudent;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStudentAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public JFXButton btnAdd;

    StudentBO studentBO = new StudentBOImpl();

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    Pattern studentNamePattern = Pattern.compile("^[A-z ]{4,25}$");
    Pattern studentaddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
    Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}[- ]?)?\\d{10}$");

    public void initialize() throws IOException {

        storeValidations();

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        cmbGender.getItems().addAll("Male", "Female");

        getAllStudent();
    }

    private void storeValidations() {
        map.put(txtStudentName, studentNamePattern);
        map.put(txtStudentAddress, studentNamePattern);
        map.put(txtStudentContact, phonePattern);
    }

    public void getAllStudent() throws IOException {
        tblStudent.getItems().clear();
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();

        for (StudentDTO studentDTO : allStudent) {
            tblStudent.getItems().add(new StudentTM(
                    studentDTO.getStudentId(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getTelNo(),
                    studentDTO.getDob(),
                    studentDTO.getGender()
            ));
        }
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextField(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }

        }

    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) throws IOException {

        if (btnAdd.getText().equals("Add Student")) {
            boolean b = studentBO.saveStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtStudentAddress.getText(),
                    txtStudentContact.getText(),
                    DOB.getValue(),
                    cmbGender.getValue()
            ));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Added SuccessFully").show();

                tblStudent.getItems().add(new StudentTM(
                        txtStudentId.getText(),
                        txtStudentName.getText(),
                        txtStudentAddress.getText(),
                        txtStudentContact.getText(),
                        DOB.getValue(),
                        cmbGender.getValue()
                ));
                clearAllFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Went Wrong !!").show();
            }
        } else {
            studentBO.updateStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtStudentAddress.getText(),
                    txtStudentContact.getText(),
                    DOB.getValue(),
                    cmbGender.getValue()
            ));
            btnAdd.setText("Add Student");
            txtStudentId.setEditable(true);
            clearAllFields();
            getAllStudent();
        }
    }

    public void clearAllFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentContact.clear();
        cmbGender.setValue(null);
        DOB.setValue(null);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void menuUpdateOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        txtStudentId.setText(selectedItem.getStudentId());
        txtStudentName.setEditable(false);
        txtStudentName.setText(selectedItem.getName());
        txtStudentAddress.setText(selectedItem.getAddress());
        txtStudentContact.setText(selectedItem.getTelNo());
        DOB.setValue(selectedItem.getDob());
        cmbGender.setValue(selectedItem.getGender());

        btnAdd.setText("Update");

    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws IOException {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if (studentBO.deleteStudent(selectedItem.getStudentId())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted SuccessFully").show();
            getAllStudent();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Went Wring !!").show();
        }
    }
}
