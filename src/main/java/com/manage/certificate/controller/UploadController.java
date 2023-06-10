package com.manage.certificate.controller;

import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ResourceLoader;

@RestController
public class UploadController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ResourceLoader resourceLoader;

    public UploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("/a/upload")
    @ResponseBody
    public Response upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return super.failResponse(BizExceptionEnum.UPLOAD_DATA_ERROR);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = formatter.format(new Date());
        str += (int) ((Math.random() * 9 + 1) * 10000);

        System.out.println(str);
        String fileName = file.getOriginalFilename();
        String filePath = "/usr/local/certificate/java/temp/" + str;
        File dest = new File(filePath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        try {
            if (fileName != null) {
                file.transferTo(new File(dest, fileName));
                return super.successResponse(BizResponseEnum.UPLOAD_SUCCEED, filePath + "/" + fileName);
            }
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return super.failResponse(BizExceptionEnum.UPLOAD_ERROR);
    }

    @PostMapping("/a/multiUpload")
    @ResponseBody
    public Response multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "/usr/local/certificate/java/temp/";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return super.successResponse(BizResponseEnum.UPLOAD_ERROR, "上传第" + (i++) + "个文件失败");
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                logger.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                logger.error(e.toString(), e);
                return super.successResponse(BizResponseEnum.UPLOAD_ERROR, "上传第" + (i++) + "个文件失败");
            }
        }

        return super.successResponse(BizResponseEnum.UPLOAD_SUCCEED);
    }

    @GetMapping("/a/download")
    public Response downloadFile(String fileName, HttpServletResponse response) throws IOException {
        // 获得待下载文件所在文件夹的绝对路径
        // 获得文件输入流
        if (fileName != null && !"".equals(fileName)) {
            FileInputStream inputStream = new FileInputStream(fileName);
            int index = fileName.lastIndexOf("/");
            String substring = fileName.substring(index + 1);
            response.setHeader("content-disposition", "attachment; fileName=" + substring);
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return super.successResponse(BizResponseEnum.DOWNLOAD_SUCCEED);
        }
        return super.failResponse(BizExceptionEnum.DOWNLOAD_ROUTE_ERROR);
    }

//    @GetMapping("/a/show")
    public Response showFile(String fileName, HttpServletResponse response) {
        return super.successResponse(BizResponseEnum.DOWNLOAD_SUCCEED, resourceLoader.getResource("file:" + Paths.get(fileName)));
    }

    @GetMapping("/a/show")
    public void showphoto(String fileName, HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 获得的系统的根目录
        File fileParent = new File(File.separator);
        String photoName = (String) session.getAttribute("photoName");
        // 获得/usr/CBeann目录
        File file = new File(fileParent, fileName);


        BufferedImage bi = ImageIO.read(Files.newInputStream(file.toPath()));
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }

    }

}
