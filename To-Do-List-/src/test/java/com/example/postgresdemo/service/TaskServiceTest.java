package com.example.postgresdemo.service;

import com.example.postgresdemo.model.Task;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SecurityService securityService;

    @Mock
    private UserService userService;

    @Test
    public void shouldGetTaskList() {
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("task1");

        Task task2 = new Task();
        task2.setId(2);
        task2.setName("task2");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        User user = new User();
        user.setTasks(Arrays.asList(task1,task2));

        when(taskRepository.findByUser(user)).thenReturn(taskList);

        List<Task> getTaskList = service.getTaskList(user);

        assertEquals(getTaskList, taskList);
        assertThat(getTaskList.size(), equalTo(2));
    }

    @Test
    public void shouldGetInactiveTaskList() {
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("task1");
        task1.setCompleted(false);

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);

        User user = new User();
        user.setTasks(Arrays.asList(task1));

        when(taskRepository.findByUserAndCompletedFalse(user)).thenReturn(taskList);

        service.getInactiveTaskList(user);

        assertThat(taskList.size(), equalTo(1));
    }

    @Test
    public void shouldGetActiveTaskList() {
        Task task1 = new Task();

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);

        User user = new User();
        user.setTasks(Arrays.asList(task1));

        when(taskRepository.findByUserAndCompletedTrue(user)).thenReturn(taskList);

        service.getActiveTaskList(user);

        assertEquals(taskList, taskList);
        assertThat(taskList.size(), equalTo(1));
    }

    @Test
    public void shouldGetTaskInfo() {
        service.getTaskInfo(anyInt());

        verify(taskRepository).getTaskById(anyInt());
    }

    @Test
    public void shouldDeleteTask() {
        service.deleteTask(anyInt());

        verify(taskRepository).deleteById(anyInt());
    }

    @Test
    public void shouldSaveTask() {
        Task task = new Task();

        service.saveTask(task);

        verify(taskRepository).save(task);
    }

    @Test
    public void shouldGetCurrentUser() {
        String username = "serhat";
        User user = new User();
        user.setUsername(username);
        when(securityService.findUserName()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);

        User currentUser = service.getCurrentUser();

        verify(securityService).findUserName();
        verify(userService).findByUsername("serhat");
        assertThat(currentUser, equalTo(user));
    }

}