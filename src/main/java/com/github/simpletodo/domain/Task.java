package com.github.simpletodo.domain;

import java.io.Serializable;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"description"})
public class Task implements Comparable<Task>, Serializable {

  private static final long serialVersionUID = 1L;

  @NonNull
  private String title;

  private String description;

  @NonNull
  private LocalDateTime dateAndTime;

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
    return title + '\t' + description + '\t' + dateAndTime.toString(formatter);
  }

  @Override
  public int compareTo(Task o) {
    return title.compareTo(o.getTitle()) + dateAndTime.compareTo(o.getDateAndTime());
  }
}




