package com.wh.dao;

import org.springframework.stereotype.Repository;
import com.wh.entity.Items;

import java.util.List;

/**
 * Created by guozhaohui628@gmail.com on 2018/5/8
 * Description:
 */
@Repository
public interface ItemsDao {
    public List<Items> queryAllItemsList();
}
