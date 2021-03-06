package io.superflat

import java.time.{Instant, LocalDate, ZoneId}

import com.google.protobuf.timestamp.Timestamp

package object lagompb {

  implicit class Timestamps(timestamp: Timestamp) {

    /**
     * converts a protocol buffer timestamp to java Instant date
     *
     * @return java Instant date
     */
    def toInstant: Instant =
      Instant.ofEpochSecond(timestamp.seconds, timestamp.nanos)

    /**
     * converts a protocol buffer timestamp to java Instant date based upon a given time zone
     *
     * @param zoneId the time zone
     * @return java Instant date
     */
    def toInstant(zoneId: ZoneId): Instant =
      Instant
        .ofEpochSecond(timestamp.seconds, timestamp.nanos)
        .atZone(zoneId)
        .toInstant

    /**
     * converts a protocol buffer timestamp to java local date based upon a given time zone
     *
     * @param zoneId the time zone id
     * @return  java local date
     */
    def toLocalDate(zoneId: ZoneId): LocalDate =
      Instant
        .ofEpochSecond(timestamp.seconds, timestamp.nanos)
        .atZone(zoneId)
        .toLocalDate
  }

  implicit class Instants(instant: Instant) {

    /**
     * converts a java Instant date to a google protocol buffer timestamp
     *
     * @return Protobuf timestamp
     */
    def toTimestamp: Timestamp =
      Timestamp()
        .withNanos(instant.getNano)
        .withSeconds(instant.getEpochSecond)
  }
}
