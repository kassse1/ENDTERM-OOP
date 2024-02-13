package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Task;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @InjectMocks
    private TaskController controller;

    @Mock
    private TaskService taskService;

    @Test
    public void shouldGetAllTasks() {
        User user = new User();
        when(taskService.getCurrentUser()).thenReturn(user);

        ModelAndView modelAndView = controller.getAllTasks();

        assertThat(modelAndView.getViewName(), equalTo("tasks"));
        assertTrue(modelAndView.getModelMap().containsAttribute("tasks"));
        assertTrue(modelAndView.getModelMap().containsAttribute("activeList"));
        assertTrue(modelAndView.getModelMap().containsAttribute("inactiveList"));
    }

    @Test
    public void shouldReturnNewTaskView() {
        ModelAndView modelAndView = controller.newTask();

        assertThat(modelAndView.getViewName(), equalTo("taskform"));
        assertTrue(modelAndView.getModelMap().containsAttribute("task"));
    }

    @Test
    public void shouldGetTaskInfo() {
        Task taskInfo = controller.getTaskInfo(1);
        verify(taskService,times(1)).getTaskInfo(1);
        assertEquals(taskInfo, taskService.getTaskInfo(1));
    }

    @Test
    public void shouldSaveTask() {
        User user = new User();
        Task task = new Task();
        task.setId(3);
        when(taskService.getCurrentUser()).thenReturn(user);

        String redirectUrl = controller.saveTask(task);

        verify(taskService,times(1)).saveTask(task);
        assertThat(redirectUrl, equalTo("redirect:/task/3"));
    }

    @Test
    public void shouldEditTask() {
        ModelAndView modelAndView = controller.edit(anyInt());

        assertThat(modelAndView.getViewName(), equalTo("taskform"));
        assertTrue(modelAndView.getModelMap().containsAttribute("task"));
    }

    @Test
    public void shouldDeleteTask() {
        controller.deleteTask(anyInt());
        verify(taskService,times(1)).deleteTask(anyInt());
    }

    @Test
    public void shouldCompleteTask() {
        Task task = new Task();
        task.setId(3);
        when(taskService.getTaskInfo(task.getId())).thenReturn(task);

        controller.completeTask(task.getId());

        verify(taskService,times(1)).saveTask(task);
        assertTrue(task.isCompleted());
    }

    @Test
    public void shouldReopenTask() {
        Task task = new Task();
        task.setId(3);
        when(taskService.getTaskInfo(task.getId())).thenReturn(task);

        controller.reopenTask(task.getId());

        verify(taskService,times(1)).saveTask(task);
        assertFalse(task.isCompleted());
    }

}