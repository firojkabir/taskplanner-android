package com.example.taskplanner.view;//%% NEW FILE TaskAdapter BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import com.example.taskplanner.controller.TaskController;
import com.example.taskplanner.model.Task;

import java.util.*;
import java.sql.Date;

// line 26 "model.ump"
// line 47 "model.ump"
public class TaskAdapter
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TaskAdapter Associations
  private List<Task> tasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TaskAdapter()
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
  public Task addTask(String aTitle, String aDescription, Date aDueDate, TaskController aTaskController)
  {
    return new Task(aTitle, aDescription, aDueDate, aTaskController, this);
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    TaskAdapter existingTaskAdapter = aTask.getTaskAdapter();
    boolean isNewTaskAdapter = existingTaskAdapter != null && !this.equals(existingTaskAdapter);
    if (isNewTaskAdapter)
    {
      aTask.setTaskAdapter(this);
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
    //Unable to remove aTask, as it must always have a taskAdapter
    if (!this.equals(aTask.getTaskAdapter()))
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

  // line 29 "model.ump"
  public void bindTask(Task task, int position){
    
  }

  // line 30 "model.ump"
  public void notifyDataSetChanged(){
    
  }

}

