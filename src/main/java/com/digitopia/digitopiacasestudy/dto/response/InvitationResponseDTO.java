package com.digitopia.digitopiacasestudy.dto.response;

import com.digitopia.digitopiacasestudy.enums.InvitationStatus;

import java.util.UUID;

public record InvitationResponseDTO(

        UUID invitationID,
        UUID userID,
        String invitationMessage,

        String invitationCode,

        InvitationStatus invitationStatus,

        String userMail
) {
}
