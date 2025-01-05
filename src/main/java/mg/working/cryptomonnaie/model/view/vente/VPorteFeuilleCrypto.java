package mg.working.cryptomonnaie.model.view.vente;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "v_portefeuille_crypto")
public class VPorteFeuilleCrypto {
    String cryptomonnaie;
    BigDecimal quantite;
    BigDecimal prixUnitaire;
    int idUtilisateur;

    public void setQuantite(String quantite) {
        this.quantite = new BigDecimal(quantite);
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = new BigDecimal(prixUnitaire);
    }
}
