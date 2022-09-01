package com.edu.eci.ieti.controller;

import com.edu.eci.ieti.dto.TaskDto;
import com.edu.eci.ieti.entities.Task;
import com.edu.eci.ieti.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(){
        List<TaskDto> tasks = new ArrayList<>();
        List<Task> taskList = taskService.getAll();
        for (Task task:taskList) {
            tasks.add(task.toDto());
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<TaskDto> findById( @PathVariable String id ) {
        Task task = taskService.findById(id);
        if (task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task.toDto());
    }

    @PostMapping
    public ResponseEntity<TaskDto> create( @RequestBody TaskDto taskDto ) {
        Task task = taskService.create(taskDto.toEntity());
        if (task == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(task.toDto());
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<TaskDto> update( @RequestBody TaskDto task, @PathVariable String id ) {
        Task newTask = taskService.update(task.toEntity(),id);
        if (newTask == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newTask.toDto());
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        if (taskService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.deleteById(id));
    }
}
