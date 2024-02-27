package com.digitopia.digitopiacasestudy.mapper;

import com.digitopia.digitopiacasestudy.dto.response.InvitationResponseDTO;
import com.digitopia.digitopiacasestudy.model.Invitation;

import java.util.List;
import java.util.stream.Collectors;

public class InvitationServiceMapper {

    public static InvitationResponseDTO convertToDTO(Invitation invitation) {

        return new InvitationResponseDTO(invitation.getInvitationID(),
                invitation.getUserID(),
                invitation.getInvitationMessage(),
                invitation.getInvitationCode(),
                invitation.getInvitationStatus(),
                invitation.getUserMail());
    }

    public static List<InvitationResponseDTO> convertToDTO(List<Invitation> invitation) {

        return invitation.stream().map(InvitationServiceMapper::convertToDTO).collect(Collectors.toList());
    }
}
