package lk.d24hostel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    String roomTypeId;
    String type;
    double keyMoney;
    int qty;
}
