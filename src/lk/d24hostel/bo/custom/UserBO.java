package lk.d24hostel.bo.custom;

import lk.d24hostel.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface UserBO extends SuperBO{
    ArrayList<UserDTO> getAllUser() throws IOException;

    boolean updateUser(UserDTO dto) throws IOException;


}
