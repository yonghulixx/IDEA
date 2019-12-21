package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    public List<Banner> selectAllBanner(Integer page, Integer rows);

    public Integer totalCount();

    /*public*/ String save(Banner banner);

    /*public*/ String update(Banner banner);

    public void delete(String id);


}
