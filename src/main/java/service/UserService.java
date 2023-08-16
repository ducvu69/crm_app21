package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();
    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }

    public List<RoleModel> getAllRole(){
        return roleRepository.findAll();
    }

    public boolean insertUser(String email, String password, String fullname, int role_id){
        return userRepository.insertUser(email, password, fullname, role_id);
    }

    public boolean deleteUser(int id){
        return userRepository.deleteById(id);
    }

    public boolean updateUser(String email, String password, String fullname, int role_id, int id){
        return userRepository.updateUser(email, password, fullname, role_id, id);
    }

}
