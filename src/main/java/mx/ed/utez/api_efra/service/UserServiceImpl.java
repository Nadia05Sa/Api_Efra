package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.DAO.UserDao;
import mx.ed.utez.api_efra.model.Empleados;
import mx.ed.utez.api_efra.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    private User user;

    @Override
    public List<User> getUsers(){
        return (List<User>)  userDao.findAll();
    }

    @Override
    public User getUserById(Long id){
        Optional<User> user = userDao.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public User saveUser(User user){
        return userDao.save(user);
    }

    @Override
    public User updateUser(Long id){
        User nuevoUser = getUserById(id);
        nuevoUser.setId(user.getId());
        return userDao.save(nuevoUser);
    }

    @Override
    public boolean deleteUser(Long id){
        if(userDao.existsById(id)){
            userDao.deleteById(id);
            return true;
        }
        return false;
    }
}