package ru.Batareya.NaumenUnit26.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.Batareya.NaumenUnit26.model.User;


import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    /** Находит пользователей по имени */
    User findByUsername(String name);

    /** Находит пользователя по email (для авторизации) */
    User findByEmail(String email);

    @NonNull Optional<User> findById(@NonNull Long id);

}
