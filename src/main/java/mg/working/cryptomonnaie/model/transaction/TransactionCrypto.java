package mg.working.cryptomonnaie.model.transaction;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;

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
    @JoinColumn(name = "id_crypto_monnaie", nullable = false , referencedColumnName = "id_crypto_monnaie")
    private CryptoMonnaie cryptoMonnaie;

    @Column(name = "quantite", nullable = false, precision = 15, scale = 6)
    private double quantite;

    @Column(name = "prix_total", nullable = false, precision = 15, scale = 2)
    private double prixTotal;

    @Column(name = "date_heure", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateHeure = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transaction", nullable = false, length = 10)
    private TypeTransaction typeTransaction;

    public enum TypeTransaction {
        ACHAT,
        VENTE
    }
}

