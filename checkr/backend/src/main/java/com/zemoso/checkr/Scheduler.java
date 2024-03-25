package com.zemoso.checkr;

import com.zemoso.checkr.service.impl.AdverseActionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class Scheduler {
    Logger logger= Logger.getLogger(Scheduler.class.getName());
    AdverseActionServiceImp adverseActionServiceImp;
    @Autowired
    public Scheduler(AdverseActionServiceImp adverseActionServiceImp) {
        this.adverseActionServiceImp = adverseActionServiceImp;
    }

    @Scheduled(cron = "0 0 12 * * MON-FRI")
    public void myScheduledTask() {
        //adverseActionServiceImp.findPostNoticeDateTodayCandidtes();
        logger.log(Level.INFO,"Executing scheduled task...");
    }

}
