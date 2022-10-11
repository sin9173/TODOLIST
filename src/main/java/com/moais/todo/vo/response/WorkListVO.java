package com.moais.todo.vo.response;

import com.moais.todo.entity.Work;
import com.moais.todo.entity.WorkState;
import com.moais.todo.vo.PagingVO;
import com.moais.todo.vo.ResponseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class WorkListVO extends ResponseVO { //할일 리스트 조회 데이터
    private int startPage;

    private int endPage;

    private int lastPage;

    private int currentPage;

    private List<WorkListData> list;

    public WorkListVO(Page<Work> data) {
        System.out.println("totalElement : " + data.getTotalElements());
        System.out.println("totalPages : " + data.getTotalPages());
        PagingVO page = new PagingVO(data.getTotalElements(), data.getNumber()+1, data.getSize());
        startPage = page.getStartPage();
        endPage = page.getEndPage();
        lastPage = page.getLastPage();
        currentPage = page.getNowPage();

        list = data.getContent().stream()
                .map(w -> new WorkListData(w))
                .collect(Collectors.toList());
    }
}

@Getter
@Setter
@ToString
class WorkListData {
    @ApiModelProperty(notes = "인덱스", example = "1", position = 0)
    private Long id;

    @ApiModelProperty(notes = "아이디", example = "aaa", position = 1)
    private String user_id;

    @ApiModelProperty(notes = "닉네임", example = "닉네임", position = 2)
    private String nick_name;

    @ApiModelProperty(notes = "할일", example = "산책", position = 3)
    private String content;

    @ApiModelProperty(notes = "상태[할일, 진행중, 완료됨]", example = "할일", position = 4)
    private WorkState state;

    @ApiModelProperty(notes = "등록일시", example = "2022-10-09 20:00:00", position = 5)
    private String reg_date;

    @ApiModelProperty(notes = "수정일시", example = "2022-10-09 20:00:00", position = 6)
    private String update_date;

    public WorkListData(Work work) {
        this.id = work.getId();
        this.user_id = work.getMember().getUserId();
        this.nick_name = work.getMember().getMemberInfo().getNickName();
        this.content = work.getContent();
        this.state = work.getState();
        this.reg_date = work.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.update_date = work.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
