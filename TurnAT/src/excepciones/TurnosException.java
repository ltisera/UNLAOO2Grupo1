package excepciones;

public class TurnosException extends RuntimeException {
    public TurnosException(String mensaje) {
        super(mensaje);
    }

    public TurnosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
