package mg.working.cryptomonnaie.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.model.user.Utilisateur;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_crypto")
@SequenceGenerator(
        name = "s_transaction_crypto",
        sequenceName = "s_transaction_crypto",
        allocationSize = 1
)
public class TransactionCrypto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_transaction_crypto")
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false , referencedColumnName = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crypto_monnaie", nullable = false , referencedColumnName = "id_crypto_monnaie")
    private CryptoMonnaie cryptoMonnaie;

    @Column(name = "quantite", nullable = false, precision = 15, scale = 6)
    private BigDecimal quantite;

    @Column(name = "prix_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal prixTotal;

    @Column(name = "date_heure", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateHeure = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transaction", nullable = false, length = 10)
    private TypeTransaction typeTransaction;

    public enum TypeTransaction {
        ACHAT,
        VENTE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public CryptoMonnaie getCryptoMonnaie() {
        return cryptoMonnaie;
    }

    public void setCryptoMonnaie(CryptoMonnaie cryptoMonnaie) {
        this.cryptoMonnaie = cryptoMonnaie;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
}

