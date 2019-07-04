package com.moody.moon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moody.moon.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
