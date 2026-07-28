package dev.andrea.cuentabancaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testRetirar() {
        Cuenta cuenta = new Cuenta(1000, 5);
        cuenta.retirar(300);
        assertEquals(700, cuenta.saldo);
        assertEquals(1, cuenta.numRetiros);
    }

    @Test
    public void testRetirarSuperaSaldo() {
        Cuenta cuenta = new Cuenta(1000, 5);
        cuenta.retirar(1500);
        assertEquals(1000, cuenta.saldo);
        assertEquals(0, cuenta.numRetiros);
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
