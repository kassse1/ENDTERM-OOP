package com.example.postgresdemo.service;

import com.example.postgresdemo.model.Task;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    public List<Task> getTaskList(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> getInactiveTaskList(User user) {
        return taskRepository.findByUserAndCompletedTrue(user);
    }

    public List<Task> getActiveTaskList(User user) {
        return taskRepository.findByUserAndCompletedFalse(user);
    }

    public Task getTaskInfo(Integer id) {
        return taskRepository.getTaskById(id);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public User getCurrentUser() {
        String username = securityService.findUserName();
        return userService.findByUsername(username);
    }

}
