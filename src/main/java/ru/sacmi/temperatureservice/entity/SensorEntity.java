package ru.sacmi.temperatureservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

@Table(name = "sensor_entity")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SensorEntity implements Serializable {

    @Column(nullable = false)
    final Instant createdAt = Instant.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;

    @NotEmpty
    @Column(nullable = false)
    String label;

    @NotEmpty
    @Column(nullable = false, unique = true)
    String uuid;

    @Column(nullable = false)
    Float maxTemp;

    @Column(nullable = false)
    Float minTemp;

    @Positive
    @Column(nullable = false)
    Integer sendDelay;

    @Positive
    @Column(nullable = false)
    Integer updateDelay;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    Collection<ReadingEntity> inDay;
}
