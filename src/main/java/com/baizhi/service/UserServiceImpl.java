package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> selectAllUser(Integer page, Integer rows) {
        List<User> users = userDao.selectAllUser(page, rows);
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer integer = userDao.totalCount();
        return integer;
    }

    @Override
    public void save(User user) {
//        employee.setId(UUID.randomUUID().toString());
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("nv");}
        userDao.save(user);
    }

    @Override
    public void update(User user) {
//        System.out.println(99999999);
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("女");}
        userDao.update(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public List<User> selectAll() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public HashMap<String, Object> selectByMonth() {
        HashMap<String, Object> map = new HashMap<>();
        List<Month> ns = userDao.selectByMonthN();
        List<Month> vs = userDao.selectByMonthV();
        HashSet<String> m = new HashSet<>();
        ArrayList<String> mm = new ArrayList<>();
        ArrayList<Integer> nc = new ArrayList<>();
        ArrayList<Integer> vc = new ArrayList<>();
        for (Month n : ns) {
            m.add(n.getMonth() + "月");
            nc.add(n.getCount() * 10);
        }
        map.put("boys", nc);
        for (Month n : vs) {
            m.add(n.getMonth() + "月");
            vc.add(n.getCount() * 10);
        }
        map.put("girls", vc);
        map.put("month", m);
        return map;
    }

    @Override
    public List<Object> selectByCity() {
        ArrayList<Object> list = new ArrayList<>();
        List<City> ns = userDao.selectByCityN();
        List<City> vs = userDao.selectByCityV();
        ArrayList<Object> nls = new ArrayList<>();
        ArrayList<Object> vls = new ArrayList<>();
        HashMap<String, Object> nentity = new HashMap<>();
        HashMap<String, Object> ventity = new HashMap<>();
        for (City n : ns) {
            HashMap<String, String> nmap = new HashMap<>();
            nmap.put(n.getCity(), n.getCount().toString());
            nls.add(nmap);
        }
        nentity.put("title", "小哥哥");
        nentity.put("citys", nls);
        for (City v : vs) {
            HashMap<String, String> vmap = new HashMap<>();
            vmap.put(v.getCity(), v.getCount().toString());
            vls.add(vmap);
        }
        ventity.put("title", "小姐姐");
        ventity.put("citys", vls);
        //list.add(ventity);
        list.add(nentity);
        return list;
    }
}
