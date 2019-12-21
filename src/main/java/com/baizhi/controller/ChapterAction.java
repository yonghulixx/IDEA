package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chapter")
public class ChapterAction {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/queryByPage")
    @ResponseBody
    public Map<String, Object> selectAll(String albumId, Integer page, Integer rows) {
        //    System.out.println(albumId+page+"--C---"+rows);
        List<Chapter> chapters = chapterService.selectAllChapter(albumId, (page - 1) * rows, rows);
        Integer total = chapterService.totalCount();
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
        maps.put("rows", chapters);
        return maps;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Chapter chapter, String oper, String albumId) {

        chapter.setAlbum_id(albumId);
        // System.out.println(oper);
        String uid = null;
        if ("add".equals(oper)) {
            uid = chapterService.save(chapter);
        } else if ("edit".equals(oper)) {
            uid = chapterService.update(chapter);
        } else if ("del".equals(oper)) {
            // System.out.println(chapter.getId()+"????????");
            chapterService.delete(chapter.getId());
            uid = null;
        }
        //System.out.println(uid);
        return uid;
//        if("add".equals(oper)){
//            employeeService.save(employee);
//        }

        /*return "";*/

    }

    @RequestMapping("uploadChapter")
    public HashMap<String, Object> uploadBanner(MultipartFile url, String id, HttpServletRequest request) {
//        System.out.println("--------upload----------" );
        HashMap<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/bootstrap/radio");

        File file = new File(realPath);

        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = url.getOriginalFilename();

        String name = new Date().getTime() + "_" + filename;

        try {
            url.transferTo(new File(realPath, name));

            long size = url.getSize();
            String sizes = size / 1024 / 1024 + "MB";

            DecimalFormat format = new DecimalFormat("0.00");
            String str = String.valueOf(size);
            Double dd = Double.valueOf(str) / 1024 / 1024;
            String newsize = format.format(dd) + "MB";

            AudioFileIO fileIO = new AudioFileIO();
            AudioFile audio = fileIO.readFile(new File(realPath, name));
            AudioHeader audioHeader = audio.getAudioHeader();

            int length = audioHeader.getTrackLength();
            String duration = length / 60 + "分" + length % 60 + "秒";

            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setSize(newsize);
            chapter.setDuration(duration);
            chapter.setUrl(name);
            chapterService.update(chapter);
            map.put("success", "200");
            map.put("message", "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", "400");
            map.put("message", "上传失败");
        }
        return map;
    }

    @RequestMapping("/downloadChapter")
    @ResponseBody
    public void downloadChapter(String fileName, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(fileName + "--------down----------");
        String realPath = request.getSession().getServletContext().getRealPath("/bootstrap/radio");
        System.out.println(realPath);
        try {
            File file = new File(realPath + "/" + fileName);
            FileInputStream inputStream = new FileInputStream(file);//attachment

            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            ServletOutputStream outpupStream = response.getOutputStream();
            IOUtils.copy(inputStream, outpupStream);
            inputStream.close();
            outpupStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
//response.setHeader("conetent-dispostition","attachment"+ URLEncoder.encode(fileName,"UTF-8"));
//        ServletOutputStream outpupStream = response.getOutputStream();
//        IOUtils.copy(inputStream,outpupStream);