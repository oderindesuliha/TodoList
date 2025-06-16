//package org.todolist;
//
//
//import org.todolist.dtos.requests.UserRegisterRequest;
//import org.todolist.exceptions.UserExceptions;
//
//public class UserValidation {
//    public static void validateUserName(UserRegisterRequest request) {
//        if (request == null) {
//            throw new UserExceptions("Registration request cannot be null");
//        }
//        validateUserName(request.getUserName);
//        validateEmail(request.getEmail());
//    }
//
//
//    private static void validateUserName(String UserName) {
//        if (userNName == null || userName.trim().isEmpty() || !userName.matches("^[A-Za-z\\s-']{2,50}$")) {
//            throw new UserExceptions("First name must contain letters, spaces, or apostrophes (2-50 characters)");
//        }
//    }
//
//
//    private static void validateEmail(String email) {
//        if (email == null || email.trim().isEmpty() || !email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
//            throw new UserExceptions("Enter a valid email address");
//        }
//    }
//
//}