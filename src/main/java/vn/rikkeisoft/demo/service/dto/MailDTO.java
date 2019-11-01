package vn.rikkeisoft.demo.service.dto;

import lombok.Data;

import java.util.Map;
@Data
public class MailDTO {
    private String from;

    private String to;

    private String subject;

    private Map<String, Object> model;

}
