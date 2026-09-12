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

        System.out.println(login.checkUserName("kyl_1"));      // should print: true
        System.out.println(login.checkUserName("kyle!!!!!!")); // should print: false
        
        System.out.println(login.checkPasswordComplexity("Ch&&sec@ke99!")); // should print: true
        System.out.println(login.checkPasswordComplexity("password"));      // should print: false
    }
}