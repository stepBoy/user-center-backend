package com.ycu.userCenter.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class QueryPageVo<T> implements Serializable {
    private static final long serialVersionUID = 4144208189746548544L;

    private Long totalPage;

    private List<T> records;



}
