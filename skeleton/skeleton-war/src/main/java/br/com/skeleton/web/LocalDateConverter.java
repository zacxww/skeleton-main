package br.com.skeleton.web;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Override
    public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Retorna null se o valor estiver vazio
        }
        try {
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new ConverterException("Formato inválido. Por favor, use aaaa/MM/dd como formato de data.", e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
        if (value == null) {
            return ""; // Retorna string vazia se o valor for null
        }
        return formatter.format(value);
    }
}