package com.test01.regist01.event.listener;

import com.test01.regist01.event.RegistrationCompleteEvent;
import com.test01.regist01.user.User;
import com.test01.regist01.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        User theUser = event.getUser();
        // 2. Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        // 3. Save the verification token for the user
        userService.saveUserVerificationToken(theUser, verificationToken);
        // 4. Build the verification url to be sent to the user
        String url  = event.getApplicationUrl()+"/register/verifyEmail?token=" + verificationToken;
        // 5. Send the mail
        log.info("Click the link to verify your registration : {}", url);

    }
}
