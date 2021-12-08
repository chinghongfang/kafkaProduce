package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.UnixStyleUsageFormatter;

public class ArgumentUtil {
  private ArgumentUtil() {}

  public static <T> T parseArgument(T newArgument, String[] args) {
    JCommander jc = JCommander.newBuilder().addObject(newArgument).build();
    jc.setUsageFormatter(new UnixStyleUsageFormatter(jc));
    try {
      jc.parse(args);
    } catch (ParameterException pe) {
      var sb = new StringBuilder();
      jc.getUsageFormatter().usage(sb);
      throw new ParameterException(pe.getMessage() + "\n" + sb);
    }
    return newArgument;
  }
}
