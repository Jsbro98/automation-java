package org.utils;

import java.io.*;

public class Update {
  public static void main(String[] args) {
    UpdateProcessManager pm = new UpdateProcessManager();

    try {
      pm.createAndRunProcess();
    } catch (IOException e) {
      System.out.println("Error in executing commands");
      UpdateLogger.writeExceptionToLog(e);
    }

  }
}