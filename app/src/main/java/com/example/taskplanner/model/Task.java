package com.example.taskplanner.model;//%% NEW FILE Task BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import com.example.taskplanner.controller.TaskController;
import com.example.taskplanner.view.TaskAdapter;

import java.sql.Date;

// line 2 "model.ump"
// line 36 "model.ump"
public class Task
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Task Attributes
  private String title;
  private String description;
  private Date dueDate;

  //Task State Machines
  public enum Status { Pending, InProgress, Completed }
  private Status status;

  //Task Associations
  private TaskController taskController;
  private TaskAdapter taskAdapter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Task(String aTitle, String aDescription, Date aDueDate, TaskController aTaskController, TaskAdapter aTaskAdapter)
  {
    title = aTitle;
    description = aDescription;
    dueDate = aDueDate;
    boolean didAddTaskController = setTaskController(aTaskController);
    if (!didAddTaskController)
    {
      throw new RuntimeException("Unable to create task due to taskController. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTaskAdapter = setTaskAdapter(aTaskAdapter);
    if (!didAddTaskAdapter)
    {
      throw new RuntimeException("Unable to create task due to taskAdapter. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setStatus(Status.Pending);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setDueDate(Date aDueDate)
  {
    boolean wasSet = false;
    dueDate = aDueDate;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public Date getDueDate()
  {
    return dueDate;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Pending:
        setStatus(Status.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean complete()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InProgress:
        setStatus(Status.Completed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }
  /* Code from template association_GetOne */
  public TaskController getTaskController()
  {
    return taskController;
  }
  /* Code from template association_GetOne */
  public TaskAdapter getTaskAdapter()
  {
    return taskAdapter;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTaskController(TaskController aTaskController)
  {
    boolean wasSet = false;
    if (aTaskController == null)
    {
      return wasSet;
    }

    TaskController existingTaskController = taskController;
    taskController = aTaskController;
    if (existingTaskController != null && !existingTaskController.equals(aTaskController))
    {
      existingTaskController.removeTask(this);
    }
    taskController.addTask(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTaskAdapter(TaskAdapter aTaskAdapter)
  {
    boolean wasSet = false;
    if (aTaskAdapter == null)
    {
      return wasSet;
    }

    TaskAdapter existingTaskAdapter = taskAdapter;
    taskAdapter = aTaskAdapter;
    if (existingTaskAdapter != null && !existingTaskAdapter.equals(aTaskAdapter))
    {
      existingTaskAdapter.removeTask(this);
    }
    taskAdapter.addTask(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TaskController placeholderTaskController = taskController;
    this.taskController = null;
    if(placeholderTaskController != null)
    {
      placeholderTaskController.removeTask(this);
    }
    TaskAdapter placeholderTaskAdapter = taskAdapter;
    this.taskAdapter = null;
    if(placeholderTaskAdapter != null)
    {
      placeholderTaskAdapter.removeTask(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dueDate" + "=" + (getDueDate() != null ? !getDueDate().equals(this)  ? getDueDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "taskController = "+(getTaskController()!=null?Integer.toHexString(System.identityHashCode(getTaskController())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "taskAdapter = "+(getTaskAdapter()!=null?Integer.toHexString(System.identityHashCode(getTaskAdapter())):"null");
  }
}

