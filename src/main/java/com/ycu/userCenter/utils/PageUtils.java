package com.ycu.userCenter.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ycu.userCenter.common.QueryPageVo;

import java.util.List;

public class PageUtils {


    public static <T> QueryPageVo<T> getQueryPageVo(IPage<T> iPage){


        QueryPageVo<T> queryPageVo = new QueryPageVo<>();

        List<T> records = iPage.getRecords();

        long totalPage = iPage.getPages();

        queryPageVo.setRecords(records);

        queryPageVo.setTotalPage(totalPage);

        return queryPageVo;


    }

}
