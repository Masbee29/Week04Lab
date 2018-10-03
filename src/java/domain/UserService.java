/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Mason
 */
public class UserService implements Serializable {

    public User login(String username, String password) {
        
        if((username.equals("adam") && password.equals("password")) || (username.equals("betty") && password.equals("password"))) {
            return new User(username, null);
        }
   
        return null;
    }
}
