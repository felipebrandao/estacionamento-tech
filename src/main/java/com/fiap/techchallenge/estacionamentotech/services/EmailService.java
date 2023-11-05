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
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    public void sendEmail(EmailDataDTO emailData) {
        try {
            Email from = new Email("sribeiro.thalita@gmail.com");
            Email to = new Email(emailData.getTo());
            String subject = emailData.getSubject();

            Content content = new Content("text/plain", emailData.getText()
                    .replace("[[NOME_DO_CLIENTE]]", emailData.getNomeDoCliente())
                    .replace("[[DATA_HORA]]", emailData.getDataHora())
                    .replace("[[VALOR_PAGO]]", emailData.getValorPago())
                    .replace("[[LOCAL]]", emailData.getLocal()));

            Mail mail = new Mail(from, subject, to, content);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            response.getStatusCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
