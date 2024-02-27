package com.digitopia.digitopiacasestudy.dto.request;

import java.util.UUID;

public record InvitationRequestDTO(
        UUID userID,

        String invitationMessage
) {
}
