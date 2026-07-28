package dev.andrea.cuentabancaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class CuentaCorrienteTest {

    @Test
    public void testConstructor() {
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        assertEquals(0, cuenta.getSobregiro());
    }

    static Stream<Arguments> retirarTestCases() {
        return Stream.of(
            Arguments.of(20000f, 500f, 19500f, 0f),
            Arguments.of(1000f, 1500f, 0f, 500f)
        );
    }

    @ParameterizedTest
    @MethodSource("retirarTestCases")
    public void testRetirarParametrizado(float saldoInicial, float cantidad, float saldoEsperado, float sobreGiroEsperado) {
        CuentaCorriente cuenta = new CuentaCorriente(saldoInicial, 5);
        cuenta.retirar(cantidad);
        assertEquals(saldoEsperado, cuenta.saldo);
        assertEquals(sobreGiroEsperado, cuenta.getSobregiro());
    }

    @Test
    public void testConsignarSinSobregiro(){
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        cuenta.consignar(1500);
        assertEquals(2500, cuenta.saldo);
        assertEquals(0, cuenta.getSobregiro());
    }

    @Test
    public void testConsignarMasQueSobregiro(){
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        cuenta.retirar(1500);
        cuenta.consignar(1500);
        assertEquals(1500, cuenta.saldo);
        assertEquals(0, cuenta.getSobregiro());
    }

    @Test
    public void testImprimirResumen() {
        CuentaCorriente cuenta = new CuentaCorriente(20000, 5);
        cuenta.consignar(1000);
        cuenta.retirar(500);
        String resultado = cuenta.imprimirResumen();
        assertEquals(true, resultado.contains("Saldo: 20500.0"));
        assertEquals(true, resultado.contains("totalTransacciones: 2"));
        assertEquals(true, resultado.contains("sobreGiro: 0.0"));
    }

}
