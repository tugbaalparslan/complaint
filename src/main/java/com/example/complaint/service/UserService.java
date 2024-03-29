package com.example.complaint.service;


import com.example.complaint.communications.error.ErrorCodes;
import com.example.complaint.communications.messages.ErrorMessages;
import com.example.complaint.dao.UserRepository;
import com.example.complaint.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository; // userRepository bean to be injected by Spring IoC Container ; default bean scope is "Singleton". @Scope("prototype")

@Autowired // Mark the dependency, Spring IoC container is going to inject the bean to the client (client: User Service)

    public UserService(UserRepository userRepository){ // Constructor dependency injection is better than field injection, handles null pointer exception
    this.userRepository = userRepository;
}

public List<User> findAll(){
    return userRepository.findAll();
}


public User saveUser(User user){
    try {
        userRepository.save(user);
    } catch(Exception e) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ErrorMessages.getMessage(ErrorCodes.SERVER_ERROR));
    }
    return user;
}

public User getUser(int id){
    Optional<User> user = userRepository.findById(id);

    if(user.isPresent())
        return user.get();
    else
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found!");
}


public ResponseEntity<List<User>> searchUserByPhone(String phone) {
    List<User> users = userRepository.findByPhoneNumber(phone);
    if(users == null || users.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found!");

    return ResponseEntity.status(HttpStatus.OK).body(users);
}

public void deleteUser(int id){
    // check if user exists
    Optional<User> user = userRepository.findById(id);

    if(user.isPresent())
        userRepository.deleteById(id);
    else
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found!");

}

public void updateUser(int id, User user){
    Optional<User> optUser = userRepository.findById(id);

    if(optUser.isPresent()){
        User regUser = optUser.get();
        BeanUtils.copyProperties(user, regUser,"id");
        userRepository.save(regUser);
    }else
        userRepository.save(user);
}

public void deleteAll(){
    userRepository.deleteAll();
}


public void updateUserEmail(int id, String email){
    Optional<User> user = userRepository.findById(id);
    if(user.isPresent()){
        User regUser = user.get();
        regUser.setEmail(email);
        userRepository.save(regUser);
    }
    else
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found!");
}


}
