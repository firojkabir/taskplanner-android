package com.example.taskplanner.controller;//%% NEW FILE TaskController BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import com.example.taskplanner.model.Task;
import com.example.taskplanner.view.TaskAdapter;

import java.util.*;
import java.sql.Date;

// line 18 "model.ump"
// line 41 "model.ump"
public class TaskController
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TaskController Associations
  private List<Task> tasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TaskController()
  {
    tasks = new ArrayList<Task>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Task getTask(int index)
  {
    Task aTask = tasks.get(index);
    return aTask;
  }

  public List<Task> getTasks()
  {
    List<Task> newTasks = Collections.unmodifiableList(tasks);
    return newTasks;
  }

  public int numberOfTasks()
  {
    int number = tasks.size();
    return number;
  }

  public boolean hasTasks()
  {
    boolean has = tasks.size() > 0;
    return has;
  }

  public int indexOfTask(Task aTask)
  {
    int index = tasks.indexOf(aTask);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTasks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Task addTask(String aTitle, String aDescription, Date aDueDate, TaskAdapter aTaskAdapter)
  {
    return new Task(aTitle, aDescription, aDueDate, this, aTaskAdapter);
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    TaskController existingTaskController = aTask.getTaskController();
    boolean isNewTaskController = existingTaskController != null && !this.equals(existingTaskController);
    if (isNewTaskController)
    {
      aTask.setTaskController(this);
    }
    else
    {
      tasks.add(aTask);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTask(Task aTask)
  {
    boolean wasRemoved = false;
    //Unable to remove aTask, as it must always have a taskController
    if (!this.equals(aTask.getTaskController()))
    {
      tasks.remove(aTask);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTaskAt(Task aTask, int index)
  {  
    boolean wasAdded = false;
    if(addTask(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTaskAt(Task aTask, int index)
  {
    boolean wasAdded = false;
    if(tasks.contains(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTaskAt(aTask, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=tasks.size(); i > 0; i--)
    {
      Task aTask = tasks.get(i - 1);
      aTask.delete();
    }
  }

  // line 21 "model.ump"
  public void addTask(String title, String description, Date dueDate){
    
  }

  // line 22 "model.ump"
  public void deleteTask(int position){
    
  }

  // line 23 "model.ump"
  public void updateTaskStatus(int position, String newStatus){
    
  }

}
