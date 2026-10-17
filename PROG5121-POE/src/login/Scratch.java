/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

public class Scratch {
    public static void main(String[] args) {
        Message msg = new Message();
        System.out.println(msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?"));
        System.out.println(msg.checkMessageLength("a".repeat(250)));
        System.out.println(msg.checkMessageLength("a".repeat(255)));
    }
}