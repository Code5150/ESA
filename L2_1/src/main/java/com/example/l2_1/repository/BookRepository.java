package com.example.l2_1.repository;

import com.example.l2_1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface BookRepository extends JpaRepository<Book, UUID> {
}
