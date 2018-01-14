package com.williamsilva.cursomc.cursomc;

import com.williamsilva.cursomc.cursomc.model.*;
import com.williamsilva.cursomc.cursomc.model.enums.TipoCliente;
import com.williamsilva.cursomc.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication

public class CursomcApplication implements CommandLineRunner{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        Categoria categoria1 = new Categoria("Informática");
        Categoria categoria2 = new Categoria("Escritório");

        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);

        categoria1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        categoria2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(categoria1));
        p2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        p3.getCategorias().addAll(Arrays.asList(categoria1));

        categoriaRepository.save(Arrays.asList(categoria1, categoria2));
        produtoRepository.save(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("São Paulo");

        Cidade c1 = new Cidade("Uberlância", est1);
        Cidade c2 = new Cidade("São Paulo", est2);
        Cidade c3 = new Cidade("Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.save(Arrays.asList(est1, est2));
        cidadeRepository.save(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36378912377",
                TipoCliente.PESSOAFISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = new Endereco("Rua flores", 300, "Apto 303", "Jardim",
                "38220834", cli1, c1);

        Endereco e2 = new Endereco("Avenida Matos", 105, "Sala 800", "Centro",
                "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.save(cli1);
        enderecoRepository.save(Arrays.asList(e1, e2));








    }
}
