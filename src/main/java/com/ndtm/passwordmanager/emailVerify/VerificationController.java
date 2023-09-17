package com.ndtm.passwordmanager.emailVerify;


import com.ndtm.passwordmanager.userActions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerificationController {

    @Autowired
    UserService userService;

    @GetMapping("/verify")
    public String verificationProcess(@Param("verificationToken") String verificationToken) {
        if(userService.checkLoginFieldForCorrectAndNotNull(UserService.currentVerificationUser.getLogin()) &&
                UserService.currentVerificationUser.getVerificationToken().equals(verificationToken)) {
            // тут будет показываться html страница с завершением регистрации

            userService.makeRegister(UserService.currentVerificationUser);
            return "success";
        } else {
            // тут будет показываться html страница с ошибкой

            return "error";
        }
    }

}
