package ru.Batareya.NaumenUnit26.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность пользователя системы.
 *
 * <p>Представляет зарегистрированного пользователя, включая его основные
 * персональные данные, настройки профиля и роль в системе.</p>
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private LocalDateTime createdAt;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}