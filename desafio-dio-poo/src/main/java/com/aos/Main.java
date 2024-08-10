package com.aos;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedHashSet;
import java.util.Set;

import com.aos.dominio.Bootcamp;
import com.aos.dominio.Conteudo;
import com.aos.dominio.Curso;
import com.aos.dominio.Dev;
import com.aos.dominio.Mentoria;

public class Main {
        public static void main(String[] args) {
                Curso curso = new Curso();
                curso.setTitulo("Try/Catch");
                curso.setDescricao("Java e Tratamento de Exceções");
                curso.setCargaHoraria(75);

                Mentoria mentoria = new Mentoria();
                mentoria.setTitulo("Desenvolvendo APIs e Microsserviços na Claro");
                mentoria.setDescricao(
                                "Descubra como a Claro desenvolve e gerencia APIs e microsserviços para oferecer soluções ágeis e escaláveis. Nesta mentoria, abordaremos os princípios fundamentais do desenvolvimento de APIs e a arquitetura de microsserviços, destacando sua importância na modernização e eficiência dos serviços.");
                LocalDateTime data = LocalDateTime.of(2024,
                                Month.AUGUST, 13, 16, 00, 00);
                mentoria.setData(data);

                Bootcamp bootcamp = new Bootcamp();
                bootcamp.setNome("Claro - Java com Spring Boot");
                bootcamp.setDescricao("Construa uma API com Java e Spring de ponta a ponta!");

                Set<Conteudo> conteudos = new LinkedHashSet<>();
                conteudos.add(curso);
                conteudos.add(mentoria);

                bootcamp.setConteudos(conteudos);

                Dev devArilson = new Dev();
                devArilson.setNome("Arilson");

                devArilson.inscreverBootcamp(bootcamp);

                devArilson.listarConteudosInscritos();
                devArilson.progredir();
                devArilson.listarConteudosConcluidos();
        }
}
