package com.simplecoding.simpledmsreactlogin.emp.service;


import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import com.simplecoding.simpledmsreactlogin.common.MapStruct;
import com.simplecoding.simpledmsreactlogin.emp.dto.EmpDto;
import com.simplecoding.simpledmsreactlogin.emp.entity.Emp;
import com.simplecoding.simpledmsreactlogin.emp.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepository empRepository;
    private final MapStruct mapStruct;
    private final CommonUtil commonUtil;

    public Page<EmpDto> selectEmpList(String searchKeyword, Pageable pageable) {
        Page<EmpDto>  page= empRepository.selectEmpList(searchKeyword, pageable);
        return page;
    }

    public void save(EmpDto empDto) {
        Emp emp=mapStruct.toEntity(empDto);
        empRepository.save(emp);
    }

    public EmpDto findById(long eno) {
        Emp emp= empRepository.findById(eno)
                .orElseThrow(() -> new RuntimeException(commonUtil.getMessage("errors.not.found")));
        return mapStruct.toDto(emp);
    }


    @Transactional
    public void updateFromDto(EmpDto empDto) {
        Emp emp=empRepository.findById(empDto.getEno())
                .orElseThrow(() -> new RuntimeException("errors.not.found"));

        mapStruct.updateFromDto(empDto, emp);
    }

    public void deleteById(long eno) {
        empRepository.deleteById(eno);
    }
}

