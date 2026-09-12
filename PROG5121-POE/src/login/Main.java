/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

/**
 *
 * @author HUAWEI
 */
public class Main {
    public static void main(String[] args) {
        Login login = new Login();

        System.out.println(login.checkUserName("kyl_1"));      
        System.out.println(login.checkUserName("kyle!!!!!!")); 
        
        System.out.println(login.checkPasswordComplexity("Ch&&sec@ke99!")); 
        System.out.println(login.checkPasswordComplexity("password"));      
        
        System.out.println(login.checkCellPhoneNumber("+27838968976")); 
        System.out.println(login.checkCellPhoneNumber("08966553"));
        
        System.out.println(login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"));
        System.out.println(login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976"));
        System.out.println(login.registerUser("kyl_1", "password", "+27838968976"));
        System.out.println(login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553"));
    }
}