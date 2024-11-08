package estacionamento;

public class Vaga {
    private int numero;
    private String tamanho; 
    private boolean disponivel;

    public Vaga(int numero, String tamanho) {
        this.numero = numero;
        this.tamanho = tamanho;
        this.disponivel = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void ocupar() {
        this.disponivel = false;
    }

    public void liberar() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return "Vaga " + numero + " (" + tamanho + ")";
    }
}
