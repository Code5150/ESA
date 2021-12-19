package com.example.l2_1.service.jms;

import com.example.l2_1.entity.Log;
import com.example.l2_1.util.DBChanges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void logging(String entity, DBChanges changes, Object descriptions) {
        System.out.println("Info: ====== " + changes.toString() + " " + entity + " ======");
        Log log = new Log();
        log.setKindChange(changes.toString());
        log.setEntity(entity);
        log.setDetails(descriptions.toString());
        jmsTemplate.convertAndSend(JmsHelper.DESTINATION_NAME, log);

    }


}
