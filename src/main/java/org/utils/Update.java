package org.utils;

import java.io.*;
import java.util.Calendar;

public class Update {
  public static void main(String[] args) {
    ProcessBuilder pb = createProcess();
    try {
      runProcess(pb);
    } catch (IOException e) {
      System.out.println("Error in executing commands");

      try {
        writeExceptionToLog(e);
      } catch (IOException innerErr) {
        System.out.println("Error creating log file");
        throw new RuntimeException(innerErr.getMessage());
      }

    }
  }


  // helper methods

  public static ProcessBuilder createProcess() {
    ProcessBuilder procBuilder = new ProcessBuilder();
    procBuilder.command("sudo", "apt", "update");
    procBuilder.command("sudo", "apt", "upgrade", "-y");
    procBuilder.directory(new File(System.getProperty("user.home")));

    return procBuilder;
  }

  public static void runProcess(ProcessBuilder pb) throws IOException {
    Process process = pb.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }

    reader.close();
  }

  public static void writeExceptionToLog(IOException e) throws IOException {
    File log = new File("/var/log/update.log");
    log.createNewFile();

    Calendar cal = Calendar.getInstance();
    PrintWriter writer = new PrintWriter(new FileWriter(log, true), true);

    writer.println();
    writer.print("Error at ");
    writer.println(
            cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH) + " " +
                    cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE)
    );

    e.printStackTrace(writer);
    writer.close();
  }
}