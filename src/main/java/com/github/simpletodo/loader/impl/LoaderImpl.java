package com.github.simpletodo.loader.impl;

import com.github.simpletodo.domain.Task;
import com.github.simpletodo.domain.Tasks;
import com.github.simpletodo.loader.Loader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import org.apache.log4j.Logger;

public class LoaderImpl implements Loader {

  private static final String FILE = "data.ser";
  public static final Logger LOG = Logger.getLogger(LoaderImpl.class);

  private Tasks tasks;

  @Override
  public void save() {
    LOG.info("save");
    try (FileOutputStream outputStream = new FileOutputStream(FILE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

      objectOutputStream.writeObject(tasks);

    } catch (Exception e) {
      LOG.error("Exception thrown during data save: " + e.toString());
    }
  }

  @Override
  public void init() {
    LOG.info("init");
    tasks = new Tasks();
    try (FileInputStream inputStream = new FileInputStream(FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

      Tasks readTasks = (Tasks) objectInputStream.readObject();

      this.tasks = readTasks;

    } catch (Exception e) {
      LOG.error("Exception thrown during data init: " + e.toString());
    }

  }

  @Override
  public Set<Task> getSet() {
    LOG.info("getSet");
    return this.tasks.getTaskSet();
  }
}
