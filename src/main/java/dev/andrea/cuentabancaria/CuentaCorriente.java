package dev.andrea.cuentabancaria;

public class CuentaCorriente extends Cuenta {

    private float sobreGiro;

    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);

    }

    @Override
    public void calcularInteresMensual() {
        super.calcularInteresMensual();
    }

    @Override
    public void consignar(float cantidad) {
        super.consignar(cantidad);
        if (sobreGiro > 0) {
            sobreGiro = sobreGiro - cantidad;
            if (sobreGiro < 0) {
                sobreGiro = 0;
            }
        }
    }

    @Override
    public void extractoMensual() {
        super.extractoMensual();
    }

    public String imprimirResumen() {
        int totalTransacciones = numConsignaciones + numRetiros;
        String resultado = "";
        resultado = resultado + "Saldo: " + saldo;
        resultado = resultado + "\ncomisionMensual: " + comisionMensual;
        resultado = resultado + "\ntotalTransacciones: " + totalTransacciones;
        resultado = resultado + "\nsobreGiro: " + sobreGiro;
        return resultado;
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo = saldo - cantidad;
        } else {
            sobreGiro = sobreGiro + (cantidad - saldo);
            saldo = 0;
        }
        numRetiros++;
    }

}
