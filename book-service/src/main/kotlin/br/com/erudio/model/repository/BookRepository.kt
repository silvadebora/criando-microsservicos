package br.com.erudio.model.repository

import br.com.erudio.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long?>