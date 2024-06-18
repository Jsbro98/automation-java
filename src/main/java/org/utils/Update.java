package org.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Update {
  public static void main(String[] args) {
    ProcessBuilder procBuilder = new ProcessBuilder();
    procBuilder.command("sudo", "apt", "update");
    procBuilder.command("sudo", "apt", "upgrade");
    procBuilder.directory(new File(System.getProperty("user.home")));
    try {
      Process process = procBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }

      reader.close();
    } catch (IOException e) {
      System.out.println("Error in executing commands");
      e.printStackTrace();
    }
  }
}