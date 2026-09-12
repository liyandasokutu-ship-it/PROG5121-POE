/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

/**
 *
 * @author HUAWEI
 */
public class Login {
    
    private String registeredUsername;
    private String registeredPassword;
    private String registeredFirstName;
    private String registeredLastName;

    public boolean checkUserName(String username) {
    return username.contains("_") && username.length() <= 5;
}

    public boolean checkPasswordComplexity(String password) {
    boolean hasLength = password.length() >= 8;
    boolean hasCapital = false;
    boolean hasNumber = false;
    boolean hasSpecial = false;

    for (char c : password.toCharArray()) {
        if (Character.isUpperCase(c)) {
            hasCapital = true;
        } else if (Character.isDigit(c)) {
            hasNumber = true;
        } else if (!Character.isLetterOrDigit(c)) {
            hasSpecial = true;
        }
    }

    return hasLength && hasCapital && hasNumber && hasSpecial;
}

    // Regex pattern adapted from: https://www.baeldung.com/java-regex-validate-phone-numbers
    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.matches("^\\+\\d{1,3}\\d{7,10}$");
}

    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
    if (!checkUserName(username)) {
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }
    if (!checkPasswordComplexity(password)) {
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }
    if (!checkCellPhoneNumber(cellNumber)) {
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

    registeredUsername = username;
    registeredPassword = password;
    registeredFirstName = firstName;
    registeredLastName = lastName;

    return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
}

    public boolean loginUser(String username, String password) {
    return username.equals(registeredUsername) && password.equals(registeredPassword);
}
    
    public String returnLoginStatus(boolean status) {
    if (status) {
        return "Welcome " + registeredFirstName + ", " + registeredLastName + " it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }
}
}    

    