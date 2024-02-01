package tech.rent.be.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tech.rent.be.entity.Users;

@Component
public class AccountUtils {

    public Users getCurrentUser(){
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
