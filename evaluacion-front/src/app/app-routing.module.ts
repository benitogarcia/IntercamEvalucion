import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormComponent } from './component/empleado/form.component';
import { ListaComponent } from './component/empleado/lista.component';

const routes: Routes = [
  {path: "", component: ListaComponent},
  {path: "empleado/form", component: FormComponent},
  {path: "empleado/form/:id", component: FormComponent},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
