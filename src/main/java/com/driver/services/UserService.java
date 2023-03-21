package com.driver.services;
import com.driver.models.User;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String username, String password){

        User user=new User();
        user.setUserName(username);
        user.setPassword(password);

        User newUser=userRepository.save(user);


        return newUser;
    }

    public void deleteUser(int userId){

        userRepository.deleteById(userId);

    }

    public User updateUser(Integer id, String password){

        User user=userRepository.findById(id).get();

        user.setPassword(password);

        userRepository.save(user);

        User updatedUser=userRepository.findById(id).get();

        return updatedUser;
    }
}
