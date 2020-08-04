package com.bysj.wyb.common.result;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyb
 */
@Data
public class PageResult {

    int nowPage;

    int total;

    int pageSize;

    int thisSize;

    List<Object> pageEntity;

    public Result pageRes(PageResult pr) {
        HandleResult hr = new HandleResult();
        return hr.outResultWithData("0", "success", pr);
    }

    public List<Object> pageStarter(int pageNum, int pageSize, List<Object> o) {
        if (null != o && !o.isEmpty()) {
            return pageReturn(pageNum, pageSize, o);
        }
        return null;
    }

    public List<Object> pageReturn(int pageNum, int pageSize, List<Object> o) {
        this.nowPage = pageNum;
        if (pageNum < 1)
            return null;
        List<Object> temp = new ArrayList<>();
        this.pageSize = pageSize;
        if (o.size() % pageSize > 0) {
            this.total = o.size() / pageSize + 1;
        } else {
            this.total = o.size() / pageSize;
        }
        if (pageNum > this.total)
            return null;
        for (int i = (pageNum - 1) * pageSize; i <= pageNum * pageSize - 1; i++) {
            if (i == o.size()) {
                break;
            }
            temp.add(o.get(i));
        }
        this.pageEntity = temp;
        this.thisSize = temp.size();
        return temp;
    }
}
