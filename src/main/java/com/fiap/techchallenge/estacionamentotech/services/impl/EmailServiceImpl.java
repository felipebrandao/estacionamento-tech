package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final String conteudoHtml;
    private static final String TEMPLATE_FILE_PATH = "resources/templates/email-template.html";
    private static final String TO_EMAIL = "to";
    private static final String EMAIL_SUBJECT = "subject";

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        try {
            this.conteudoHtml = carregarConteudoHtml();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String carregarConteudoHtml() throws IOException {
        File arquivoHtml = new File(TEMPLATE_FILE_PATH);
        char[] buffer = new char[(int) arquivoHtml.length()];
        try (FileReader fileReader = new FileReader(arquivoHtml)) {
            fileReader.read(buffer);
        }
        return new String(buffer);
    }

    public void enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO) throws MessagingException {
        String conteudoHtmlFinal = conteudoHtml
                .replace("[[NOME_DO_CLIENTE]]", veiculoEstacionadoDTO.getIdVeiculo().toString())
                .replace("[[DATA_HORA]]", veiculoEstacionadoDTO.getDataHoraInicio().toString())
                .replace("[[VALOR_PAGO]]", veiculoEstacionadoDTO.getIdLocalEstacionamento().toString())
                .replace("[[LOCAL]]", veiculoEstacionadoDTO.getIdLocalEstacionamento().toString());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
            helper.setTo(TO_EMAIL);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setText(conteudoHtmlFinal, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }
}
