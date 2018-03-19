package ru.randscheduler.web.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.randscheduler.data.user_data.UserData;
import ru.randscheduler.repository.UserRepository;
import ru.randscheduler.tools.UserCookieUtils;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ru.randscheduler.tools.UserCookieUtils.COOKIE_ID;

/**
 * Created by dimaMJ on 30.01.2018
 */
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginCtrl {

    private final UserRepository userRepository;

    @GetMapping
    public String getPage(@CookieValue(value = COOKIE_ID, required = false) String userId) {
        if (userId != null) return "redirect:/";
        return "/login";
    }

    @PostMapping
    @ResponseBody
    public boolean tryLogin(@RequestParam("userId") String userId,
                            boolean firstTime,
                            HttpServletResponse resp) {
        boolean exists = userRepository.exists(userId);

        if (exists) {
            if (firstTime) return false;
            else resp.addCookie(UserCookieUtils.createUserCookie(userId));
        } else {
            resp.addCookie(UserCookieUtils.createUserCookie(userId));
            userRepository.save(new UserData(userId));
        }

        return true;
    }

}
