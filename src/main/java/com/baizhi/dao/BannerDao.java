package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    public List<Banner> selectAllBanner(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer totalCount();

    /*public*/ void save(Banner banner);

    public void update(Banner banner);

    public void delete(String id);
}
