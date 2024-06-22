package com.itacademy.finalprod.project_.repo;



import com.itacademy.finalprod.project_.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String userName);
}
