package com.blog.postservice.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 80, nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Post(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
