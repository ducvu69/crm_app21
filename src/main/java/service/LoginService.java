package service;

import model.UserModel;
import repository.UserRepository;

import java.util.List;

public class LoginService {
    // Có bao nhiêu tính năng thì sẽ có bấy nhiêu hàm tương ứng trong class này
    // Phải có tham số của repository
    private UserRepository userRepository = new UserRepository();
    public boolean checkLogin(String email, String password){
        List<UserModel> list = userRepository.findByEmailAndPassword(email,password);
        return list.size() > 0;
    }
}
/*
    - Tạo ra một link /user load giao diện user-table.html
    - UserController lấy được danh sách user
 */