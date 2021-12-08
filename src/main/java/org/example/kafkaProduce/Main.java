package org.example.kafkaProduce;

import java.util.Properties;
import java.util.concurrent.Executors;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.example.ArgumentUtil;

public class Main {
  public static void main(String[] args) {
    var param = ArgumentUtil.parseArgument(new Argument(), args);

    // Setting producer configs
    final Properties prop = new Properties();
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, param.brokers);

    // Initialize
    var dataManager = new DataManager(param.topic, param.records, param.recordSize);
    var service = Executors.newFixedThreadPool(param.producerThreads);

    // Run producers
    for (int i = 0; i < param.producerThreads; ++i) {
      service.execute(new Producer(prop, dataManager));
    }
    service.shutdown();
  }
}
