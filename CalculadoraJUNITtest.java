import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraJUNITtest {
    private CalculadoraSwing calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraSwing();
    }

    @Test
    public void testSuma() {
        calculadora.primerOperando = 5;
        calculadora.segundoOperando = 3;
        calculadora.operador = '+';
        double resultado = calculadora.realizarCalculo();
        assertEquals(8, resultado);
    }

    @Test
    public void testResta() {
        calculadora.primerOperando = 10;
        calculadora.segundoOperando = 7;
        calculadora.operador = '-';
        double resultado = calculadora.realizarCalculo();
        assertEquals(3, resultado);
    }

    @Test
    public void testMultiplicacion() {
        calculadora.primerOperando = 4;
        calculadora.segundoOperando = 6;
        calculadora.operador = '*';
        double resultado = calculadora.realizarCalculo();
        assertEquals(24, resultado);
    }

    @Test
    public void testDivision() {
        calculadora.primerOperando = 15;
        calculadora.segundoOperando = 3;
        calculadora.operador = '/';
        double resultado = calculadora.realizarCalculo();
        assertEquals(5, resultado);
    }

    @Test
    public void testDivisionEntreCero() {
        calculadora.primerOperando = 10;
        calculadora.segundoOperando = 0;
        calculadora.operador = '/';
        assertThrows(ArithmeticException.class, calculadora::realizarCalculo);
    }
}
