package com.nhnacademy.projectapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDTO {

    private Long tagId;
    private String tagContent;

}
