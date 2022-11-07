package com.moais.todo.service;

import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;

public interface WorkService {

    public ResponseVO workRegister(WorkRegisterRequestVO vo, String user_id);

    public ResponseVO workList(WorkListRequestVO vo, String user_id);

    public ResponseVO workRecent(String user_id);

    public ResponseVO workModify(WorkModifyRequestVO vo, Long id, String user_id);

    public ResponseVO workModifyState(WorkModifyStateRequestVO vo, Long id, String user_id);

    public ResponseVO workDelete(WorkDeleteRequestVO vo, String user_id);
}
