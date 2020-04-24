package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDaoRepository extends JpaRepository<DAOUser,Long> {

    Optional<DAOUser> findUserByUserName(String userName);
}
