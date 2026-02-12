package com.simplecoding.simpledmsreactlogin.filedb.service;


import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import com.simplecoding.simpledmsreactlogin.common.MapStruct;
import com.simplecoding.simpledmsreactlogin.filedb.dto.FileDbDto;
import com.simplecoding.simpledmsreactlogin.filedb.entity.FileDb;
import com.simplecoding.simpledmsreactlogin.filedb.repository.FileDbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileDbService {

    private final FileDbRepository fileDbRepository;
    private final MapStruct mapStruct;
    private final CommonUtil commonUtil;

    public Page<FileDbDto> selectFileDbList(String searchKeyword, Pageable pageable) {
        Page<FileDbDto> page= fileDbRepository.selectFileDbList(searchKeyword, pageable);
        return page;
    }

    public void save(FileDbDto fileDbDto) throws Exception {
        FileDb fileDb=mapStruct.toEntity(fileDbDto);

        String uuid=UUID.randomUUID().toString();
        fileDb.setUuid(uuid);

        if(fileDbDto.getFileData()!=null) {
            String downloadURL=commonUtil.generateUrl("fileDb", uuid);
            fileDb.setFileUrl(downloadURL);
            commonUtil.saveFile(fileDbDto.getFileData(), uuid);
        }

        fileDbRepository.save(fileDb);
    }

    public FileDb findById(String uuid) {
        return fileDbRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException(commonUtil.getMessage("errors.not.found")));
    }

    public void deleteById(String uuid) {
        commonUtil.deleteFile(uuid);
        fileDbRepository.deleteById(uuid);
    }
}

