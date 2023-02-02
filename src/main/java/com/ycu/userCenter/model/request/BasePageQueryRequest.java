package com.ycu.userCenter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageQueryRequest implements Serializable {


    private static final long serialVersionUID = 6271824900303360476L;

    private Integer currentPage = 1;

    private Integer pageSize = 10;


}
