package br.csi.floricultura.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode("admin"); // troque 1234 pela senha desejada
        System.out.println(senhaCriptografada);
    }
}
