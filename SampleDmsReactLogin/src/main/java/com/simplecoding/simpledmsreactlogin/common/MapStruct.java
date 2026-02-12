package com.simplecoding.simpledmsreactlogin.common;

import com.simplecoding.simpledmsreactlogin.auth.dto.MemberDto;
import com.simplecoding.simpledmsreactlogin.auth.dto.MypageDto;
import com.simplecoding.simpledmsreactlogin.auth.entity.Member;
import com.simplecoding.simpledmsreactlogin.dept.dto.DeptDto;
import com.simplecoding.simpledmsreactlogin.dept.entity.Dept;
import com.simplecoding.simpledmsreactlogin.emp.dto.EmpDto;
import com.simplecoding.simpledmsreactlogin.emp.entity.Emp;
import com.simplecoding.simpledmsreactlogin.filedb.dto.FileDbDto;
import com.simplecoding.simpledmsreactlogin.filedb.entity.FileDb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  // null 제외 기능(update 시 사용)
)
public interface MapStruct {

    DeptDto toDto(Dept dept);
    Dept toEntity(DeptDto deptDto);
    void updateFromDto(DeptDto deptDto, @MappingTarget Dept dept);

    @Mapping(source = "dept.dno", target = "dno")
    EmpDto toDto(Emp emp);
    @Mapping(source = "dno", target = "dept.dno")
    Emp toEntity(EmpDto empDto);
    @Mapping(target = "dept", ignore = true)
    void updateFromDto(EmpDto empDto, @MappingTarget Emp emp);

    FileDbDto toDto(FileDb fileDb);
    FileDb toEntity(FileDbDto fileDbDto);

    @Mapping(source = "emp.eno", target = "eno")
    MemberDto toDto(Member member);
    @Mapping(source = "eno", target = "emp.eno")
    Member toEntity(MemberDto memberDto);

    MypageDto toDto2(Member member);
    Member toEntity2(MypageDto mypageDto);
}
