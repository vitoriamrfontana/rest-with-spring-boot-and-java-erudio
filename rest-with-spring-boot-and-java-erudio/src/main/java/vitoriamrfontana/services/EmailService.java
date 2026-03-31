package vitoriamrfontana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitoriamrfontana.config.EmailConfig;
import vitoriamrfontana.data.dto.request.EmailRequestDTO;
import vitoriamrfontana.mail.EmailSender;

@Service
public class EmailService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailConfig emailConfigs;

    public void sendSimpleEmail(EmailRequestDTO emailRequest){
        emailSender
                .to(emailRequest.getTo())
                .withSubject(emailRequest.getSubject())
                .withMessage(emailRequest.getSubject())
                .send(emailConfigs);
    }
}
