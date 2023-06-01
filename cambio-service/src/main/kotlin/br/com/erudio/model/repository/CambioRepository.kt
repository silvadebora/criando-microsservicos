package br.com.erudio.model.repository

import br.com.erudio.model.Cambio
import org.springframework.data.jpa.repository.JpaRepository

interface CambioRepository: JpaRepository<Cambio, Long?>{

    fun findByFromAndTo(from: String, to: String): Cambio?

}