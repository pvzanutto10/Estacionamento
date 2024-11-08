package estacionamento;

import java.util.Scanner;

public class Sistema {

    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento(10); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar veículo");
            System.out.println("2. Registrar saída de veículo");
            System.out.println("3. Mostrar vagas ocupadas");
            System.out.println("4. Mostrar histórico de veículos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Digite o modelo do veículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Digite o tamanho do veículo (pequeno, médio, grande): ");
                    String tamanho = scanner.nextLine();
                    System.out.print("Digite a hora de entrada: ");
                    String horaEntrada = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa, modelo, tamanho);
                    estacionamento.alocarVaga(veiculo, horaEntrada);
                    break;
                case 2:
                    System.out.print("Digite a placa do veículo para registrar a saída: ");
                    String placaSaida = scanner.nextLine();
                    Veiculo veiculoSaida = null;
                    for (Veiculo v : estacionamento.getVeiculos()) {
                        if (v != null && v.getPlaca().equals(placaSaida)) {
                            veiculoSaida = v;
                            break;
                        }
                    }
                    if (veiculoSaida != null) {
                        System.out.print("Digite a hora de saída: ");
                        String horaSaida = scanner.nextLine();
                        estacionamento.registrarSaida(veiculoSaida, horaSaida);
                    } else {
                        System.out.println("Veículo com placa " + placaSaida + " não encontrado.");
                    }
                    break;
                case 3:
                    estacionamento.mostrarRelatorioVagasOcupadas();
                    break;
                case 4:
                    estacionamento.mostrarHistoricoVeiculos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
