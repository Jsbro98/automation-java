package org.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class UpdateLogger {

  // used for in class exception handling
  private static void logException(IOException e) throws IOException {
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

  public static void writeExceptionToLog(IOException e) {
    try {
      logException(e);
    } catch (IOException logEx) {
      System.out.println("Issue occurred writing error to log file");
      throw new RuntimeException(logEx.getMessage());
    }
  }
}
