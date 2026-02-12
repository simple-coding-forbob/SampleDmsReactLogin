package com.simplecoding.simpledmsreactlogin.filedb.entity;

import com.simplecoding.simpledmsreactlogin.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "TB_FILE_DB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "uuid", callSuper = false)
public class FileDb extends BaseTimeEntity {

    @Id
    private String uuid;
    private String fileTitle;
    private String fileContent;
    private String fileUrl="https://placehold.co/600x400";
}
