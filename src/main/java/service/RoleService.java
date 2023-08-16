package service;

import repository.RoleRepository;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();

    public boolean insertRole(String name, String description){
        return roleRepository.insertRole(name, description);
    }

    public boolean deleteRole(Integer id){
        return roleRepository.deleteById(id);
    }
}
