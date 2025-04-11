package lucaslimb.com.github.leilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucaslimb.com.github.leilao.dto.NovoLanceDto;
import lucaslimb.com.github.leilao.model.Lance;
import lucaslimb.com.github.leilao.model.Leilao;
import lucaslimb.com.github.leilao.model.Usuario;
import lucaslimb.com.github.leilao.repositories.LanceRepository;
import lucaslimb.com.github.leilao.repositories.LeilaoRepository;
import lucaslimb.com.github.leilao.repositories.UsuarioRepository;

@Service
public class LanceService {

	@Autowired
	private LanceRepository lances;

	@Autowired
	private UsuarioRepository usuarios;

	@Autowired
	private LeilaoRepository leiloes;

	public boolean propoeLance(NovoLanceDto lanceDto, String nomeUsuario) {

		Usuario usuario = usuarios.getUserByUsername(nomeUsuario);
		Lance lance = lanceDto.toLance(usuario);

		Leilao leilao = this.getLeilao(lanceDto.getLeilaoId());

		if (leilao.propoe(lance)) {
			lances.save(lance);
			return true;
		}

		return false;
	}

	public Leilao getLeilao(Long leilaoId) {
		return leiloes.getOne(leilaoId);
	}

}