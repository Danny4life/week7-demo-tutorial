package com.osiki.RegLogPageDemo.repository;

import com.osiki.RegLogPageDemo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByLoginAndPassword(String login, String password);
    Optional<UserModel> findFirstByLogin(String login);

    //Optional<UserModel> findFirstByLogin(String login);
}
