package org.example.kafkaProduce;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.kafka.clients.producer.ProducerRecord;

public class DataManager {
  private final String topic;
  private final long records;
  private final int recordSize;
  private final AtomicLong produced = new AtomicLong(0);

  public DataManager(String topic, long records, int recordSize) {
    this.topic = topic;
    this.records = records;
    this.recordSize = recordSize;
  }

  public Optional<ProducerRecord<String, String>> generateRecord() {
    long num = produced.getAndIncrement();
    if (num >= records) return Optional.empty();
    return Optional.of(
        new ProducerRecord<>(
            topic,
            String.format("key-%010d", num).concat(new String(new byte[recordSize - 14])),
            String.format("value-%010d", num).concat(new String(new byte[recordSize - 16]))));
  }
}
