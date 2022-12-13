package br.com.rchlo.main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

public class Main {
    public static void main(String[] args) {
        List<Produto> camisas = ListaDeProdutos.lista();

        System.out.println("Apresentando o nome de todas as camisas");
        camisas.forEach(camisa -> System.out.println(camisa.getNome()));

        System.out.println("Apresentando o nome de todas as camisas brancas");
        camisas.stream()
            .filter(camisa -> camisa.getCor() == Cor.BRANCA)
            .forEach(camisa -> System.out.println(camisa.getNome()));     
        // Apresentando todos detalhes
            // .forEach(System.out::println);   
        
        
        boolean temAlgumaCamisaCinza = camisas.stream()
            .anyMatch(camisa -> camisa.getCor() == Cor.CINZA);
        System.out.println("Há alguma camisa cinza? " + temAlgumaCamisaCinza);

        Produto camisaMenorValorComDesconto = camisas.stream()
            .filter(Produto::temDesconto)
            .min((camisa1, camisa2) -> camisa1.getPreco().compareTo(camisa2.getPreco()))
            .get();
        
        System.out.println("Menor preço das camisas que tem desconto RS " + camisaMenorValorComDesconto.getPreco()); 
        
        Produto camisaMaiorValorComDesconto = camisas.stream()
            .filter(Produto::temDesconto)
            .max((camisa1, camisa2) -> camisa1.getPreco().compareTo(camisa2.getPreco()))
            .get();
        
        System.out.println("Maior preço das camisas que tem desconto RS " + camisaMaiorValorComDesconto.getPreco()); 

        Map<Cor, Long> cores = camisas.stream()
            .collect(Collectors.groupingBy(Produto::getCor, Collectors.counting()));
        
        cores.forEach((cor, quantidade) -> System.out.println("cor = " + cor + " quantidade " + quantidade));

        //falta eu apresentar os tamanhos com os produtos de cada tamanho, esse eu preciso estudar mais um pouquinho para realizar              

    }
}
