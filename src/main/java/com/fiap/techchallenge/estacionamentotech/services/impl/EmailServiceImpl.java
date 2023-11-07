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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

@Service
public class EmailServiceImpl implements EmailService {
    private final ResourceLoader resourceLoader;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(ResourceLoader resourceLoader, JavaMailSender javaMailSender) {
        this.resourceLoader = resourceLoader;
        this.javaMailSender = javaMailSender;
    }

    private String carregarConteudoHtml() throws IOException {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + "/templates/email-template.html");
            byte[] byteArray = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(byteArray, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(EmailEstacionamentoDTO emailEstacionamentoDTO) {

        String conteudoHtml;
        try {
            conteudoHtml = carregarConteudoHtml();
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

        StringBuilder conteudoVoucher = criarConteudoVoucher(emailEstacionamentoDTO);

        conteudoHtml = conteudoHtml.replace("[$conteudo_voucher]", conteudoVoucher.toString());


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
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

    private static StringBuilder criarConteudoVoucher(EmailEstacionamentoDTO emailEstacionamentoDTO) {
        StringBuilder conteudoVoucher = new StringBuilder();

        for (VoucherEstacionamentoDTO voucherEstacionamentoDTO : emailEstacionamentoDTO.getVoucherEstacionamentoDTOList()) {
            String linhaHTML = "<tr>" +
                    "<td class=\"tg-0lax\">" + voucherEstacionamentoDTO.getHorasEstacionado() + "</td>" +
                    "<td class=\"tg-0lax\">" + voucherEstacionamentoDTO.getFormaDePagamento() + "</td>" +
                    "</tr>";

            conteudoVoucher.append(linhaHTML);
        }
        return conteudoVoucher;
    }
}
