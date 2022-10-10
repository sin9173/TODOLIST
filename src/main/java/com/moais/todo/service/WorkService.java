package com.moais.todo.service;

import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;

public interface WorkService {

    public ResponseVO workRegister(WorkRegisterRequestVO vo, String token);

    public ResponseVO workList(WorkListRequestVO vo, String token);

    public ResponseVO workRecent(String token);

    public ResponseVO workModify(WorkModifyRequestVO vo);

    public ResponseVO workModifyState(WorkModifyStateRequestVO vo);

    public ResponseVO workDelete(WorkDeleteRequestVO vo, String token);
}
