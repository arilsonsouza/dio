package com.aos.dominio;

import java.time.LocalDateTime;

public class Mentoria extends Conteudo {
  private LocalDateTime data;

  @Override
  public double calcularXp() {
    return XP_PADRAO + 10d;
  }

  public LocalDateTime getData() {
    return data;
  }

  public void setData(LocalDateTime data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Mentoria [data=" + data + ", titulo=" + getTitulo() + ", descricao=" + getDescricao() + "]";
  }

}
