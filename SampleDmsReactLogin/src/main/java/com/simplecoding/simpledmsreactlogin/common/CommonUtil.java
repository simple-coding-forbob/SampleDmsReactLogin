package com.simplecoding.simpledmsreactlogin.common;

import com.simplecoding.simpledmsreactlogin.auth.dto.SecurityUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Component
@Log4j2
@RequiredArgsConstructor
public class CommonUtil {

    @Value("${image.upload-dir}")
    private String uploadDir;
    private final MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public void checkBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(error -> log.error(error.getDefaultMessage()));

            throw new RuntimeException(getMessage("errors.validation.common"));
        }
    }

    public void saveFile(MultipartFile file, String  uuid) throws Exception {
        Path folderPath = Paths.get(uploadDir);
        if (!Files.exists(folderPath)) {
            throw new RuntimeException(getMessage("errors.path.not.found"));
        }

        Path uuidPath = folderPath.resolve(uuid);
        file.transferTo(uuidPath);
    }

    public void deleteFile(String uuid) {
        Path uuidPath = Paths.get(uploadDir).resolve(uuid);
        try {
            Files.deleteIfExists(uuidPath);
        } catch (IOException e) {
            throw new RuntimeException(getMessage("errors.file.delete.fail"), e);
        }
    }

    public byte[] readFile(String uuid) throws Exception {
        Path path = Paths.get(uploadDir).resolve(uuid);

        if (!Files.exists(path)) {
            throw new RuntimeException(getMessage("errors.path.not.found"));
        }

        return Files.readAllBytes(path);
    }

    public String generateUrl(String path, String uuid) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/download/"+path+"/{uuid}")
                .buildAndExpand(uuid)
                .toUriString();

    }

    public SecurityUserDto getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException(getMessage("errors.unauthorized"));
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof SecurityUserDto user) {
            return user;
        } else {
            throw new RuntimeException(getMessage("errors.unauthorized"));
        }
    }
}
