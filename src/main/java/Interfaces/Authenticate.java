package Interfaces;

import DTO.User;

public interface Authenticate {

    public User autenticar(String email, String senha);

}
