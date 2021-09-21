package ru.sacmi.temperatureservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "sensor_entity")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SensorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    String label;

    @Column(nullable = false, unique = true)
    String uuid;

    @Column(nullable = false)
    final LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    Collection<InMonthEntity> inMonth;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    Collection<InDayEntity> inDay;
}