package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumAction {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryByPage")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {

        List<Album> albums = albumService.selectAllAlbum((page - 1) * rows, rows);
        Integer total = albumService.totalCount();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("page", page);
        maps.put("records", total);
        Integer pageCount = 0;
        if (total % rows == 0) {
            pageCount = total / rows;
        } else {
            pageCount = total / rows + 1;
        }
        maps.put("total", pageCount);
        maps.put("rows", albums);
        return maps;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public void edit(Album album, String oper) {
        System.out.println(album);
        System.out.println(oper);
        if ("add".equals(oper)) {
            String uid = albumService.save(album);
        } else if ("edit".equals(oper)) {
            albumService.update(album);
        } else if ("del".equals(oper)) {
            // albumService.delete(album.getId());
        }

//        if("add".equals(oper)){
//            employeeService.save(employee);
//        }

        /*return "";*/
    }
}
