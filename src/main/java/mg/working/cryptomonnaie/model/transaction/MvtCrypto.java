package mg.working.cryptomonnaie.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "mvt_crypto")
@SequenceGenerator(
    name = "s_mvt_crypto",
    sequenceName = "s_mvt_crypto",
    allocationSize = 1
)
public class MvtCrypto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_mvt_crypto")
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crypto_monnaie", nullable = false , referencedColumnName = "id_crypto_monnaie")
    private CryptoMonnaie cryptoMonnaie;

    @Column(name = "variation", nullable = false, precision = 5, scale = 2)
    private BigDecimal variation;

    @Column(name = "date_heure", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateHeure = LocalDateTime.now();

    public void setVariation(String variation) {
        this.variation = BigDecimal.valueOf(Double.parseDouble(variation));
    }
}
