package org.example.kafkaProduce;

import com.beust.jcommander.Parameter;

public class Argument {
  @Parameter(
      names = {"--brokers"},
      description = "String: kafka brokers to connect to",
      required = true)
  String brokers;

  @Parameter(
      names = {"--topic"},
      description = "String: kafka topic name")
  String topic = "testing";

  @Parameter(
      names = {"--records"},
      description = "Long: number of records to produce")
  long records = 10000L;

  @Parameter(
      names = {"--recordSize"},
      description = "int: size in byte per record")
  int recordSize = 1024;

  @Parameter(
      names = "--producer.threads",
      description = "int: number of threads to produce records",
      hidden = true)
  int producerThreads = 10;
}
