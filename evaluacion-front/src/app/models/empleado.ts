export class Empleado {

    id: number;
    nombre: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    fechaNacimiento: Date;
    ingresos: number;
    codigoPostal: string;


    constructor(id: number, nombre: string, apellidoPaterno: string, apellidoMaterno: string, fechaNacimiento: Date, ingresos: number, codigoPostal: string) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.ingresos = ingresos;
        this.codigoPostal = codigoPostal;
    }

    toString() {
        return "{id:" + this.id + ", nombre:" + this.nombre + ", apellidoPaterno:" + this.apellidoPaterno + ", apellidoMaterno:" + this.apellidoMaterno + ", fechaNacimiento: + this.fechaNacimiento.toDateString() + , ingresos:" + this.ingresos + ", codigoPostal:" + this.codigoPostal + "}";
    }

}
