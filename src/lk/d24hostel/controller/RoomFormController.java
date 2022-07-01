package lk.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import lk.d24hostel.bo.custom.RoomBO;
import lk.d24hostel.bo.custom.impl.RoomBOImpl;
import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.entity.Room;
import lk.d24hostel.view.tdm.RoomTM;

import java.io.IOException;

public class RoomFormController {
    public JFXComboBox<String> cmbRoomType;
    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeymoney;
    public JFXTextField txtRoomId;
    public JFXTextField txtQty;
    public TableView<RoomTM> tblRoom;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeymoney;
    public TableColumn colQty;
    public JFXCheckBox checkType;
    public JFXCheckBox checkId;
    public JFXButton btnAddRoomType;
    public Rectangle recId;
    public Rectangle recType;
    public JFXButton btnAddRoomId;
    public JFXButton btnAdd;

    RoomBO roomBO = new RoomBOImpl();

    public void initialize() throws IOException {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        cmbRoomId.setEditable(false);

        btnAddRoomType.setDisable(true);
        txtRoomType.setDisable(true);
        recType.setDisable(true);

        btnAddRoomId.setDisable(true);
        txtRoomId.setDisable(true);
        recId.setDisable(true);


        txtKeymoney.setEditable(false);


        loadAllRooms();
        setCmbRoomTypes();
        addRoomTypeSelectListener();
        setCmbRoomIds();


        enableDisableCheckBox(checkType, btnAddRoomType, txtRoomType, recType,cmbRoomType);

        enableDisableCheckBox(checkId, btnAddRoomId, txtRoomId, recId,cmbRoomId);
    }

    private void enableDisableCheckBox(JFXCheckBox checkBox, JFXButton btnAdd, JFXTextField txtRoom, Rectangle rectangle,JFXComboBox combo) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnAdd.setDisable(false);
                txtRoom.setDisable(false);
                rectangle.setDisable(false);

                txtKeymoney.setEditable(true);
                combo.setValue(null);

                if(checkBox.getText().equals("Add new Room Type")){
                    txtKeymoney.clear();
                }

            } else {
                btnAdd.setDisable(true);
                txtRoom.setDisable(true);
                rectangle.setDisable(true);
                txtKeymoney.setEditable(false);
            }
        });
    }

    private void addRoomTypeSelectListener() {
        cmbRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    for (Room room : roomBO.getRoomDataFromType(newValue)) {

                        cmbRoomId.getSelectionModel().select(room.getRoomTypeId());
                        txtKeymoney.setText(String.valueOf(room.getKeyMoney()));

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public void loadAllRooms() throws IOException {
        tblRoom.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            tblRoom.getItems().add(new RoomTM(
                    roomDTO.getRoomTypeId(),
                    roomDTO.getType(),
                    roomDTO.getKeyMoney(),
                    roomDTO.getQty()
            ));
        }

    }

    private void setCmbRoomTypes() throws IOException {
        cmbRoomType.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            cmbRoomType.getItems().add(roomDTO.getType());
        }

    }

    private void setCmbRoomIds() throws IOException {
        cmbRoomId.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            cmbRoomId.getItems().add(roomDTO.getRoomTypeId());
        }

    }

    public void btnAddRoomTypeOnAction(ActionEvent actionEvent) {
        cmbRoomType.getItems().add(txtRoomType.getText());
        cmbRoomType.getSelectionModel().select(txtRoomType.getText());

        cmbRoomId.setValue(null);
        cmbRoomId.setDisable(true);
        checkId.selectedProperty().setValue(true);
        checkType.selectedProperty().setValue(false);
    }

    public void btnAddRoomIdOnAction(ActionEvent actionEvent) {
        cmbRoomId.getItems().add(txtRoomId.getText());
        cmbRoomId.getSelectionModel().select(txtRoomId.getText());
        cmbRoomId.setDisable(false);
        checkId.selectedProperty().setValue(false);
    }

    public void menuUpdateOnAction(ActionEvent actionEvent) {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();

        cmbRoomId.getSelectionModel().select(selectedItem.getRoomTypeId());
        cmbRoomId.setDisable(true);

        cmbRoomType.getSelectionModel().select(selectedItem.getType());
        txtKeymoney.setText(String.valueOf(selectedItem.getKeyMoney()));
        txtQty.setText(String.valueOf(selectedItem.getQty()));
        txtKeymoney.setEditable(true);

        btnAdd.setText("Update");
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws IOException {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();

        if (roomBO.deleteRoom(selectedItem.getRoomTypeId())){
            new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted SuccessFully").show();
            loadAllRooms();
            setCmbRoomTypes();
            setRoomIds();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong !!").show();
        }
    }

    private void setRoomIds() throws IOException {
        cmbRoomId.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            cmbRoomId.getItems().add(roomDTO.getRoomTypeId());
        }
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) throws IOException {
        boolean b=true;
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            if (roomDTO.getRoomTypeId().equals(cmbRoomId.getValue())){
                b=false;
            }
        }
        if (b) {
            roomBO.saveRoom(new RoomDTO(
                    cmbRoomId.getValue(),
                    cmbRoomType.getValue(),
                    Double.parseDouble(txtKeymoney.getText()),
                    Integer.parseInt(txtQty.getText())
            ));
            clearAllFields();
            loadAllRooms();
        }else{
            Room room = roomBO.getRoom(cmbRoomId.getValue());
            roomBO.updateRoom(new RoomDTO(
                    cmbRoomId.getValue(),
                    cmbRoomType.getValue(),
                    Double.parseDouble(txtKeymoney.getText()),
                    (room.getQty()+(Integer.parseInt(txtQty.getText()))
                    )));
            clearAllFields();
            loadAllRooms();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void clearAllFields(){
        cmbRoomType.setValue(null);
        txtRoomType.clear();
        txtKeymoney.clear();
        cmbRoomId.setValue(null);
        txtRoomId.clear();
        txtQty.clear();
    }
}
