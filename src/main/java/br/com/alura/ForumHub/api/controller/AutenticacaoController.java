package br.com.alura.ForumHub.api.controller;

import br.com.alura.ForumHub.api.models.DadosAutenticacao;
import br.com.alura.ForumHub.api.models.Usuario.DadosCadastroUsuario;
import br.com.alura.ForumHub.api.models.Usuario.DadosDetalhesUsuario;
import br.com.alura.ForumHub.api.models.Usuario.Usuario;
import br.com.alura.ForumHub.api.repositories.UsuarioRepository;
import br.com.alura.ForumHub.api.security.DadosTokenJWT;
import br.com.alura.ForumHub.api.services.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosDetalhesUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dadosUsuario, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dadosUsuario);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/login/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesUsuario(usuario));
    }
}
