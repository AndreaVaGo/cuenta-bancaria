package dev.andrea.cuentabancaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class CuentaAhorrosTest {

    @Test
    public void testConstructorCuentaActiva() {
        CuentaAhorros cuenta = new CuentaAhorros(10000, 5);
        assertEquals(true, cuenta.getCuentaAhorrosActiva());
    }

    @Test
    public void testConstructorCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(10, 5);
        assertEquals(false, cuenta.getCuentaAhorrosActiva());
    }

    static Stream<Arguments> consignarTestCases() {
        return Stream.of(
            Arguments.of(10000f, 500f, 10500f),
            Arguments.of(10f, 500f, 10f)
        );
    }

    @ParameterizedTest
    @MethodSource("consignarTestCases")
    public void testConsignarParametrizado(float saldoInicial, float cantidad, float saldoEsperado) {
        CuentaAhorros cuenta = new CuentaAhorros(saldoInicial, 5);
        cuenta.consignar(cantidad);
        assertEquals(saldoEsperado, cuenta.saldo);
    }

    static Stream<Arguments> retirarTestCases() {
        return Stream.of(
            Arguments.of(20000f, 500f, 19500f),
            Arguments.of(10f, 500f, 10f)
        );
    }

    @ParameterizedTest
    @MethodSource("retirarTestCases")
    public void testRetirarParametrizado(float saldoInicial, float cantidad, float saldoEsperado) {
        CuentaAhorros cuenta = new CuentaAhorros(saldoInicial, 5);
        cuenta.retirar(cantidad);
        assertEquals(saldoEsperado, cuenta.saldo);
    }

    @Test
    public void testExtractoMensualConComisionExtra() {
        CuentaAhorros cuenta = new CuentaAhorros(20000, 5);
        cuenta.numRetiros = 6;
        cuenta.extractoMensual();
        assertEquals(18075f, cuenta.saldo, 0.1f);
    }

    @Test
    public void testImprimirResumen() {
        CuentaAhorros cuenta = new CuentaAhorros(20000, 5);
        cuenta.consignar(1000);
        cuenta.retirar(500);
        String resultado = cuenta.imprimirResumen();
        assertEquals(true, resultado.contains("Saldo: 20500.0"));
        assertEquals(true, resultado.contains("totalTransacciones: 2"));
    }

}
