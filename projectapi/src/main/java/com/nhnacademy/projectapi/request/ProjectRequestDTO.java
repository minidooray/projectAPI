package com.nhnacademy.projectapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDTO {
    private String commentContent;
    private String commentAccount;
    private String accountId;

}
