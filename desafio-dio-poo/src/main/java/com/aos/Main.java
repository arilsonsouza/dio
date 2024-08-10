package com.aos;

import java.time.LocalDateTime;
import java.time.Month;

import com.aos.dominio.Curso;
import com.aos.dominio.Mentoria;

public class Main {
        public static void main(String[] args) {
                Curso curso = new Curso();
                curso.setTitulo(" Bootcamp Claro - Java com Spring");
                curso.setDescricao(
                                "Nesta trilha, você vai desenvolver suas habilidades com projetos práticos, desafios de códigos e mentorias com experts da DIO e Claro, além disso, ficará disponível na Talent Match para tech recrutadores de empresas parceiras que procuram profissionais com esse perfil.");
                curso.setCargaHoraria(75);

                Mentoria mentoria = new Mentoria();
                mentoria.setTitulo("Desenvolvendo APIs e Microsserviços na Claro");
                mentoria.setDescricao(
                                "Descubra como a Claro desenvolve e gerencia APIs e microsserviços para oferecer soluções ágeis e escaláveis. Nesta mentoria, abordaremos os princípios fundamentais do desenvolvimento de APIs e a arquitetura de microsserviços, destacando sua importância na modernização e eficiência dos serviços.");
                LocalDateTime data = LocalDateTime.of(2024,
                                Month.AUGUST, 13, 16, 00, 00);
                mentoria.setData(data);

                System.out.println(curso);
                System.out.println(mentoria);
        }
}
