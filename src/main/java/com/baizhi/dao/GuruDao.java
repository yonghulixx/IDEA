package com.baizhi.dao;

import com.baizhi.entity.Guru;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruDao {
    public List<Guru> selectAllGuru(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer totalCount();

    public void save(Guru guru);

    public void update(Guru guru);

    public void delete(String id);
}
