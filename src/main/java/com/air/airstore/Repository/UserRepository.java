package com.air.airstore.Repository;

import com.air.airstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
