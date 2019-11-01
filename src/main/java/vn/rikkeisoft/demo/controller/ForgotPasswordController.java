package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.rikkeisoft.demo.entity.AccountEntity;
import vn.rikkeisoft.demo.entity.PasswordResetToken;
import vn.rikkeisoft.demo.repositories.PasswordResetTokenRepository;
import vn.rikkeisoft.demo.service.AccountService;
import vn.rikkeisoft.demo.service.EmailService;
import vn.rikkeisoft.demo.service.dto.AccountDTO;
import vn.rikkeisoft.demo.service.dto.MailDTO;
import vn.rikkeisoft.demo.service.dto.PasswordForgotDTO;
import vn.rikkeisoft.demo.service.mapper.AccountMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordResetTokenRepository tokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    AccountMapper accountMapper;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDTO forgotPasswordDto() {
        return new PasswordForgotDTO();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "password/forgotpassword";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDTO form,
                                            BindingResult result,
                                            HttpServletRequest request) {
        if (result.hasErrors()){
            return "forgot-password";
        }

        AccountDTO dto = accountService.findByEmail(form.getEmail());
        if (dto == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setAccount(accountMapper.toEntity(dto));
        token.setExpiryDate(30);
        tokenRepository.save(token);
        MailDTO mail = new MailDTO();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(dto.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("dto", dto);
        model.put("signature", "quangha12thxd@gmail.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
        return "redirect:/forgot-password?success";
    }

}
