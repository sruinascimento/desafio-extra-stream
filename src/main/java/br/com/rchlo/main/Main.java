package br.com.rchlo.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;
import br.com.rchlo.domain.Tamanho;

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();

        //Mostrar todas as camisetas
        List<Produto> camisetas =  produtos.stream()
            .filter(camisa -> camisa.getNome().toLowerCase().contains("camiseta"))
            .collect(Collectors.toList());
        
        System.out.println("Apresentando o nome de todas as camisetas");
        camisetas.forEach(camiseta -> System.out.println(camiseta.getNome()));

        //Mostrar todas as camisetas
        System.out.println("Apresentando as camisetas brancas");
        camisetas.stream()
            .filter(camisa -> Cor.BRANCA.equals(camisa.getCor()))
            .forEach(camisa -> System.out.println(camisa.getNome()));     

        //Mostrar se existe ou não alguma camiseta cinza
        boolean temAlgumaCamisaCinza = produtos.stream()
            .anyMatch(camisa -> Cor.CINZA.equals(camisa.getCor()));
        System.out.println("Há alguma camisa cinza? " + temAlgumaCamisaCinza);

        //Mostrar o menor preço de um produto que contenha desconto
        Produto camisaMenorValorComDesconto = produtos.stream()
            .filter(Produto::temDesconto)
            .min(Comparator.comparing(Produto::getPreco))
            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        
        System.out.println("Menor preço das produtos que tem desconto RS " + camisaMenorValorComDesconto.getPreco()); 
        
        //Mostrar o produto que tenha o maior preço
        Produto produtoMaiorPreco = produtos.stream()
            // .filter(Produto::temDesconto)
            .max(Comparator.comparing(Produto::getPreco))
            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        System.out.println("Produto com o maior preço RS " + produtoMaiorPreco.getPreco()); 

        //Mostrar todas as cores com a quantidade de produtos de cada cor
        Map<Cor, Long> coresPorQuantidade = produtos.stream()
            .collect(Collectors.groupingBy(Produto::getCor, Collectors.counting()));
        System.out.println("Cor - Quantidade");
        coresPorQuantidade.forEach((cor, quantidade) -> System.out.printf("cor: %-8s - quantidade: %d%n", cor, quantidade));

        //Mostrar os tamanhos com os produtos de cada tamanho              
    
        Map<Tamanho, List<String>> produtoPorTamanho = new HashMap<>();
        for (Tamanho tamanho: Tamanho.values()) {
            produtoPorTamanho.put(tamanho, new ArrayList<>());
            for (Produto produto : produtos) {
                if (produto.getTamanhosDisponiveis().contains(tamanho)) {
                    produtoPorTamanho.get(tamanho).add(produto.getNome());
                }
            }
        }

        produtoPorTamanho.forEach((chave, valor) -> System.out.println(chave + " " + valor));
        
    }
}
