package dev.andrea.cuentabancaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CuentaCorrienteTest {

    @Test
    public void testConstructor() {
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        assertEquals(0, cuenta.getSobreGiro());
    }

    @Test
    public void testRetirarSinSobregiro(){
        CuentaCorriente cuenta = new CuentaCorriente(20000, 5);
        cuenta.retirar(500);
        assertEquals(19500, cuenta.saldo);
        assertEquals(0, cuenta.getSobreGiro());
    }

    @Test
    public void testRetirarConSobregiro(){
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        cuenta.retirar(1500);
        assertEquals(0, cuenta.saldo);
        assertEquals(500, cuenta.getSobreGiro());
    }

    @Test
    public void testConsignarSinSobregiro(){
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        cuenta.consignar(1500);
        assertEquals(2500, cuenta.saldo);
        assertEquals(0, cuenta.getSobreGiro());
    }

    @Test
    public void testConsignarMasQueSobregiro(){
        CuentaCorriente cuenta = new CuentaCorriente(1000, 5);
        cuenta.retirar(1500);
        cuenta.consignar(1500);
        assertEquals(1500, cuenta.saldo);
        assertEquals(0, cuenta.getSobreGiro());
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
