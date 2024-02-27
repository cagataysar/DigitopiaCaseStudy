package com.digitopia.digitopiacasestudy.repository;

import com.digitopia.digitopiacasestudy.enums.InvitationStatus;
import com.digitopia.digitopiacasestudy.model.Invitation;
import com.digitopia.digitopiacasestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, UUID> {

    Optional<Invitation> findByInvitationCode(String invitationCode);


    List<Invitation> findByUserID(UUID id);
}
