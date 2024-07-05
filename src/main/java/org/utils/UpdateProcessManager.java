package org.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdateProcessManager {
  private final ProcessBuilder pb;

  public UpdateProcessManager() {
    pb = new ProcessBuilder();
  }

  public void createAndRunProcess() throws IOException {
    createProcess();
    runProcess();
  }

  private void createProcess() {
    pb.command("sudo", "apt", "update");
    pb.command("sudo", "apt", "upgrade", "-y");
    pb.directory(new File(System.getProperty("user.home")));
  }

  private void runProcess() throws IOException {
    Process process = pb.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }

    reader.close();
  }
}
