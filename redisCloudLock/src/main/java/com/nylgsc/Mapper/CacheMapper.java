package com.nylgsc.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nylgsc.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CacheMapper extends BaseMapper<OrderInfo> {
    List<OrderInfo> getAllOrder();
}
