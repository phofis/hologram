package mkhc.hologram.dto.conversation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupConversationDTO {
    private List<Long> userIds;
    private Long adminId;
    private String optionalGroupName;
}
