import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { Vehiculo } from './app.vehiculo';
import * as xml2js from 'xml2js';

@Injectable()
export class AppService{
    private headers = new Headers(
        {   'accept': 'application/json', 
            'Content-Type': 'application/json', 
            'IF-MATCH': '*', 
            'X-HTTP-Method': null
        });
    constructor(private http:Http){}

    registrarVehiculo(vehiculo:Vehiculo){
      return this.http.post("http://localhost:8090/vehicle", vehiculo, {headers:this.headers}).toPromise()
    }

    consultarCampoVehiculos(){
        return this.http.get("http://localhost:8090/vehicle/allVehicles")
            .map((response: Response) =>response);
    }

    retirarVehiculo(placa:string){
        return this.http.delete("http://localhost:8090/vehicle/delete/"+placa).toPromise();
    }

}