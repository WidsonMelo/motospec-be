package com.motospec.config;

import com.motospec.entity.Moto;
import com.motospec.entity.Role;
import com.motospec.entity.User;
import com.motospec.repository.MotoRepository;
import com.motospec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MotoRepository motoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Criar usuário padrão
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setEmail("admin@motospec.com");
            admin.setNome("Administrador");
            admin.setSobrenome("Sistema");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);

            User user = new User();
            user.setEmail("user@motospec.com");
            user.setNome("Usuário");
            user.setSobrenome("Teste");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        }

        // Criar motos de exemplo
        if (motoRepository.count() == 0) {
            Moto moto1 = new Moto();
            moto1.setMarca("Honda");
            moto1.setModelo("CB 500F");
            moto1.setAno(2023);
            moto1.setPreco(new BigDecimal("35000.00"));
            moto1.setCor("Preta");
            moto1.setCilindrada(500);
            moto1.setDescricao("Moto naked esportiva, ideal para cidade e estrada");
            motoRepository.save(moto1);

            Moto moto2 = new Moto();
            moto2.setMarca("Yamaha");
            moto2.setModelo("MT-07");
            moto2.setAno(2023);
            moto2.setPreco(new BigDecimal("42000.00"));
            moto2.setCor("Azul");
            moto2.setCilindrada(689);
            moto2.setDescricao("Naked torque master, motor bicilíndrico paralelo");
            motoRepository.save(moto2);

            Moto moto3 = new Moto();
            moto3.setMarca("Kawasaki");
            moto3.setModelo("Ninja 400");
            moto3.setAno(2023);
            moto3.setPreco(new BigDecimal("32000.00"));
            moto3.setCor("Verde");
            moto3.setCilindrada(399);
            moto3.setDescricao("Esportiva leve e ágil, perfeita para iniciantes");
            motoRepository.save(moto3);

            Moto moto4 = new Moto();
            moto4.setMarca("BMW");
            moto4.setModelo("G 310 R");
            moto4.setAno(2023);
            moto4.setPreco(new BigDecimal("28000.00"));
            moto4.setCor("Branca");
            moto4.setCilindrada(313);
            moto4.setDescricao("Roadster alemã compacta e versátil");
            motoRepository.save(moto4);

            Moto moto5 = new Moto();
            moto5.setMarca("Suzuki");
            moto5.setModelo("GSX-S750");
            moto5.setAno(2022);
            moto5.setPreco(new BigDecimal("48000.00"));
            moto5.setCor("Vermelha");
            moto5.setCilindrada(749);
            moto5.setDescricao("Naked esportiva com motor de 4 cilindros");
            motoRepository.save(moto5);

            Moto moto6 = new Moto();
            moto6.setMarca("Honda");
            moto6.setModelo("CB 650R");
            moto6.setAno(2024);
            moto6.setPreco(new BigDecimal("45000.00"));
            moto6.setCor("Preta");
            moto6.setCilindrada(649);
            moto6.setDescricao("Naked de média cilindrada com design agressivo");
            motoRepository.save(moto6);

            Moto moto7 = new Moto();
            moto7.setMarca("Yamaha");
            moto7.setModelo("YZF-R3");
            moto7.setAno(2024);
            moto7.setPreco(new BigDecimal("29000.00"));
            moto7.setCor("Azul");
            moto7.setCilindrada(321);
            moto7.setDescricao("Esportiva de entrada com design inspirado nas MotoGP");
            motoRepository.save(moto7);

            Moto moto8 = new Moto();
            moto8.setMarca("Kawasaki");
            moto8.setModelo("Z900");
            moto8.setAno(2024);
            moto8.setPreco(new BigDecimal("52000.00"));
            moto8.setCor("Verde");
            moto8.setCilindrada(948);
            moto8.setDescricao("Supernaked com excelente relação peso/potência");
            motoRepository.save(moto8);

            Moto moto9 = new Moto();
            moto9.setMarca("BMW");
            moto9.setModelo("S1000RR");
            moto9.setAno(2024);
            moto9.setPreco(new BigDecimal("95000.00"));
            moto9.setCor("Branca");
            moto9.setCilindrada(999);
            moto9.setDescricao("Superbike premium com tecnologia de ponta");
            motoRepository.save(moto9);

            Moto moto10 = new Moto();
            moto10.setMarca("Ducati");
            moto10.setModelo("Panigale V2");
            moto10.setAno(2024);
            moto10.setPreco(new BigDecimal("89000.00"));
            moto10.setCor("Vermelha");
            moto10.setCilindrada(955);
            moto10.setDescricao("Esportiva italiana com design icônico");
            motoRepository.save(moto10);

            Moto moto11 = new Moto();
            moto11.setMarca("Honda");
            moto11.setModelo("CG 160");
            moto11.setAno(2023);
            moto11.setPreco(new BigDecimal("12000.00"));
            moto11.setCor("Vermelha");
            moto11.setCilindrada(162);
            moto11.setDescricao("Moto econômica e confiável para o dia a dia");
            motoRepository.save(moto11);

            Moto moto12 = new Moto();
            moto12.setMarca("Yamaha");
            moto12.setModelo("Factor 150");
            moto12.setAno(2023);
            moto12.setPreco(new BigDecimal("13500.00"));
            moto12.setCor("Preta");
            moto12.setCilindrada(149);
            moto12.setDescricao("Moto urbana com ótimo consumo de combustível");
            motoRepository.save(moto12);

            Moto moto13 = new Moto();
            moto13.setMarca("Kawasaki");
            moto13.setModelo("Versys 650");
            moto13.setAno(2023);
            moto13.setPreco(new BigDecimal("48000.00"));
            moto13.setCor("Verde");
            moto13.setCilindrada(649);
            moto13.setDescricao("Adventure touring versátil e confortável");
            motoRepository.save(moto13);

            Moto moto14 = new Moto();
            moto14.setMarca("Triumph");
            moto14.setModelo("Street Triple");
            moto14.setAno(2024);
            moto14.setPreco(new BigDecimal("58000.00"));
            moto14.setCor("Branca");
            moto14.setCilindrada(765);
            moto14.setDescricao("Naked britânica com motor três cilindros");
            motoRepository.save(moto14);

            Moto moto15 = new Moto();
            moto15.setMarca("KTM");
            moto15.setModelo("Duke 390");
            moto15.setAno(2024);
            moto15.setPreco(new BigDecimal("33000.00"));
            moto15.setCor("Laranja");
            moto15.setCilindrada(373);
            moto15.setDescricao("Naked austríaca ágil e divertida");
            motoRepository.save(moto15);
        }
    }
}
