package com.app.library.service;

import com.app.library.form.UserRegistrationForm;
import com.app.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationForm userRegistrationForm);
}
