package br.com.erudio.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "cambio")
data class Cambio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "from_currency", nullable = false, length = 3)
    var from: String = "",

    @Column(name = "to_currency", nullable = false, length = 3)
    var to: String = "",

    @Column(nullable = false)
    var conversionFactor: BigDecimal = BigDecimal.ZERO,

    //não representa coluna do banco
    @Transient
    var convertedValue: BigDecimal = BigDecimal.ZERO,

    @Transient
    var environment: String? = ""
)