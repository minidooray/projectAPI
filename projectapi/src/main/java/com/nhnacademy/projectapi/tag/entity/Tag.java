package com.nhnacademy.projectapi.tag.entity;

import com.nhnacademy.projectapi.project.entity.Project;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "Tags")
@Getter
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    public void modify(String tagContent){
        if(tagContent!=null){
            this.tagContent = tagContent;
        }
    }

}
