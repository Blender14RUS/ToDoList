package com.github.simpletodo.service;

import com.github.simpletodo.domain.Task;
import java.util.Set;

public interface ToDoService {

  void add(Task task);

  void delete(String title);

  Set<Task> getTasks();

  Set<Task> find(String search);
}
