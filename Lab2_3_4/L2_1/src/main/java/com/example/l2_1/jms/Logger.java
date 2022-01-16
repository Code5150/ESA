package com.example.l2_1.jms;

import com.example.l2_1.entity.Log;
import com.example.l2_1.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Logger {
    @Autowired
    private LogRepository logRepository;

    @JmsListener(
            destination = JmsHelper.LOGGER_DESTINATION_NAME,
            containerFactory = JmsHelper.FACTORY_NAME,
            id = "jms_logger"
    )
    @SendTo(JmsHelper.MAILBOX_DESTINATION_NAME)
    public Log receiveMessage(Log log) {
        System.out.println("Info: ====== " + log.getKindChange() + " " + log.getEntity() + " ======");
        return logRepository.save(log);
    }
}
