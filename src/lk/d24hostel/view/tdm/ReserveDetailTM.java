package lk.d24hostel.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDetailTM {
    String resId;
    LocalDate date;
    String studentId;
    String roomId;
    String status;
    String remainKeyMoney;
}
