package com.yaoyili.tasks;

import com.yaoyili.service.AlbumService;
import com.yaoyili.service.SheetService;
import com.yaoyili.utils.SpringContextUtil;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdatePlayJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String msg = (String) context.getJobDetail().getJobDataMap().get("msg");
        System.out.println("current time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "---" + msg);

        if (SpringContextUtil.getApplicationContext() == null)
            return;
        AlbumService albumService = (AlbumService)SpringContextUtil.getBean(AlbumService.class);
        SheetService sheetService = (SheetService)SpringContextUtil.getBean(SheetService.class);
        albumService.updatePlay();
        sheetService.updatePlay();
    }
}
