package Interfaces;

import DTO.Animal;

import java.util.List;

public interface OperacaoBD {

    boolean create();

    <T> T detalhe(int codigo);

    List<?> listar();

    boolean atualizar(int codigo);

    boolean deletar(int codigo);

}
