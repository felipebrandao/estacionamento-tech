package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.services.VeiculoService;
import com.fiap.techchallenge.estacionamentotech.utils.UserDetailsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final UserDetailsUtil userDetailsUtil;

    @Autowired
    public VeiculoController(VeiculoService veiculoService, UserDetailsUtil userDetailsUtil) {
        this.veiculoService = veiculoService;
        this.userDetailsUtil = userDetailsUtil;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculo) {
        Usuario usuario = userDetailsUtil.getLoggedUsuario();

        VeiculoDTO veiculoCadastrado = veiculoService.cadastrarVeiculo(veiculo, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorId(@PathVariable Long id) {
        Usuario usuario = userDetailsUtil.getLoggedUsuario();

        VeiculoDTO veiculo = veiculoService.buscarVeiculoPorIdEUsuario(id, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(veiculo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculos() {
        Usuario usuario = userDetailsUtil.getLoggedUsuario();

        List<VeiculoDTO> veiculos = veiculoService.listarVeiculosPorUsuario(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(veiculos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        Usuario usuario = userDetailsUtil.getLoggedUsuario();
        veiculoService.excluirVeiculo(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
