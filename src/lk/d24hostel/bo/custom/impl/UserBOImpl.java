package lk.d24hostel.bo.custom.impl;

import lk.d24hostel.bo.custom.UserBO;
import lk.d24hostel.dao.custom.UserDAO;
import lk.d24hostel.dao.custom.impl.UserDAOImpl;
import lk.d24hostel.dto.UserDTO;
import lk.d24hostel.entity.User;

import java.io.IOException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = new UserDAOImpl();
    @Override
    public ArrayList<UserDTO> getAllUser() throws IOException {
        ArrayList<User> all = userDAO.getAll();

        ArrayList<UserDTO> ud = new ArrayList<>();


        for (User user : all) {
            ud.add(new UserDTO(
                    user.getUserId(),
                    user.getName(),
                    user.getUserName(),
                    user.getPassword()
            ));
        }

        return ud;
    }

    @Override
    public boolean updateUser(UserDTO dto) throws IOException {
        return userDAO.update(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }
}
