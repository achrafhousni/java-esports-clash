package fr.ancyracademy.esportsclash.auth.application.usecases;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;

public class RegisterCommand  implements Command<IdResponse> {

    private String emailAddress;
    private String password;

    
   public  RegisterCommand(){
        
    }

    public RegisterCommand(String emailAdress, String password) {
        this.emailAddress = emailAdress;
        this.password = password;
    }

    public String getEmailAdress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }


}
