package mg.working.cryptomonnaie.model.crypto;

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

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crypto_monnaie")
@SequenceGenerator(
    name = "s_crypto_monnaie",
    sequenceName = "s_crypto_monnaie",
    allocationSize = 1
)
public class CryptoMonnaie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_crypto_monnaie")
    @Column(name = "id_crypto_monnaie")
    private int id;

    @Column(name = "designation", nullable = false, length = 100)
    private String designation;

    @Column(name = "symbol", nullable = false, unique = true, length = 10)
    private String symbol;

    @Column(name = "prix_unitaire", nullable = false, precision = 15, scale = 2)
    private BigDecimal prixUnitaire;

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = BigDecimal.valueOf(Double.parseDouble(prixUnitaire));
    }
}

