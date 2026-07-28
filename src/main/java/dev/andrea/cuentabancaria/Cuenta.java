package dev.andrea.cuentabancaria;

public class Cuenta {

    protected float saldo;
    protected int numConsignaciones = 0;
    protected int numRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual = 0;
    public Integer consignar;

    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;

    }

    public void consignar(float cantidad) {
        saldo = saldo + cantidad;
        numConsignaciones++;
    }

    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo = saldo - cantidad;
            numRetiros++;
        } else {
        }
    }

    public void calcularInteresMensual() {
        float interes = saldo * (tasaAnual / 12 / 100);
        saldo = saldo + interes;
    }

    public void extractoMensual() {
        saldo = saldo - comisionMensual;
        calcularInteresMensual();
    }

    public String imprimir() {
        String resultado = "";
        resultado = resultado + "Saldo: " + saldo;
        resultado = resultado + "\nConsignaciones: " + numConsignaciones;
        resultado = resultado + "\nRetiros: " + numRetiros;
        resultado = resultado + "\ntasaAnual: " + tasaAnual;
        resultado = resultado + "\ncomisionMensual: " + comisionMensual;
        return resultado;
    }

}
