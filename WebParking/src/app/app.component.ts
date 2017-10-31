import { Component, OnInit } from '@angular/core';
import { Vehiculo } from './app.vehiculo';
import { AppService } from './app.component.service';
import {Observable} from "rxjs"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AppService]
})
export class AppComponent {
  title = 'App Parking';
  resultado;
  public newVehiculo:Vehiculo;
  public listaVehiculos;
  public placaEliminar:string;
  public mensajeRegistro;
  public mensajeRetiro;
  public claseAlertRegistro="";
  public claseAlertRetiro="";
  public mostrarAlertaRegistro=false;
  public mostrarAlertaRetiro=false;

  constructor(private appService:AppService){
    this.newVehiculo=new Vehiculo();
    this.newVehiculo.tipo="Carro";
    this.mensajeRetiro="";
  }

  ngOnInit(){
    this.consultarCampoVehiculos();
  }

  registrarVehiculo(){
    this.appService.registrarVehiculo(this.newVehiculo).then(
      res=>{
        this.mensajeRegistro=res;
        this.mensajeRegistro=this.mensajeRegistro._body;
        this.validarMensajeRegistro()
        this.consultarCampoVehiculos();
        Observable.interval(5000)
        .subscribe(i => { 
          this.mostrarAlertaRegistro=false;
        });
      }
    );
  }

  consultarCampoVehiculos(){
    this.appService.consultarCampoVehiculos().subscribe(
      data=>{
        this.listaVehiculos=data;
        this.listaVehiculos=JSON.parse(this.listaVehiculos._body);
      }
    );
  }

  retirarVehiculo(){
    this.appService.retirarVehiculo(this.placaEliminar).then(
      res=>{
        this.mensajeRetiro=res;
        this.mensajeRetiro=this.mensajeRetiro._body;
        this.validarMensajeRetiro();
        this.consultarCampoVehiculos();
        Observable.interval(2000)
        .subscribe(i => { 
          this.mostrarAlertaRetiro=false;
        });
      }
    );
  }

  validarMensajeRegistro(){
    if(this.mensajeRegistro=="Un vehículo ha ingresado"){
      this.claseAlertRegistro="alert-success";
    }else if(this.mensajeRegistro=="El vehículo no pudo ser ingresado"){
      this.claseAlertRegistro="alert-danger";
    }else if(this.mensajeRegistro=="El vehiculo no puede ingresar hoy. El vehiculo no se encuentra en un día hábil."){
      this.claseAlertRegistro="alert-warning";
    }else if(this.mensajeRegistro=="El cupo para carros está lleno."){
      this.claseAlertRegistro="alert-warning";
    }else if(this.mensajeRegistro=="El cupo para motos está lleno."){
      this.claseAlertRegistro="alert-warning";
    }else if(this.mensajeRegistro=="Tipo de vehículo no válido."){
      this.claseAlertRegistro="alert-danger";
    }
    this.mostrarAlertaRegistro=true;
  }

  validarMensajeRetiro(){
    if(this.mensajeRetiro=="No se pudo calcular la hora."){
      this.claseAlertRetiro="alert-danger";
    }else if(this.mensajeRetiro=="Ha ocurrido un error."){
      this.claseAlertRetiro="alert-danger";
    }else{
      this.claseAlertRetiro="alert-info";
    }
    this.mostrarAlertaRetiro=true;
  }

}
