package mg.working.cryptomonnaie.model.user;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
@SequenceGenerator(
    name = "s_utilisateur",
    sequenceName = "s_utilisateur",
    allocationSize = 1
)
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "s_utilisateur") @Column(name = "id_utilisateur")
    int id;
    String nom;
    String mail;
    String mdp;
    Date dtn;
    double solde = 0.0;

    public void setSolde(String solde) {
        this.solde = Double.parseDouble(solde);
    }

    public void setDtn(String dtn) {
        this.dtn = Date.valueOf(dtn);
    }
}
