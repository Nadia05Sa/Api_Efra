package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id);
    boolean deleteUser(Long id);
}
