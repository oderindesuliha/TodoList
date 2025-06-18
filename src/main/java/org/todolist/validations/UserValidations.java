package org.todolist.validations;

import org.todolist.data.models.User;
import org.todolist.dtos.requests.UserRegisterRequest;
import org.todolist.exceptions.UserExceptions;
import org.todolist.utils.Password;

import java.util.Optional;

public class UserValidations {
    public static void validateUser(UserRegisterRequest request) {
        if (request == null) {
            throw new UserExceptions("Registration fields cannot be empty");
        }
        validateNames(request.getFirstName(), request.getLastName(), request.getUserName());
        validateEmail(request.getEmail());
    }


    private static void validateNames(String firstName, String lastName, String userName) {
        if (firstName == null || firstName.trim().isEmpty() || !firstName.matches("^[A-Za-z\\s-']{2,50}$")) {
            throw new UserExceptions("First name must contain letters");
        }
        if(lastName == null || lastName.trim().isEmpty() || !lastName.matches("^[A-Za-z\\s-']{2,50}$")) {
            throw new UserExceptions("Last name must contain letters");
        }
        if(userName.isEmpty()) {
            throw new UserExceptions("Username cannot be empty");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new UserExceptions("Enter a valid email address");
        }
    }

    public void validateUserLoggedIn(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !user.get().isLoggedIn()) {
            throw new UserExceptions("User is not logged in");
        }
        User user = userRepository.findByUserName(request.getUsername());
        if (user == null) {
            throw new UserExceptions("User not found");
        }


        if (!user.isLoggedIn()) {
            throw new UserExceptions("User is not logged in");
        }

        if (!Password.checkPassword(request.getPassword(), user.getPassword())) {
            throw new UserExceptions("Invalid password");
        }
    }
   
}
