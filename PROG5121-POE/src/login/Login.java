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

    public boolean checkCellPhoneNumber(String cellNumber) {
        return false;
    }

    public String registerUser(String username, String password, String cellNumber) {
        return "";
    }

    public boolean loginUser(String username, String password) {
        return false;
    }

    public String returnLoginStatus(boolean status) {
        return "";
    }
}