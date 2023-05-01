package app.zinu.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.zinu.com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}