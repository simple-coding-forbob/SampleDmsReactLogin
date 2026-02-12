package com.simplecoding.simpledmsreactlogin.dept.service;




import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import com.simplecoding.simpledmsreactlogin.common.MapStruct;
import com.simplecoding.simpledmsreactlogin.dept.dto.DeptDto;
import com.simplecoding.simpledmsreactlogin.dept.entity.Dept;
import com.simplecoding.simpledmsreactlogin.dept.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;
    private final MapStruct mapStruct;
    private final CommonUtil commonUtil;

    public Page<DeptDto> selectDeptList(String searchKeyword, Pageable pageable) {
        return deptRepository.selectDeptList(searchKeyword, pageable);
    }

    public void save(DeptDto deptDto) {
        Dept dept=mapStruct.toEntity(deptDto);
        deptRepository.save(dept);
    }

    public DeptDto findById(long dno) {
        Dept dept = deptRepository.findById(dno)
                .orElseThrow(() -> new RuntimeException(commonUtil.getMessage("errors.not.found")));

        return mapStruct.toDto(dept);
    }

    @Transactional
    public void updateFromDto(DeptDto deptDto) {
        Dept dept=deptRepository.findById(deptDto.getDno())
                .orElseThrow(() -> new RuntimeException(commonUtil.getMessage("errors.not.found")));

        mapStruct.updateFromDto(deptDto, dept);

    }

    public void deleteById(long dno) {
        deptRepository.deleteById(dno);
    }
}

