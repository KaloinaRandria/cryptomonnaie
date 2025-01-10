package mg.working.cryptomonnaie.services.login;

import mg.working.cryptomonnaie.model.user.Utilisateur;
import mg.working.cryptomonnaie.services.utilisateur.UtilisateurService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Service
public class InscriptionService {

    @Autowired
    UtilisateurService utilisateurService;

    private final RestTemplate restTemplate;

    private final String SYMFONY_API_URL = "http://localhost:8000/inscription"; // URL de l'API Symfony

    public InscriptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String inscrireUtilisateur(String nom, String dateNaissance, String email, String motDePasse) {
        // Préparer les données de l'utilisateur à envoyer à l'API Symfony
        String jsonPayload = String.format(
                "{\"nom\": \"%s\", \"dateNaissance\": \"%s\", \"email\": \"%s\", \"motDePasse\": \"%s\"}",
                nom, dateNaissance, email, motDePasse
        );

        // Créer un en-tête HTTP pour l'envoi
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Créer une entité HTTP avec les données et les en-têtes
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        // Appeler l'API Symfony et récupérer la réponse
        ResponseEntity<String> response = restTemplate.exchange(SYMFONY_API_URL, HttpMethod.POST, requestEntity, String.class);

        // Vérifier si l'API a renvoyé une réponse réussie
        if (response.getStatusCode() == HttpStatus.OK) {
            return "Inscription réussie, veuillez vérifier votre e-mail.";
        } else {
            return "Erreur lors de l'inscription : " + response.getBody();
        }
    }

    public String confirmerInscription(String jeton) {
        // Construire l'URL pour confirmer l'inscription avec le jeton
        String confirmUrl = "http://localhost:8000/confirm/" + jeton;

        // Appeler l'API Symfony pour confirmer l'inscription
        ResponseEntity<String> response = restTemplate.exchange(confirmUrl, HttpMethod.GET, null, String.class);

        // Vérifier si l'API a renvoyé une réponse réussie
        if (response.getStatusCode() == HttpStatus.OK) {
            // Extraire les informations de l'utilisateur à partir de la réponse JSON
            String responseBody = response.getBody();

            // Par exemple, on suppose que la réponse est un JSON avec un champ "utilisateur" qui contient les informations
            // Vous pouvez utiliser une bibliothèque comme Jackson ou Gson pour extraire les données du JSON
            // Ici, c'est une simplification de l'extraction
            JSONObject jsonResponse = new JSONObject(responseBody);
            String utilisateurEmail = jsonResponse.getJSONObject("data").getJSONObject("utilisateur").getString("email");
            String utilisateurNom = jsonResponse.getJSONObject("data").getJSONObject("utilisateur").getString("nom");
            String utilisateurMdp = jsonResponse.getJSONObject("data").getJSONObject("utilisateur").getString("mdp");
            String utilisateurDtn = jsonResponse.getJSONObject("data").getJSONObject("utilisateur").getString("dateNaissance");

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setDtn(utilisateurDtn);
            utilisateur.setNom(utilisateurNom);
            utilisateur.setMdp(utilisateurMdp);
            utilisateur.setSolde("100000");
            utilisateur.setMail(utilisateurEmail);
            utilisateurService.insertUtilisateur(utilisateur);

            // Retourner les informations de l'utilisateur (ou un autre traitement)
            return "Inscription confirmée avec succès. Utilisateur: " + utilisateurNom + " (" + utilisateurEmail + ")";
        } else {
            return "Erreur lors de la confirmation du jeton : " + response.getBody();
        }
    }

}

