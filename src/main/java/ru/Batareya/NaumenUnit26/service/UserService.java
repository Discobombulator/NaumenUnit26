package ru.Batareya.NaumenUnit26.service;

import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.Batareya.NaumenUnit26.model.User;
import ru.Batareya.NaumenUnit26.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с пользователями.
 * Реализует {@link UserDetailsService} для интеграции со Spring Security.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор сервиса.
     *
     * @param userRepository репозиторий для работы с пользователями в БД
     * @param passwordEncoder энкодер для хэширования паролей
     */
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Загружает данные пользователя по email для Spring Security.
     *
     * @param username username пользователя
     * @return объект {@link UserDetails} с данными пользователя
     * @throws UsernameNotFoundException если пользователь с таким email не найден
     */
    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
    }

    /**
     * Находит пользователя по email.
     *
     * @param email email пользователя
     * @return объект {@link User} или null если не найден
     */
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
