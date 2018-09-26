package com.github.simpletodo;

import com.github.simpletodo.domain.Task;
import com.github.simpletodo.loader.Loader;
import com.github.simpletodo.loader.impl.LoaderImpl;
import com.github.simpletodo.service.ToDoService;
import com.github.simpletodo.service.impl.ToDoServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Runner {

  private static final Logger LOG = Logger.getLogger(Runner.class);

  void run() {
    Scanner in = new Scanner(System.in);
    Loader loader = new LoaderImpl();
    loader.init();
    ToDoService toDoService = new ToDoServiceImpl(loader);
    printHelp();
    while (true) {
      try {
        System.out.print(">");

        List<String> argsList = splitInputString(in.nextLine());

        if (argsList.size() == 0)
          continue;

        switch (argsList.get(0)) {
          case "add":
            if (argsList.size() > 4) {
              DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
              LocalDateTime dateAndTime = LocalDateTime.parse(argsList.get(3) + " " +  argsList.get(4), formatter);
              toDoService.add(new Task(argsList.get(1), argsList.get(2), dateAndTime));
            } else {
              LOG.error("Not Enough Input Arguments: " + argsList.toString());
              System.out.println("not enough input arguments, use 'help' for tips");
            }
            break;
          case "delete":
            if (argsList.size() > 1) {
              toDoService.delete(argsList.get(1));
            } else {
              LOG.error("Not Enough Input Arguments: " + argsList.toString());
              System.out.println("not enough input arguments, use 'help' for tips");
            }
            break;
          case "print":
            printAllTasks(toDoService.getTasks());
            break;
          case "find":
            if (argsList.size() > 1) {
              printAllTasks(toDoService.find(argsList.get(1)));
            } else {
              LOG.error("Not Enough Input Arguments: " + argsList.toString());
              System.out.println("not enough input arguments, use 'help' for tips");
            }
            break;
          case "help":
            printHelp();
            break;
          default:
            System.out.println("use 'help' for tips");
            LOG.info("bad command: " + argsList.toString());
            break;
        }
      } catch (IllegalArgumentException e){
        System.out.println("Illegal Argument, use 'help' for tips");
        LOG.error("Input error " + e.toString());
      }
    }
  }

  private List<String> splitInputString(String inputString) {
    List<String> argsList = new ArrayList<>();
    String[] arr = inputString.split("'");
    for(int i=0; i<arr.length; i++) {
      arr[i] = arr[i].trim();
      if ("".equals(arr[i])) {
        continue;
      }
      if (i % 2 == 0) {
        String[] tmp = arr[i].split(" ");
        Collections.addAll(argsList, tmp);
      } else {
        argsList.add("'" + arr[i] + "'");
      }
    }
    return argsList;
  }

  private void printHelp() {
    System.out.println("Commands:\n"
        + "add - for add new task\n"
        + "\tsyntax: add 'title' 'description' 'date in format dd.mm.yyyy hh:mm'\n"
        + "delete - for delete task by title\n"
        + "\tsyntax: delete 'title'\n"
        + "print - for output by date\n"
        + "\tsyntax: print\n"
        + "find - search tasks by word in the task description\n"
        + "\tsyntax: find 'word'");
  }

  public static void printAllTasks(Collection<Task> tasks) {
    LocalDate localDate = LocalDate.parse("9999-12-31");

    List<Task> sets = new ArrayList<>(tasks);
    Collections.sort(sets, new Comparator<Task>() {
      public int compare(Task a, Task b) {
        return a.getDateAndTime().compareTo(b.getDateAndTime());
      }});

    for (Task task : sets) {
      if (!localDate.equals(task.getDateAndTime().toLocalDate())) {
        System.out.println(task.getDateAndTime().toLocalDate());
      }
      System.out.println("\t" + task);
      localDate = task.getDateAndTime().toLocalDate();
    }
  }
}

