package org.disx;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends PanacheEntity {

    @Column(name = "content", nullable = false, length = 4000)
    private String content;

    @Column(name = "username", nullable = false, length = 280)
    private String username;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "disx_id", nullable = false)
    private Long disxId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
