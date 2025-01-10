package mg.working.cryptomonnaie.controller.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.working.cryptomonnaie.services.login.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;

@Controller
public class InscriptionController {

    @Autowired
    InscriptionService inscriptionService;

    @PostMapping("incription")
    public String inscription (HttpServletRequest request, HttpServletResponse response) {
        String nom = request.getParameter("nom");
        String dtn = request.getParameter("dtn");
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");

        inscriptionService.inscrireUtilisateur(nom, dtn, mail, mdp);

        return "redirect:/";

    }

    @GetMapping("confirmInscription/{jeton}")
    public String confirmInscription(@PathVariable String jeton) {
        inscriptionService.confirmerInscription(jeton);
        return "redirect:/";
    }

    @GetMapping("/")
    public String in() {
        return "login/inscription";
    }

}
