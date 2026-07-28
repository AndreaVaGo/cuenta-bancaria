package dev.andrea.cuentabancaria;

public class CuentaCorriente extends Cuenta {

    private float sobregiro;

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
        if (sobregiro > 0) {
            sobregiro = sobregiro - cantidad;
            if (sobregiro < 0) {
                sobregiro = 0;
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
        resultado = resultado + "\nsobregiro: " + sobregiro;
        return resultado;
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo = saldo - cantidad;
        } else {
            sobregiro = sobregiro + (cantidad - saldo);
            saldo = 0;
        }
        numRetiros++;
    }

    public float getSobregiro() {
        return sobregiro;
    }

}
