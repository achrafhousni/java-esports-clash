package fr.ancyracademy.esportsclash.auth;

import fr.ancyracademy.esportsclash.auth.application.usecases.RegisterCommand;
import fr.ancyracademy.esportsclash.auth.application.usecases.RegisterCommandHandler;
import fr.ancyracademy.esportsclash.auth.domain.model.User;
import org.junit.Assert;

public class RegisterCommandHandlerTests {

    public void shouldRegister() {
        RegisterCommand registerCommand = new RegisterCommand("contact@ancry.fr","password");
        RegisterCommandHandler commmandHandler= new RegisterCommandHandler();
        //commmandHandler.handle(command);
        User actualUser= null;
        Assert.assertEquals("contact@ancry.fr",actualUser.getEmailAddress());
    }
}
