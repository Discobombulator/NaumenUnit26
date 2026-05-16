package ru.Batareya.NaumenUnit26.restController;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.Batareya.NaumenUnit26.service.UserService;

import java.util.Date;

/**
 * Главный контроллер приложения NauGram.
 * Обрабатывает запросы связанные с регистрацией, авторизацией
 * и подтверждением email через одноразовый код.
 */
@Controller
public class AuthController {

    private final UserService userService;

    /**
     * Конструктор контроллера.
     *
     * @param userService сервис для работы с пользователями
     */
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Отображает страницу авторизации.
     * Если передан параметр error=bad_credentials — добавляет сообщение об ошибке.
     *
     * @param error необязательный параметр ошибки из URL
     * @param model модель для передачи данных в шаблон
     * @return имя шаблона страницы авторизации
     */
    @GetMapping("/login")
    public String showAuthorizationPage(@RequestParam(required = false) String error, Model model) {
        if ("bad_credentials".equals(error)) {
            model.addAttribute("error", "Неверный логин или пароль");
        }
        return "authorization";
    }
}
