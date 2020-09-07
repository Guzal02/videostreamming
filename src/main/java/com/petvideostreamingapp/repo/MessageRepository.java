package com.petvideostreamingapp.repo;

import com.petvideostreamingapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
