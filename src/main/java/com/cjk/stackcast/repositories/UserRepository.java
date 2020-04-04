package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
