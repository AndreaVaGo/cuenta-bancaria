package dev.andrea.cuentabancaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class CuentaTest {

    @Test
    public void testConstructor() {
        Cuenta cuenta = new Cuenta(50000, 5);
        assertEquals(50000, cuenta.saldo);
        assertEquals(5, cuenta.tasaAnual);
    }

    @Test
    public void testConsignar() {
        Cuenta cuenta = new Cuenta(1000, 5);
        cuenta.consignar(500);
        assertEquals(1500, cuenta.saldo);
        assertEquals(1, cuenta.numConsignaciones);
    }

    static Stream<Arguments> retirarTestCases() {
        return Stream.of(
            Arguments.of(1000f, 300f, 700f, 1),
            Arguments.of(1000f, 1500f, 1000f, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("retirarTestCases")
    public void testRetirarParametrizado(float saldoInicial, float cantidad, float saldoEsperado, int numRetirosEsperado) {
        Cuenta cuenta = new Cuenta(saldoInicial, 5);
        cuenta.retirar(cantidad);
        assertEquals(saldoEsperado, cuenta.saldo);
        assertEquals(numRetirosEsperado, cuenta.numRetiros);
    }

    @Test
    public void testCalcularInteresMensual() {
        Cuenta cuenta = new Cuenta(50000, 5);
        cuenta.calcularInteresMensual();
        assertEquals(50208.33f, cuenta.saldo, 0.1f);
    }

    @Test
    public void testExtractoMensual() {
        Cuenta cuenta = new Cuenta(50000, 5);
        cuenta.comisionMensual = 1000;
        cuenta.extractoMensual();
        assertEquals(49204.17f, cuenta.saldo, 0.1f);
    }

    @Test
    public void testImprimir() {
        Cuenta cuenta = new Cuenta(50000, 5);
        String resultado = cuenta.imprimir();
        assertTrue(resultado.contains("Saldo: 50000.0"));
        assertTrue(resultado.contains("tasaAnual: 5.0"));
    }
}
