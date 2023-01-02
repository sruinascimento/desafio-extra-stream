package br.com.rchlo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;
import br.com.rchlo.domain.Tamanho;

public class Main {
    public static void main(String[] args) {
        List<Produto> camisas = ListaDeProdutos.lista();

        //Mostrar todas as camisetas
        List<Produto> camisetas =  camisas.stream()
            .filter(camisa -> camisa.getNome().toLowerCase().contains("camiseta"))
            .collect(Collectors.toList());
        
        System.out.println("Apresentando o nome de todas as camisetas");
        camisetas.forEach(camiseta -> System.out.println(camiseta.getNome()));

        //Mostrar todas as camisetas
        System.out.println("Apresentando as camisetas brancas");
        camisetas.stream()
            .filter(camisa -> camisa.getCor().equals(Cor.BRANCA))
            .forEach(camisa -> System.out.println(camisa.getNome()));     

        //Mostrar se existe ou não alguma camiseta cinza
        boolean temAlgumaCamisaCinza = camisas.stream()
            .anyMatch(camisa -> camisa.getCor().equals(Cor.CINZA));
        System.out.println("Há alguma camisa cinza? " + temAlgumaCamisaCinza);

        //Mostrar o menor preço de um produto que contenha desconto
        Produto camisaMenorValorComDesconto = camisas.stream()
            .filter(Produto::temDesconto)
            .min((camisa1, camisa2) -> camisa1.getPreco().compareTo(camisa2.getPreco()))
            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        
        System.out.println("Menor preço das camisas que tem desconto RS " + camisaMenorValorComDesconto.getPreco()); 
        
        //Mostrar o produto que tenha o maior preço
        Produto produtoMaiorPreco = camisas.stream()
            // .filter(Produto::temDesconto)
            .max((camisa1, camisa2) -> camisa1.getPreco().compareTo(camisa2.getPreco()))
            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        System.out.println("Produto com o maior preço RS " + produtoMaiorPreco.getPreco()); 

        //Mostrar todas as cores com a quantidade de produtos de cada cor
        Map<Cor, Long> coresPorQuantidade = camisas.stream()
            .collect(Collectors.groupingBy(Produto::getCor, Collectors.counting()));
        System.out.println("Cor - Quantidade");
        coresPorQuantidade.forEach((cor, quantidade) -> System.out.printf("cor: %-8s - quantidade: %d%n", cor, quantidade));

        //Mostrar os tamanhos com os produtos de cada tamanho              
        //Não consegui fazer :'(

    }
}
