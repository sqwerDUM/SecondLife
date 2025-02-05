package com.itacademy.finalprod.project_.service;
import com.itacademy.finalprod.project_.entity.User;
import com.itacademy.finalprod.project_.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Регистрация нового пользователя
    public User registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);  // Хешируем пароль
       User user = new User(username, encodedPassword);   // Создаем пользователя
        return userRepository.save(user);  // Сохраняем в базе данных
    }
}
