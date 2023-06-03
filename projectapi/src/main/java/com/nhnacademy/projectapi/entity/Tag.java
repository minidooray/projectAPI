package com.nhnacademy.projectapi.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "Tags")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_content")
    private String tagContent;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
