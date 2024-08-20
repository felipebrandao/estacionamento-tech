package com.fiap.techchallenge.notificacao.services.impl;

import com.fiap.techchallenge.notificacao.dtos.EmailEstacionamentoDTO;
import com.fiap.techchallenge.notificacao.dtos.NotificacaoDTO;
import com.fiap.techchallenge.notificacao.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.notificacao.enums.TipoEmailEnum;
import com.fiap.techchallenge.notificacao.services.EmailService;
import com.fiap.techchallenge.notificacao.services.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private EmailService emailService;

    @Autowired
    public NotificacaoServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    public void enviarEmail(NotificacaoDTO notificacaoDTO) {

        TipoEmailEnum tipoEmail = notificacaoDTO.getTipoEmail();

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("nome_usuario", notificacaoDTO.getNomeDoUsuario());
        placeholders.put("data_hora_inicio", notificacaoDTO.getDataHoraInicioEstacionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        placeholders.put("logradouro", notificacaoDTO.getLogradouro());
        placeholders.put("bairro", notificacaoDTO.getBairro());
        placeholders.put("cep", notificacaoDTO.getCep());
        placeholders.put("intervalo_de_numero", notificacaoDTO.getIntervaloDeNumero());
        placeholders.put("marca", notificacaoDTO.getMarca());
        placeholders.put("modelo", notificacaoDTO.getModelo());
        placeholders.put("placa", notificacaoDTO.getPlaca());
        placeholders.put("data_hora_fim_estacionamento", notificacaoDTO.getDataHoraFimEstacionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        placeholders.put("conteudo_voucher", criarConteudoVoucher(notificacaoDTO).toString());

        String conteudoHtml = tipoEmail.gerarConteudoEmail(placeholders);

        emailService.enviarEmail(notificacaoDTO.getEmail(), tipoEmail.getAssunto(), conteudoHtml);

    }

//    public void enviarEmail(NotificacaoDTO notificacaoDTO) {
//
//        String conteudoHtml;
//        try {
//            conteudoHtml = carregarConteudoHtml(emailEstacionamentoDTO.getTipoEmail());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        conteudoHtml = conteudoHtml.replace("[$nome_usuario]", emailEstacionamentoDTO.getNomeDoUsuario());
//        conteudoHtml = conteudoHtml.replace("[$data_hora_inicio]",
//                emailEstacionamentoDTO.getDataHoraInicioEstacionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
//
//        conteudoHtml = conteudoHtml.replace("[$logradouro]", emailEstacionamentoDTO.getLogradouro());
//        conteudoHtml = conteudoHtml.replace("[$bairro]", emailEstacionamentoDTO.getBairro());
//        conteudoHtml = conteudoHtml.replace("[$cep]", emailEstacionamentoDTO.getCep());
//        conteudoHtml = conteudoHtml.replace("[$intervalo_de_numero]", emailEstacionamentoDTO.getIntervaloDeNumero());
//
//        conteudoHtml = conteudoHtml.replace("[$marca]", emailEstacionamentoDTO.getMarca());
//        conteudoHtml = conteudoHtml.replace("[$modelo]", emailEstacionamentoDTO.getModelo());
//        conteudoHtml = conteudoHtml.replace("[$placa]", emailEstacionamentoDTO.getPlaca());
//
//        conteudoHtml = conteudoHtml.replace("[$data_hora_fim_estacionamento]",
//                emailEstacionamentoDTO.getDataHoraFimEstacionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
//
//        StringBuilder conteudoVoucher = criarConteudoVoucher(emailEstacionamentoDTO);
//
//        conteudoHtml = conteudoHtml.replace("[$conteudo_voucher]", conteudoVoucher.toString());
//
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper;
//        try {
//            helper = new MimeMessageHelper(message, true);
//            helper.setTo(emailEstacionamentoDTO.getEmail());
//            helper.setSubject(emailEstacionamentoDTO.getAssunto());
//            helper.setText(conteudoHtml, true);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//        javaMailSender.send(message);
//    }


//    private static StringBuilder criarConteudoVoucher(EmailEstacionamentoDTO emailEstacionamentoDTO) {
//        StringBuilder conteudoVoucher = new StringBuilder();
//
//        for (VoucherEstacionamentoDTO voucherEstacionamentoDTO : emailEstacionamentoDTO.getVoucherEstacionamentoDTOList()) {
//            String linhaHTML = "<tr>" +
//                    "<td class=\"tg-0lax\">" + voucherEstacionamentoDTO.getQtdeDeHorasEstacionado() + "</td>" +
//                    "<td class=\"tg-0lax\">" + voucherEstacionamentoDTO.getFormaDePagamento() + "</td>" +
//                    "</tr>";
//
//            conteudoVoucher.append(linhaHTML);
//        }
//        return conteudoVoucher;
//    }

    private static String criarConteudoVoucher(EmailEstacionamentoDTO emailEstacionamentoDTO) {
        StringBuilder conteudoVoucher = new StringBuilder();
        String linhaTemplate = "<tr><td class=\"tg-0lax\">%s</td><td class=\"tg-0lax\">%s</td></tr>";

        for (VoucherEstacionamentoDTO voucher : emailEstacionamentoDTO.getVoucherEstacionamentoDTOList()) {
            String horasEstacionado = String.valueOf(voucher.getQtdeDeHorasEstacionado());
            String formaPagamento = voucher.getFormaDePagamento();
            conteudoVoucher.append(String.format(linhaTemplate, horasEstacionado, formaPagamento));
        }

        return conteudoVoucher.toString();
    }

}
