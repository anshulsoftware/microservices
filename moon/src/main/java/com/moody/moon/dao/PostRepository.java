package com.moody.moon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moody.moon.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
