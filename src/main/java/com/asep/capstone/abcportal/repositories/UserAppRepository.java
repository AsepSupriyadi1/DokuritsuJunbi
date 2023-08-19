package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByEmail(String email);

}
