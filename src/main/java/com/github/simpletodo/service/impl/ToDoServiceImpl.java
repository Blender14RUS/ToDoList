package com.github.simpletodo.service.impl;

import com.github.simpletodo.Runner;
import com.github.simpletodo.domain.Task;
import com.github.simpletodo.loader.Loader;
import com.github.simpletodo.service.ToDoService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

  private static final Logger LOG = Logger.getLogger(ToDoServiceImpl.class);

  private Loader loader;

  @Override
  public void add(Task task) {
    LOG.info("add task: " + task);
    boolean addStatus = loader.getSet()
                        .add(task);
    if (addStatus) {
      System.out.println("Task added");
    }
    loader.save();

  }

  @Override
  public void delete(String title) {
    LOG.info("delete task by title - " + title);
    List<Task> findTasks = new ArrayList<>();

    for (Task task : loader.getSet()) {
      if (task.getTitle().equals(title)) {
        findTasks.add(task);
      }
    }

    if (findTasks.size() > 1) {
      Runner.printAllTasks(findTasks);
      System.out.println("\nFound several task with this title "
          + "\nDelete all (YES) or delete concrete task by date (NO)");
      Scanner in = new Scanner(System.in);
      String s = in.nextLine();
      if (s.equalsIgnoreCase("no")) {
        LOG.info("user written NO (don't delete all tasks with this title, request a date)");
        System.out.println("Please enter the date in the format 'dd.MM.yyyy HH:mm'");
        String date = in.nextLine();
        LOG.info("user written a date: " + date);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateAndTime = LocalDateTime.parse(date, formatter);
        loader.getSet().remove(new Task(title, dateAndTime));
      } else {
        LOG.info("user written YES (yes delete all task with this title)");
        loader.getSet().removeAll(findTasks);
      }
    } else {
      loader.getSet().removeAll(findTasks);
    }

    loader.save();
  }

  @Override
  public Set<Task> getTasks() {
    LOG.info("getTasks");
    return loader.getSet();
  }

  @Override
  public Set<Task> find(String search) {
    LOG.info("search for tasks by param in description, param: " + search);
    Set<Task> findingResult = new HashSet<>();

    for (Task task : loader.getSet()) {
      if (task.getDescription().contains(search)) {
        findingResult.add(task);
      }
    }

    return findingResult;
  }
}
