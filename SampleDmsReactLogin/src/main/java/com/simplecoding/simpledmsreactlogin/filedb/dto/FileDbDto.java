package com.simplecoding.simpledmsreactlogin.filedb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "uuid")
public class FileDbDto {

    private String uuid;
    @NotBlank
    private String fileTitle;
    @NotBlank
    private String fileContent;
    private String fileUrl="https://placehold.co/600x400";
    private MultipartFile fileData;

    public FileDbDto(String uuid, String fileTitle, String fileContent, String fileUrl) {
        this.uuid = uuid;
        this.fileTitle = fileTitle;
        this.fileContent = fileContent;
        this.fileUrl = fileUrl;
    }
}
