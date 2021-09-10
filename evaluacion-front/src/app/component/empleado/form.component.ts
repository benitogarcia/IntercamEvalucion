import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Empleado } from 'src/app/models/empleado';
import { EmpleadoService } from 'src/app/service/empleado.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})

export class FormComponent implements OnInit {

  empleado: Empleado;
  idEmpleado: number;
  isSubmitSuccess = false;
  isSubmitError = false;
  msgSuccess: string;
  msgErrror: string;

  constructor(private emplService: EmpleadoService, private rutaActiva: ActivatedRoute) { }

  ngOnInit(): void {

    this.empleado = new Empleado(0, '', '', '', null, 0.0, '');

    const id = this.rutaActiva.snapshot.params.id;

    this.idEmpleado = 0;
    if (id != null) {
      this.idEmpleado = id;
      this.empleado.id = id;

      this.emplService.emplFindById(id).subscribe(
        data => {
          this.idEmpleado = data.id;
          this.empleado = data;
        },
        err => {
          this.idEmpleado = 0;
          this.msgErrror = err.error.message;
        }
      );
    }

  }

  onSubmit(): void {
    if (this.idEmpleado == 0) {
      this.registrarEmpleado();
    } else {
      this.editarEmpleado();
    }
  }

  private registrarEmpleado(): void {

    this.emplService.emplCreate(this.empleado).subscribe(
      data => {
        this.empleado.id = data.id;
        this.idEmpleado = data.id;
        this.isSubmitSuccess = true;
        this.isSubmitError = false;
        this.msgSuccess = "El Empleado se registro."
      },
      err => {
        this.isSubmitSuccess = false;
        this.isSubmitError = true;
        this.msgErrror = err.error.message;
      }
    );
  }

  private editarEmpleado(): void {

    this.emplService.updateEmpleadoById(this.empleado, this.idEmpleado).subscribe(
      data => {
        this.empleado.id = data.id;
        this.idEmpleado = data.id;
        this.isSubmitSuccess = true;
        this.isSubmitError = false;
        this.msgSuccess = "El Empleado se edito."
      },
      err => {
        this.isSubmitSuccess = false;
        this.isSubmitError = true;
        this.msgErrror = err.error.message;
      }
    );

  }

}
