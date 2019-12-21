package com.baizhi.service;


import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruService {
    public List<Guru> selectAllGuru(Integer page, Integer rows);

    public Integer totalCount();

    public void save(Guru guru);

    public void update(Guru guru);

    public void delete(String id);


}
