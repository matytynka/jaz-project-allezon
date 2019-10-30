package pl.edu.pjwstk.jaz.webapp;

//import pl.edu.pjwstk.jaz.auth.ProfileRepository;
import pl.edu.pjwstk.jaz.login.LoginRequest;
import pl.edu.pjwstk.jaz.users.Account;
import pl.edu.pjwstk.jaz.users.Database;
import pl.edu.pjwstk.jaz.users.UserSession;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private LoginRequest loginRequest;

    @Inject
    private Database database;

    @Inject
    private UserSession userSession;

    /*@Inject
    private ProfileRepository profileRepository;*/

    public void login() {
        System.out.println("Tried to log in using " + loginRequest.toString());

        Account acc = database.CheckLoginandPassw(loginRequest.getUsername(),loginRequest.getPassword());
        if (acc!=null) {
            userSession.setAccount(acc);
        }
       // profileRepository.sampleCodeWithPC();
    }

    
}
