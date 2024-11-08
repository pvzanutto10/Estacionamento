package estacionamento;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private Vaga[] vagas;
    private List<Veiculo> veiculos; 
    private List<String> historico; 

    public Estacionamento(int numVagas) {
        vagas = new Vaga[numVagas];
        veiculos = new ArrayList<>();
        historico = new ArrayList<>();
        for (int i = 0; i < numVagas; i++) {
            String tamanho = (i % 3 == 0) ? "grande" : (i % 2 == 0) ? "médio" : "pequeno";
            vagas[i] = new Vaga(i + 1, tamanho); 
        }
    }

    public boolean alocarVaga(Veiculo veiculo, String horaEntrada) {
        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.getTamanho().equals(veiculo.getTamanho())) {
                vaga.ocupar();
                veiculo.registrarEntrada(horaEntrada);
                veiculos.add(veiculo);
                System.out.println("Veículo " + veiculo.getPlaca() + " alocado na vaga " + vaga.getNumero());
                return true;
            }
        }
        System.out.println("Não há vagas disponíveis ou compatíveis para o veículo " + veiculo.getPlaca());
        return false;
    }

    public void registrarSaida(Veiculo veiculo, String horaSaida) {
        if (veiculos.contains(veiculo)) {
            veiculo.registrarSaida(horaSaida);
            int tempo = veiculo.calcularTempoPermanencia();
            double valor = veiculo.calcularValorPago();

            
            for (Vaga vaga : vagas) {
                if (vaga.getTamanho().equals(veiculo.getTamanho())) {
                    vaga.liberar();
                    break;
                }
            }

            veiculos.remove(veiculo);
            historico.add("Veículo " + veiculo.getPlaca() + " saiu com tempo de permanência de "
                    + tempo + " horas com valor a pagar de R$ " + valor);
            System.out.println("Veículo " + veiculo.getPlaca() + " saiu com tempo de permanência de "
                    + tempo + " horas com valor a pagar de R$ " + valor);
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public void mostrarRelatorioVagasOcupadas() {
        System.out.println("Vagas ocupadas:");
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel()) {
                System.out.println(vaga + " | " + veiculos.stream()
                        .filter(v -> v.getTamanho().equals(vaga.getTamanho()))
                        .map(Veiculo::getPlaca)
                        .findFirst().orElse("Veículo não encontrado"));
            }
        }
    }

    public void mostrarHistoricoVeiculos() {
        System.out.println("Histórico de veículos:");
        for (String registro : historico) {
            System.out.println(registro);
        }
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
