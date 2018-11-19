package br.com.cursojsf;

import java.util.List;

import javax.persistence.Persistence;

import br.com.entidades.Lancamento;

public class TesteJPA {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
		
		LancamentoBean lb = new LancamentoBean();
		lb.carregarLancamentos();
		List<Lancamento> list = lb.getLancamentos();
		
		for (Lancamento lancamento : list) {
			System.out.println(lancamento);
		}

		
	}

}
