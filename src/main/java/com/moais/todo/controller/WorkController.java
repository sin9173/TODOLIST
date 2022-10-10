package com.moais.todo.controller;

import com.moais.todo.annotation.Nullable;
import com.moais.todo.annotation.TokenCheck;
import com.moais.todo.service.WorkService;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;
import com.moais.todo.vo.response.WorkListVO;
import com.moais.todo.vo.response.WorkRecentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "할일 API", description = "할일 API")
@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @TokenCheck
    @ApiOperation(value = "할일 등록", notes = "할일을 등록합니다.", response = ResponseVO.class)
    @PostMapping("/work/register")
    public ResponseVO workRegister(@RequestBody WorkRegisterRequestVO data, @RequestHeader("token") String token) {
        return workService.workRegister(data, token);
    }

    @TokenCheck
    @ApiOperation(value = "할일 수정", notes = "할일을 수정합니다.", response = ResponseVO.class)
    @PostMapping("/work/modify/info")
    public ResponseVO workModifyInfo(@RequestBody WorkModifyRequestVO data) {
        return workService.workModify(data);
    }

    @TokenCheck
    @ApiOperation(value = "할일 상태 수정", notes = "할일의 상태를 수정합니다.", response = ResponseVO.class)
    @PostMapping("/work/modify/state")
    public ResponseVO workModifyState(@RequestBody WorkModifyStateRequestVO data) {
        return workService.workModifyState(data);
    }


    @Nullable
    @TokenCheck
    @ApiOperation(value = "TODO 리스트 조회", notes = "TODO 리스트를 조회합니다.", response = WorkListVO.class)
    @GetMapping("/work/list")
    public ResponseVO workList(@ModelAttribute WorkListRequestVO data, @RequestHeader("token") String token) {
        return workService.workList(data, token);
    }

    @Nullable
    @TokenCheck
    @ApiOperation(value = "TODO 리스트 최근 데이터 조회", notes = "최근 할일을 조회합니다.", response = WorkRecentVO.class)
    @GetMapping("/work/recent")
    public ResponseVO workRecent(@RequestHeader("token") String token) {
        return workService.workRecent(token);
    }

    @TokenCheck
    @ApiOperation(value = "할일 삭제", notes = "할일을 삭제합니다.", response = ResponseVO.class)
    @PostMapping("/work/delete")
    public ResponseVO workDelete(@RequestBody WorkDeleteRequestVO data, @RequestHeader("token") String token) {
        return workService.workDelete(data, token);
    }
}
