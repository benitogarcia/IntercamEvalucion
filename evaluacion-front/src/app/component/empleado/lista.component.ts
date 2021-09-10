import { Component, OnInit } from '@angular/core';
import { ElementHelper } from 'protractor';
import { Empleado } from 'src/app/models/empleado';
import { EmpleadoService } from 'src/app/service/empleado.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  empleados: Empleado[];
  errorLoad = false;
  errorLoadDelete = false;
  errorLoadSuccess = false;
  msgError: string;
  msgErrrorDelete: string;
  msgErrrorSuccess: string;

  constructor(private emplService: EmpleadoService) { }

  ngOnInit(): void {
    this.loadEmpleados();
  }

  private loadEmpleados(): void{
    this.emplService.emplFindAll().subscribe(
      data => {
        this.empleados = data;
        if (data == null) {
          this.errorLoad = true;
          this.msgError = "No se encontro ningun registro."
        }
      },
      err => {
        this.errorLoad = true;
        this.msgError = err.error.message;
      }
    );
  }

  alertReset(): void {
    this.errorLoadDelete = false;
    this.errorLoadSuccess = false;
  }

  deleteEmpleado(id: number, nombre: string, apellidoPaterno): void {
    if(confirm("Eliminar el usuario: " + id + " - " + nombre + " " + apellidoPaterno)){
      this.emplService.deleteEmpleadoById(id).subscribe(
        data => {
         this.loadEmpleados();
         this.errorLoadSuccess = true;
         this.msgErrrorSuccess = "Se elimino con exito.";
        },
        err => {
         this.errorLoadDelete = true;
         this.msgErrrorDelete = err.error.message;
        }
      ); 
    }
  }

}
