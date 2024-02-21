import java.util.List;

import dao.DaoContato;
import entidades.Contato;

public class App {
    public static void main(String[] args) {
        // Exemplo de utilização das classes de persistência

        // Criando um novo contato
        Contato novoContato = new Contato();
        novoContato.setNome("João");
        novoContato.setEmail("joao@example.com");
        novoContato.setFone("123456789");

        // Salvando o contato no banco de dados
        boolean sucessoSalvar = DaoContato.salvar(novoContato);
        if (sucessoSalvar) {
            System.out.println("Contato salvo com sucesso!");
        } else {
            System.out.println("Erro ao salvar o contato.");
        }

        // Consultando todos os contatos
        List<Contato> contatos = DaoContato.consultar();
        System.out.println("Lista de contatos:");
        for (Contato contato : contatos) {
            System.out.println(contato.getId() + ": " + contato.getNome() + " - " + contato.getEmail() + " - " + contato.getFone());
        }

        // Consultando um contato pelo ID
        Contato contatoConsultado = DaoContato.consultarPeloId(1);
        if (contatoConsultado.getId() != 0) {
            System.out.println("Contato encontrado:");
            System.out.println(contatoConsultado.getId() + ": " + contatoConsultado.getNome() + " - " + contatoConsultado.getEmail() + " - " + contatoConsultado.getFone());
        } else {
            System.out.println("Contato não encontrado.");
        }

        // Excluindo um contato pelo ID
        int idContatoExcluir = 1;
        DaoContato.excluir(idContatoExcluir);
        System.out.println("Contato com ID " + idContatoExcluir + " excluído.");

        // Verificando a lista de contatos após a exclusão
        System.out.println("Lista de contatos após a exclusão:");
        contatos = DaoContato.consultar();
        for (Contato contato : contatos) {
            System.out.println(contato.getId() + ": " + contato.getNome() + " - " + contato.getEmail() + " - " + contato.getFone());
        }
    }
}
