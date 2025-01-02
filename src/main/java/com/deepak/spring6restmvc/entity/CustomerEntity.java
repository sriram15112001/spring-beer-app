package com.deepak.spring6restmvc.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerEntity {
    @Id
    private UUID id;
    private String name;
    @Version
    private String version;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
