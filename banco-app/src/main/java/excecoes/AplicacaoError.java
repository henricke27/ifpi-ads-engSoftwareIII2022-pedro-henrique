package excecoes;

public class AplicacaoError extends RuntimeException{
    public AplicacaoError(String message){
        super(message);
    }
}
