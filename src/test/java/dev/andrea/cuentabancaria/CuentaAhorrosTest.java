package dev.andrea.cuentabancaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testConsignarCuentaActiva() {
        CuentaAhorros cuenta = new CuentaAhorros(10000, 5);
        cuenta.consignar(500);
        assertEquals(10500, cuenta.saldo);
    }

    @Test
    public void testConsignarCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(10, 5);
        cuenta.consignar(500);
        assertEquals(10, cuenta.saldo);
    }

    @Test
    public void testRetirarCuentaActiva() {
        CuentaAhorros cuenta = new CuentaAhorros(20000, 5);
        cuenta.retirar(500);
        assertEquals(19500, cuenta.saldo);
    }

    @Test
    public void testRetirarCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(10, 5);
        cuenta.retirar(500);
        assertEquals(10, cuenta.saldo);
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
