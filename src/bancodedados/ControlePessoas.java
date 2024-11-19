package bancodedados;

import java.util.ArrayList;
import cadastro.Pessoa;

public class ControlePessoas {

    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    public boolean salvar(Pessoa p) {
        if (p != null) {
            pessoas.add(p);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Pessoa> imprimir() {
        return pessoas;
    }

    public boolean atualizar(Pessoa p) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId() == p.getId()) {
                pessoas.set(i, p);
                return true;
            }
        }
        return false;
    }

    public boolean remover(int id) {
        return pessoas.removeIf(p -> p.getId() == id);
    }
}
