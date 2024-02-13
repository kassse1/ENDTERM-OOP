package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Task;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/tasks")
    public ModelAndView getAllTasks() {
        User currentUser = taskService.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("tasks");
        modelAndView.addObject("tasks", taskService.getTaskList(currentUser));
        modelAndView.addObject("activeList", taskService.getActiveTaskList(currentUser));
        modelAndView.addObject("inactiveList", taskService.getInactiveTaskList(currentUser));
        return modelAndView;
    }

    @RequestMapping("/task")
    public Task getTaskInfo(@PathVariable Integer id) {
        return taskService.getTaskInfo(id);
    }

    @RequestMapping("task/new")
    public ModelAndView newTask(){
        ModelAndView modelAndView = new ModelAndView("taskform");
        modelAndView.addObject("task", new Task());
        return modelAndView;
    }

    @RequestMapping(value = "task", method = RequestMethod.POST)
    public String saveTask(Task task){
        User currentUser = taskService.getCurrentUser();
        task.setUser(currentUser);
        taskService.saveTask(task);
        return "redirect:/task/" + task.getId();
    }

    @RequestMapping("task/{id}")
    public ModelAndView showProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("taskinfo");
        modelAndView.addObject("task", taskService.getTaskInfo(id));
        return modelAndView;
    }

    @RequestMapping("task/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("taskform");
        modelAndView.addObject("task", taskService.getTaskInfo(id));
        return modelAndView;
    }

    @RequestMapping("task/delete/{id}")
    public String deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @RequestMapping("task/complete/{id}")
    public String completeTask(@PathVariable Integer id){
        Task task = taskService.getTaskInfo(id);
        task.setCompleted(true);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping("task/reopen/{id}")
    public String reopenTask(@PathVariable Integer id){
        Task task = taskService.getTaskInfo(id);
        task.setCompleted(false);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }
}
