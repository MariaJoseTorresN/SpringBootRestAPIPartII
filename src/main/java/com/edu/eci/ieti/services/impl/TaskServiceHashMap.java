package com.edu.eci.ieti.services.impl;

import com.edu.eci.ieti.entities.Task;
import com.edu.eci.ieti.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskServiceHashMap implements TaskService {
    private HashMap<String, Task> tasks = new HashMap<>();

    @Override
    public Task create(Task task) {
        if (tasks.containsKey(task.getId())){
            return null;
        }
        tasks.put(task.getId(),task);
        return task;
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> getAll() {
        List<Task> allTasks = new ArrayList<>();
        for (String id: tasks.keySet()) {
            allTasks.add(tasks.get(id));
        }
        return allTasks;
    }

    @Override
    public boolean deleteById(String id) {
        Boolean flag = false;
        if (tasks.containsKey(id)){
            tasks.remove(id);
            flag = true;
        }
        return flag;
    }

    @Override
    public Task update(Task task, String id) {
        if (!tasks.containsKey(id)){
            return null;
        }
        deleteById(id);
        return create(task);
    }
}
