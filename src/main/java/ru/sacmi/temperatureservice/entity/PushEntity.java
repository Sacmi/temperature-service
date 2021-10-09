package ru.sacmi.temperatureservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;

@Table(name = "token_entity")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PushEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    String token;

    @Column(nullable = false)
    final Instant createdAt = Instant.now();
}