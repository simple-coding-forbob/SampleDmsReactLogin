/**
 * 
 */
package com.simplecoding.simpledmsreactlogin.dept.controller;


import com.simplecoding.simpledmsreactlogin.common.ApiResponse;
import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import com.simplecoding.simpledmsreactlogin.dept.dto.DeptDto;
import com.simplecoding.simpledmsreactlogin.dept.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author user
 *
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "DeptController", description = "부서 API 문서")
@RequestMapping("/api")
public class DeptController {

	private final DeptService deptService;
    private final CommonUtil commonUtil;

    @Operation(summary = "부서 전체 조회", description = "검색 키워드로 부서 목록을 조회합니다.")
	@GetMapping("/dept")
	public ResponseEntity<ApiResponse<List<DeptDto>>> selectDeptList(@Parameter(description = "검색 키워드") @RequestParam(defaultValue = "") String searchKeyword,
                                                                     @PageableDefault(page = 0, size = 3) Pageable pageable) {

		Page<DeptDto> pages=deptService.selectDeptList(searchKeyword, pageable);
		log.info("테스트 : "+pages);
        ApiResponse<List<DeptDto>> response = new ApiResponse<>(true,
                "조회 성공", pages.getContent(), pages.getNumber(), pages.getTotalElements());
        return ResponseEntity.ok(response);
	}

    @Operation(summary = "부서 저장", description = "새로운 부서를 등록합니다.")
    @PostMapping("/dept")
    public ResponseEntity<Void> create(@Valid @RequestBody DeptDto deptDto, BindingResult result) {
        commonUtil.checkBindingResult(result);
        deptService.save(deptDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "부서 수정", description = "부서를 수정합니다.")
    @PutMapping("/dept/{dno}")
    public ResponseEntity<Void> update(
            @Parameter(description = "수정할 부서 번호") @PathVariable long dno,
            @Valid @RequestBody DeptDto deptDto, BindingResult result) {
        commonUtil.checkBindingResult(result);
        deptDto.setDno(dno);
        deptService.updateFromDto(deptDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "부서 상세 조회", description = "부서 번호로 상세 정보를 조회합니다.")
    @GetMapping("/dept/{dno}")
    public ResponseEntity<ApiResponse<DeptDto>> findById(@Parameter(description = "조회할 부서 번호") @PathVariable int dno) {
        DeptDto deptDto = deptService.findById(dno);

        ApiResponse<DeptDto> response = new ApiResponse<>(true, "조회 성공", deptDto, 0, 0);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "부서 삭제", description = "부서 번호로 삭제합니다.")
    @DeleteMapping("/dept/{dno}")
    public ResponseEntity<Void> delete(@Parameter(description = "삭제할 부서 번호") @PathVariable int dno) {
        deptService.deleteById(dno);

        return ResponseEntity.ok().build();
    }
}






