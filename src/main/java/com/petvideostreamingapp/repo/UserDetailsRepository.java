package com.petvideostreamingapp.repo;

import com.petvideostreamingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
