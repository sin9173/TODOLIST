package com.moais.todo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {

    // 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL 쿼리에 쓸 start, end
    private int nowPage, startPage, endPage, cntPerPage, lastPage, start, end;
    private int cntPage = 10; //페이지 버튼 개수

    private long total;

    public PagingVO(long total, int nowPage, int cntPerPage) {
        setNowPage(nowPage);
        setCntPerPage(cntPerPage);
        setTotal(total);
        calcLastPage(getTotal(), getCntPerPage());
        calcStartEndPage(getNowPage(), cntPage);
        calcStartEnd(getNowPage(), getCntPerPage());
    }

    //마지막 페이지
    public void calcLastPage(long total, int cntPerPage) {
        setLastPage((int) ((total/cntPerPage) + (total%cntPerPage==0?0:1)));
    }

    public void calcStartEndPage(int nowPage, int cntPage) {
        int tempPage = nowPage;

        //END 페이지
        if(nowPage%cntPage==0) tempPage -= 1;
        setEndPage(((tempPage/cntPage) * cntPage) + cntPage);
        if(getLastPage() < getEndPage()) setEndPage(getLastPage());

        //START 페이지
        setStartPage(((tempPage/cntPage) * cntPage) + 1);
        if(getStartPage() < 1)  setStartPage(1);
    }

    public void calcStartEnd(int nowPage, int cntPerPage) {
        setEnd(nowPage * cntPerPage);
        setStart(getEnd() - cntPerPage);
    }
}
