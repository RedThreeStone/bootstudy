package it.lei.day1.action;

import org.aspectj.util.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/downFile")
public class DownFileAction {
    @RequestMapping("/downMyFile")
    public ResponseEntity<byte[]> downMyFile(String fileName, @RequestHeader("User-Agent") String userAgent,
                                           HttpServletRequest request) throws Exception {
        System.out.println( fileName);
        System.out.println(userAgent);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
        bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        File aimFile=new File("C:\\Users\\Default\\NTUSER.DAT");
        bodyBuilder.contentLength(aimFile.length());
        if (userAgent.contains("MSIE")){
            bodyBuilder.header("Content-Disposition","attachment;filename="+aimFile.getName());
        }else {
            bodyBuilder.header("Content-Disposition","attachment;filename*=UTF-8''"+aimFile.getName());
        }
        return  bodyBuilder.body(FileUtil.readAsByteArray(aimFile));


    }
}
