package com.upteam.auth.domain;

import com.upteam.auth.domain.domainenum.ActivityType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by vnikolaev on 27.12.2015.
 */
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @SequenceGenerator(name = "activityseq", sequenceName = "activity_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activityseq")
    private Long id;

    private Long systemUserId;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    private String description;

    private LocalDateTime activityTime;

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }
}
