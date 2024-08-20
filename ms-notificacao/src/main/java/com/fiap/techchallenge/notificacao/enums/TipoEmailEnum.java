package com.fiap.techchallenge.notificacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum TipoEmailEnum {

    ESTACIONAMENTO_REGISTRADO("Estacionamento Tech - Registro de Estacionamento", "/templates/email-notificacao-estacionamento.html") {
        @Override
        public String gerarConteudoEmail(Map<String, String> placeholders) {
            return super.processarTemplate(placeholders);
        }
    },

    ESTACIONAMENTO_EXPIRANDO("Estacionamento Tech - Aviso de Expiração", "/templates/email-notificacao-expiracao-estacionamento.html") {
        @Override
        public String gerarConteudoEmail(Map<String, String> placeholders) {
            return super.processarTemplate(placeholders);
        }
    },
    HORAS_ADICIONADAS("Estacionamento Tech - Horas Adicionadas", "/templates/email-notificacao-horas-adicionadas.html") {
        @Override
        public String gerarConteudoEmail(Map<String, String> placeholders) {
            return super.processarTemplate(placeholders);
        }
    },
    ESTACIONAMENTO_EXPIRADO("Estacionamento Tech - Expirado", "/templates/email-notificacao-expirado-estacionamento.html") {
        @Override
        public String gerarConteudoEmail(Map<String, String> placeholders) {
            return super.processarTemplate(placeholders);
        }
    },
    MULTA_REGISTRADA("Estacionamento Tech - Multa Registrada", "/templates/email-notificacao-multa.html") {
        @Override
        public String gerarConteudoEmail(Map<String, String> placeholders) {
            return super.processarTemplate(placeholders);
        }
    };

    private final String assunto;
    private final String caminho;

    public abstract String gerarConteudoEmail(Map<String, String> placeholders);

    protected String processarTemplate(Map<String, String> placeholders) {
        try {
            Path templatePath = new ClassPathResource(caminho).getFile().toPath();
            String templateContent = Files.readString(templatePath);

            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                String placeholder = String.format("[$%s]", entry.getKey());
                templateContent = templateContent.replace(placeholder, entry.getValue());
            }

            return templateContent;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o template de e-mail", e);
        }
    }
}
