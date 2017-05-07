package com.yangjun.dao.mybatis.mapper;

import com.yangjun.bean.AccountInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by admin on 2017/2/26.
 */
@Mapper
public interface AccountInfoMapper {

    AccountInfo selectByPrimaryKey(Integer id);

}
