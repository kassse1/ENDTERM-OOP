package com.example.postgresdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
@SequenceGenerator(name = "SEQ_TASK", sequenceName = "SEQ_TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private boolean completed;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Task() {
    }

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", completed=" + completed + "]";
    }

}


