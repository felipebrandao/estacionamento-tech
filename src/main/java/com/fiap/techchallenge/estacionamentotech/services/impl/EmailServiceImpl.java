package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.EmailEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

@Service
public class EmailServiceImpl implements EmailService {

    private final ResourceLoader resourceLoader;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailServiceImpl(ResourceLoader resourceLoader, JavaMailSender javaMailSender) {
        this.resourceLoader = resourceLoader;
        this.javaMailSender = javaMailSender;
    }

    private String carregarConteudoHtml(String caminhoArquivo) throws IOException {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + caminhoArquivo);
            byte[] byteArray = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(byteArray, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(EmailEstacionamentoDTO emailEstacionamentoDTO) {

        String conteudoHtml = null;
        try {
            conteudoHtml = carregarConteudoHtml("/templates/email-template.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        conteudoHtml = conteudoHtml.replace("[$nome_usuario]", emailEstacionamentoDTO.getNomeDoUsuario());
        conteudoHtml = conteudoHtml.replace("[$data_hora_inicio]",
                                            emailEstacionamentoDTO.getDataHoraInicioEstacionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        conteudoHtml = conteudoHtml.replace("[$logradouro]", emailEstacionamentoDTO.getLogradouro());
        conteudoHtml = conteudoHtml.replace("[$bairro]", emailEstacionamentoDTO.getBairro());
        conteudoHtml = conteudoHtml.replace("[$cep]", emailEstacionamentoDTO.getCep());
        conteudoHtml = conteudoHtml.replace("[$intervalo_de_numero]", emailEstacionamentoDTO.getIntervaloDeNumero());

        conteudoHtml = conteudoHtml.replace("[$marca]", emailEstacionamentoDTO.getMarca());
        conteudoHtml = conteudoHtml.replace("[$modelo]", emailEstacionamentoDTO.getModelo());
        conteudoHtml = conteudoHtml.replace("[$placa]", emailEstacionamentoDTO.getPlaca());

        conteudoHtml = conteudoHtml.replace("[$data_hora_fim_estacionamento]",
                                            emailEstacionamentoDTO.getDataHoraFimEstacionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        String conteudoVoucher = "";

        for (VoucherEstacionamentoDTO voucherEstacionamentoDTO : emailEstacionamentoDTO.getVoucherEstacionamentoDTOList()) {
            String linhaHTML = "<tr>" +
                    "<td class=\"tg-0lax\">" + voucherEstacionamentoDTO.getQtdeDeHorasEstacionado() + "</td>" +
                    "<td class=\"tg-0lax\">" + voucherEstacionamentoDTO.getFormaDePagamento() + "</td>" +
                    "</tr>";

            conteudoVoucher += linhaHTML;
        }

        conteudoHtml = conteudoHtml.replace("[$conteudo_voucher]", conteudoVoucher);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(emailEstacionamentoDTO.getEmail());
            helper.setSubject(emailEstacionamentoDTO.getAssunto());
            helper.setText(conteudoHtml, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }

}
