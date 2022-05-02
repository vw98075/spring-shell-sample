package com.example.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public record LoginPromptProvider(LoginService service) implements PromptProvider {

    @Override
    public AttributedString getPrompt() {

        return service.isLoggedIn() ?
                new AttributedString(this.service.loggedInUser() + " >", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)) :
                new AttributedString("(unknown) >", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));

    }


}
