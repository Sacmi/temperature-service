package ru.sacmi.temperatureservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Table(name = "reading_entity")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;

    @JsonIgnore
    @ManyToOne
    SensorEntity sensor;

    @Column(nullable = false)
    Float temperature;

    @Column(nullable = false)
    final Instant timestamp = Instant.now();
}