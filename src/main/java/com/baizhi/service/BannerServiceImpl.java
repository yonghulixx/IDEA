package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Banner> selectAllBanner(Integer page, Integer rows) {
        List<Banner> employees = bannerDao.selectAllBanner(page, rows);
        return employees;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer integer = bannerDao.totalCount();
        return integer;
    }

    @Override
    public String save(Banner banner) {
//        String uuid = UUID.randomUUID().toString();
        String uuid = UUIDUtil.getUUID();
        banner.setId(uuid);
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("nv");}
        banner.setStatus("1");
        banner.setUp_date(new Date());
        System.out.println(banner);
        bannerDao.save(banner);
        return uuid;
    }

    @Override
    public String update(Banner banner) {
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("女");}
//X        banner.setUp_date(new Date());
        //       System.out.println("service"+banner);
        bannerDao.update(banner);
        return banner.getId();
    }

    @Override
    public void delete(String id) {
        bannerDao.delete(id);
    }
}
