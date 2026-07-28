package dev.andrea.cuentabancaria;

public class CuentaAhorros extends Cuenta {

    private boolean cuentaAhorrosActiva;

    public CuentaAhorros(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        if (saldo < 10000) {
            cuentaAhorrosActiva = false;
        } else {
            cuentaAhorrosActiva = true;
        }
    }

    @Override
    public void calcularInteresMensual() {
        super.calcularInteresMensual();
    }

    @Override
    public void consignar(float cantidad) {
        if (cuentaAhorrosActiva) {
            super.consignar(cantidad);
        }
    }

    @Override
    public void extractoMensual() {
        if (numRetiros > 4) {
            comisionMensual = (numRetiros - 4) * 1000;
        }
        super.extractoMensual();
        if (saldo < 10000) {
            cuentaAhorrosActiva = false;
        } else {
            cuentaAhorrosActiva = true;
        }
    }

    public String imprimirResumen() {
        int totalTransacciones = numConsignaciones + numRetiros;
        String resultado = "";
        resultado = resultado + "Saldo: " + saldo;
        resultado = resultado + "\ncomisionMensual: " + comisionMensual;
        resultado = resultado + "\ntotalTransacciones: " + totalTransacciones;
        return resultado;
    }

    @Override
    public void retirar(float cantidad) {
        if (cuentaAhorrosActiva) {
            super.retirar(cantidad);
        }
    }

    public boolean getCuentaAhorrosActiva() {
    return cuentaAhorrosActiva;
    }

}
