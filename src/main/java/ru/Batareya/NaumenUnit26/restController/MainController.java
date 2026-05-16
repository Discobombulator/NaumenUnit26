package ru.Batareya.NaumenUnit26.restController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Отображает главную страницу приложения.
     *
     * @param model модель для передачи данных в шаблон
     * @return имя шаблона главной страницы
     */
    @GetMapping("/")
    public String showMainPage(Model model) {
        return "mainPage";
    }

}
