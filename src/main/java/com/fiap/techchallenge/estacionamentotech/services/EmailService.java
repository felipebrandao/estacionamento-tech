package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.EmailDataDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${email.from}")
    private String fromEmail;

    @Value("${sendgrid.endpoint}")
    private String sendGridEndpoint;

    public void sendEmail(EmailDataDTO emailData) throws IOException {
            Email from = new Email(fromEmail);
            Email to = new Email(emailData.getTo());
            String subject = emailData.getSubject();

            Content content = new Content("email-template.html", emailData.getText()
                    .replace("[[NOME_DO_CLIENTE]]", emailData.getNomeDoCliente())
                    .replace("[[DATA_HORA]]", emailData.getDataHora())
                    .replace("[[VALOR_PAGO]]", emailData.getValorPago())
                    .replace("[[LOCAL]]", emailData.getLocal()));

            Mail mail = new Mail(from, subject, to, content);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint(sendGridEndpoint);
            request.setBody(mail.build());

            request.addHeader("Authorization", "Bearer " + sendGridApiKey);

            Response response = sendGrid.api(request);

            response.getStatusCode();
    }
}
