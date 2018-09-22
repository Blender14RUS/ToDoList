package com.github.simpletodo.loader;

import com.github.simpletodo.domain.Task;
import java.util.Set;

public interface Loader {

  void save();

  void init();

  Set<Task> getSet();

}
