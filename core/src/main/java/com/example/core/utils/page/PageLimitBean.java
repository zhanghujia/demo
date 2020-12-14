package com.example.core.utils.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * @author JIA
 */

@Data
@NoArgsConstructor
public class PageLimitBean<T> implements Serializable {

    private List<T> pageData;

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalCount;

    private Integer totalPage;

    private boolean hasNext;

    private boolean hasPrev;


    public PageLimitBean(List<T> pageData, Integer currentPage,
                         Integer pageSize, Integer totalCount) {
        this.pageData = pageData;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = getTotalPage();
        this.hasNext = isHasNext();
        this.hasPrev = isHasPrev();
    }


    public Integer getTotalPage() {
        Integer totalPage = this.totalCount / this.pageSize;
        return totalCount % pageSize == 0 ? totalPage : totalPage + 1;
    }


    public boolean isHasNext() {
        return this.currentPage < getTotalPage();
    }


    public boolean isHasPrev() {
        return this.currentPage > 1;
    }


}
