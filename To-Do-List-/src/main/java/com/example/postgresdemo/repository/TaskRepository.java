package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Task;
import com.example.postgresdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task getTaskById (Integer id);

    List<Task> findByUser(User user);

    List<Task> findByUserAndCompletedTrue(User user);

    List<Task> findByUserAndCompletedFalse(User user);
}
