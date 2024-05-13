package com.jaff.tiendaOnline.Service.EmailService;

import com.jaff.tiendaOnline.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(Customer customer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("soporte@jaffantoniofigueroa.com");
        message.setTo(customer.getEmail());
        message.setSubject("Bienvenido a nuestra Tienda de Antonio Figueroa JAFF");
        message.setText("Hola " + customer.getName() + ",\n\nGracias por registrarte en nuestra Tienda online. \n\nSaludos,\nEl equipo de nuestra aplicación");
        mailSender.send(message);
    }

    public void sendNewPasswordEmail(Customer customer, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("SoporteTecnico@jaffantoniofigueroa.com");
        message.setTo(customer.getEmail());
        message.setSubject("Tu nueva contraseña");
        message.setText("Hola " + customer.getName() + ",\n\nHemos generado una nueva contraseña para ti: " + newPassword + "\n\nPor favor, inicia sesión con esta nueva contraseña y luego cámbiala por seguridad.");
        mailSender.send(message);
    }
}
