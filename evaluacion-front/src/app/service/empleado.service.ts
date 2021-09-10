import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constante } from '../models/constante';
import { Empleado } from '../models/empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  ruta: string;

  constructor(private http: HttpClient) {
    this.ruta = Constante.RUTA + "empleados/";
  }

  emplCreate(empl: Empleado): Observable<any> {
    return this.http.post<any>(this.ruta, empl);
  }

  emplFindAll(): Observable<any> {
    return this.http.get<any>(this.ruta);
  }

  emplFindById(id: number): Observable<any> {
    return this.http.get<any>(this.ruta + "search/id/" + id);
  }

  deleteEmpleadoById(id: number): Observable<any> {
    return this.http.delete<any>(this.ruta + "delete/" + id);
  }

  updateEmpleadoById(empleado: Empleado, id: number): Observable<any> {
    return this.http.put<any>(this.ruta + "update/" + id, empleado);
  }
}
