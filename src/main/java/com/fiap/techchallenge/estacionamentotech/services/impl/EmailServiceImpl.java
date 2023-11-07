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

    @Autowired
    private JavaMailSender javaMailSender;

    private String carregarConteudoHtml(String caminhoArquivo) throws IOException {
        File arquivoHtml = new File(caminhoArquivo);
        char[] buffer = new char[(int) arquivoHtml.length()];
        FileReader fileReader = new FileReader(arquivoHtml);
        fileReader.read(buffer);
        return new String(buffer);
    }

    public void enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO) {

        String conteudoHtml = null;
        try {
            conteudoHtml = carregarConteudoHtml("resources/templates/email-template.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        conteudoHtml = conteudoHtml.replace("[[NOME_DO_CLIENTE]]", veiculoEstacionadoDTO.getIdVeiculo().toString());
        conteudoHtml = conteudoHtml.replace("[[DATA_HORA]]", veiculoEstacionadoDTO.getDataHoraInicio().toString());
        conteudoHtml = conteudoHtml.replace("[[VALOR_PAGO]]", veiculoEstacionadoDTO.getIdLocalEstacionamento().toString());
        conteudoHtml = conteudoHtml.replace("[[LOCAL]]", veiculoEstacionadoDTO.getIdLocalEstacionamento().toString());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo("teste");
            helper.setSubject("teste");
            helper.setText(conteudoHtml, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }
}
