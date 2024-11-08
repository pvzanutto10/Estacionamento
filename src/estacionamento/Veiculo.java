package estacionamento;

public class Veiculo {
    private String placa;
    private String modelo;
    private String tamanho; 
    private int horaEntrada;
    private int minutoEntrada;
    private int horaSaida;
    private int minutoSaida;

    public Veiculo(String placa, String modelo, String tamanho) {
        this.placa = placa;
        this.modelo = modelo;
        this.tamanho = tamanho;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void registrarEntrada(String horaEntrada) {
        String[] partes = horaEntrada.split(":");
        this.horaEntrada = Integer.parseInt(partes[0]);
        this.minutoEntrada = Integer.parseInt(partes[1]);
    }

    public void registrarSaida(String horaSaida) {
        String[] partes = horaSaida.split(":");
        this.horaSaida = Integer.parseInt(partes[0]);
        this.minutoSaida = Integer.parseInt(partes[1]);
    }

    public int calcularTempoPermanencia() {
        int entradaTotalMinutos = horaEntrada * 60 + minutoEntrada;
        int saidaTotalMinutos = horaSaida * 60 + minutoSaida;
        return (saidaTotalMinutos - entradaTotalMinutos) / 60;
    }

    public double calcularValorPago() {
        int horas = calcularTempoPermanencia();
        if (horas <= 1) {
            return 5.0;
        } else if (horas <= 3) {
            return 10.0;
        } else {
            return 15.0;
        }
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Tamanho: " + tamanho;
    }
}
