package com.github.simpletodo.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Tasks implements Serializable {

  private static final long serialVersionUID = 1L;

  public Tasks() {
    this.taskSet = new TreeSet<>();
  }

  @NonNull
  Set<Task> taskSet;
}
