package com.CestaSolidaria.domain.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.produto.dto.DataDeteilsProduto;
import com.CestaSolidaria.domain.produto.dto.DataRegisterProduto;
import com.CestaSolidaria.domain.produto.dto.DataUpdatedProduto;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public DataDeteilsProduto addProduto(DataRegisterProduto data) {
		Produto produto = new Produto(data);
		produtoRepository.save(produto);
		return new DataDeteilsProduto(produto);
	}
	
	public DataDeteilsProduto updatedProduto(Long id, DataUpdatedProduto data) {
		
		Produto produto = produtoRepository.findById(id).get();
		
    	if(produto == null) {
    		new EntityNotFoundException("Produto não encontrado");
    	}
		
		if(data.nome() != null && !data.nome().isEmpty()) produto.setNome(data.nome());
		if(data.descricao() != null && !data.descricao().isEmpty()) produto.setDescricao(data.descricao());
		if(data.preco() != 0) produto.setPreco(data.preco());
		if(data.quantidade() != 0) produto.setQuantidade(data.quantidade());
		if(data.volume() != null && !data.volume().isEmpty()) produto.setVolume(data.volume());
		if(data.categoria() != null)produto.setCategoria(data.categoria());
		if(data.urlImagem() != null && !data.urlImagem().isEmpty()) produto.setUrlImagem(data.urlImagem());
		
		produtoRepository.save(produto);

		return new DataDeteilsProduto(produto);
	}
	
	public double valorTotalMercadoria() {
		List<Produto> p = produtoRepository.findAll();
		if (p == null) {
			new EntityNotFoundException("Produto nao encontrado !");
		}
		double total = p.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
		return total;
	}

	public Produto getProduto(Long idProduto) {
		
		Produto produto = produtoRepository.findById(idProduto).get();
		
		if (produto == null) {
			new EntityNotFoundException("Produto nao encontrado !");
		}
		return produto;
	}

	public DataDeteilsProduto atualizarEstoqueProduto(Long id, int q, DataUpdatedProduto produto) {
		
		Produto p = getProduto(id);
		
		p.setQuantidade(p.getQuantidade() + q);
		
		if(p.getQuantidade() < 0) {
			new EntityNotFoundException("Nao a quantidade suficiente.");
		}
		
		produtoRepository.save(p);
		
		return new DataDeteilsProduto(p);
	}

}
